package tr.edu.gazi.bm.bitirme2018.flink.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tr.edu.gazi.bm.bitirme2018.flink.converter.CsvConverter;
import tr.edu.gazi.bm.bitirme2018.flink.helper.CsvReader;
import tr.edu.gazi.bm.bitirme2018.flink.helper.Helper;
import tr.edu.gazi.bm.bitirme2018.flink.helper.OsDetector;
import tr.edu.gazi.bm.bitirme2018.flink.interfaces.IReader;
import tr.edu.gazi.bm.bitirme2018.flink.persist.entity.DataModel;
import tr.edu.gazi.bm.bitirme2018.flink.weka.Model;
import tr.edu.gazi.bm.bitirme2018.flink.weka.WekaClassifier;
import weka.classifiers.AbstractClassifier;
import weka.classifiers.lazy.IBk;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by flink on 2/23/18.
 */
public class MainApp {

    private static Logger LOGGER = LoggerFactory.getLogger(MainApp.class);


    // işlem tamamdır yarın buluşalım mı
    public static void main(String[] args) throws Exception {
        System.out.println("islem basladı");
        Helper helper = new Helper();

        CsvConverter<DataModel> converter = new CsvConverter<DataModel>();

        IReader reader = new CsvReader<>();
        List<DataModel> lstCleanData = new LinkedList<>();


        String absolitePath = helper.getAbsolitePath("csv/temizCoin.csv");

        List<DataModel> lstModel = reader.reader(absolitePath, ";", DataModel.class);
        lstModel.stream().forEach(dataModel -> {
            if (dataModel.getFark() < -16 && dataModel.getFark() >= -20) {
                dataModel.setSinif(1);
            } else if (dataModel.getFark() < -12 && dataModel.getFark() >= -16) {
                dataModel.setSinif(2);
            } else if (dataModel.getFark() < -8 && dataModel.getFark() >= -12) {
                dataModel.setSinif(3);
            } else if (dataModel.getFark() < -4 && dataModel.getFark() >= -8) {
                dataModel.setSinif(4);
            } else if (dataModel.getFark() < 0 && dataModel.getFark() >= -4) {
                dataModel.setSinif(5);
            } else if (dataModel.getFark() < 4 && dataModel.getFark() >= 0) {
                dataModel.setSinif(6);
            } else if (dataModel.getFark() < 8 && dataModel.getFark() >= 4) {
                dataModel.setSinif(7);
            } else if (dataModel.getFark() < 12 && dataModel.getFark() >= 8) {
                dataModel.setSinif(8);
            } else if (dataModel.getFark() < 16 && dataModel.getFark() > 12) {
                dataModel.setSinif(9);
            } else if (dataModel.getFark() < 20 && dataModel.getFark() >= 20) {
                dataModel.setSinif(10);
            }
            if (dataModel.getSinif() != 0) {
                lstCleanData.add(dataModel);
            }
        });


        for (int i = lstCleanData.size() - 1; i >= 0; i--) {
            if (i != 0) {
                DataModel currentModel = lstCleanData.get(i);
                DataModel prevModel = lstCleanData.get(i - 1);
                currentModel.setFark(prevModel.getFark());
                currentModel.setDusuk(prevModel.getDusuk());
                currentModel.setYuksek(prevModel.getYuksek());
                currentModel.setHacim(prevModel.getHacim());
                lstCleanData.set(i, currentModel);
            }
        }

        lstCleanData.forEach(dataModel -> {
            System.out.println(dataModel.toString());
        });

        String absPath = helper.getAbsolitePath("application.properties").substring(0,
                helper.getAbsolitePath("application.properties").lastIndexOf("/"));
        File dataFile = null;
        try {
            dataFile = converter.listToCsvFile(lstCleanData, absPath, "testData.arff", DataModel.class,
                    DataModel.class.getDeclaredField("sinif"));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        // *** Weka Classifier Example
        WekaClassifier wekaClassifier = new WekaClassifier();

        List<Model> lstModel2 = new ArrayList<>();
        ArrayList<Class<? extends AbstractClassifier>> lstSubClass = helper.getAllClassForPackageName("weka.classifiers");
        int sayac = 0;
        for (Class<? extends AbstractClassifier> clazz : lstSubClass) {
            try {
                Model model = new Model();
                model.setId(sayac);
                LOGGER.info(clazz.getName());
                String succesRatio= clazz.getName();
                succesRatio += wekaClassifier.getSuccesRatio(dataFile.getName(), clazz.newInstance(), null);
                model.setData(succesRatio);
                lstModel2.add(model);
                sayac++;
            } catch (Exception ex) {
                LOGGER.info("Class not found exception " + ex.getMessage());
            }
        }
        String tempFolder = System.getProperty("java.io.tmpdir");

        if (OsDetector.isWindows()) {
            helper.writeExcel(tempFolder + "\\" + System.nanoTime() + ".xlsx", lstModel2);
        } else {
            helper.writeExcel(tempFolder + "/" + System.nanoTime() + ".xlsx", lstModel2);
        }
    }
}
