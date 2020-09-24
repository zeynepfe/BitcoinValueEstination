package tr.edu.gazi.bm.bitirme2018.flink.util;

import org.junit.Before;
import org.junit.Test;
import tr.edu.gazi.bm.bitirme2018.flink.helper.Helper;
import tr.edu.gazi.bm.bitirme2018.flink.weka.Model;

import java.util.ArrayList;
import java.util.List;

public class ReadWriteExcelFileTest {
    Helper helper = new Helper();
    ReadWriteExcelFile excelFile;
    List<Model> lstModel = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        for (int i = 0; i < 100; i++) {
            lstModel.add(helper.createDummyData(Model.class));
        }
    }

    @Test
    public void writeToExcel() {
        String tempFolder = System.getProperty("java.io.tmpdir");
        helper.writeExcel(tempFolder + "\\" + "asd.xlsx", lstModel);
    }
}