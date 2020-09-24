package tr.edu.gazi.bm.bitirme2018.flink.stream;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import tr.edu.gazi.bm.bitirme2018.flink.interfaces.DataMatch;
import tr.edu.gazi.bm.bitirme2018.flink.persist.entity.DataModel;
import tr.edu.gazi.bm.bitirme2018.flink.parser.HtmlParser;
import weka.core.DenseInstance;
import weka.core.Instance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Stream {
    private static List<Instance> lstInstance;

    private static Stream instance;

    private Stream(){
        lstInstance=  new ArrayList<>();
    }
    public static Stream getInstance(){
        if (instance == null){
            instance= new Stream();
            lstInstance= new ArrayList<>();
        }
        return instance;
    }

    public List<Instance> getLstInstance(){
        if (lstInstance.size() == 0){
            lstInstance= setDataInstance();
        }
        return lstInstance;
    }


    public synchronized List<Instance> setDataInstance() {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStream<String> inputURLs = env.fromElements("https://tr.investing.com/crypto/bitcoin/btc-usd");

        inputURLs.map(new MapFunction<String, List<DataModel>>() {
            @Override
            public List<DataModel> map(String s) throws Exception {
                HtmlParser htmlParser = new HtmlParser();
                List<DataModel> lstDataModel = htmlParser.siteParserData(s);
                lstDataModel.forEach(dataModel -> {
                    double[] dataAtr = new double[dataModel.getClass().getDeclaredFields().length - 1];
                    Arrays.stream(dataModel.getClass().getDeclaredFields())
                            .forEach(field -> {
                                field.setAccessible(true);
                                try {
                                    Object val = field.get(dataModel);
                                    DataMatch fAnno = field.getAnnotation(DataMatch.class);
                                    if (fAnno != null && fAnno.dataClass() != true) {
                                        dataAtr[fAnno.index() - 1] = (Double) val;
                                    }
                                } catch (IllegalAccessException e) {
                                    e.printStackTrace();
                                }
                            });
                    Instance instance = new DenseInstance(1.0, dataAtr);
                    lstInstance.add(instance);
                });

                return lstDataModel;
            }
        });


        try {
            env.execute("URL download job");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("***********   Done   ************");
        return lstInstance;
    }

}
