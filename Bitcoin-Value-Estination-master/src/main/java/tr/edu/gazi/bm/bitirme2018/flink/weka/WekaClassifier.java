package tr.edu.gazi.bm.bitirme2018.flink.weka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tr.edu.gazi.bm.bitirme2018.flink.helper.Helper;
import tr.edu.gazi.bm.bitirme2018.flink.stream.Stream;
import weka.classifiers.AbstractClassifier;
import weka.classifiers.Evaluation;
import weka.core.Attribute;
import weka.core.Instances;
import weka.core.Utils;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by flink on 2/23/18.
 */
public class WekaClassifier {

    Helper helper = new Helper();
    private Logger LOGGER = LoggerFactory.getLogger(WekaClassifier.class);

    public <T extends AbstractClassifier> String getSuccesRatio(String filePath, T classifier, String options) {

        try {
            Instances data = helper.getDataInstance(filePath);
            //network training
            if (options != null) {
                classifier.setOptions(Utils.splitOptions(options));
            }
            classifier.buildClassifier(data);

// burada attributelerin
            ArrayList<Attribute> lstAttributes = new ArrayList<>();
            for (int i = 0; i < data.numAttributes(); i++) {
                lstAttributes.add(data.attribute(i));
            }

            Instances dataUnlabeled = new Instances("TestInstances", lstAttributes, 0);
            dataUnlabeled.add(Stream.getInstance().getLstInstance().get(0));
            dataUnlabeled.setClassIndex(dataUnlabeled.numAttributes() - 1);
            double classif = classifier.classifyInstance(dataUnlabeled.firstInstance());

            System.out.println("****************   " + classif + "   *************************");
            System.out.println("final weights:");
            System.out.println(classifier);


            //  double classify = mlp.classifyInstance(testInstance);

            System.out.println("\nSuccess Metrics: ");
            Evaluation eval = new Evaluation(data);
            eval.evaluateModel(classifier, data);

            String tahmin = "Günlük değişim tahmini " + predict(classif) + " 'tir. Geçmiş data verileri ise  ";
            System.out.println(eval.toSummaryString("\nResults\n======\n", true));

            return tahmin + "  " + eval.toSummaryString("\nResults\n======\n", true);

        } catch (IOException e) {
            LOGGER.info(e.getMessage());
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
        }


        return "";
    }

    private int predict(double classifier) {
        if (classifier == 1) {
            return -18;
        } else if (classifier == 2) {
            return -14;
        } else if (classifier == 3) {
            return -10;
        } else if (classifier == 4) {
            return -6;
        } else if (classifier == 5) {
            return -2;
        } else if (classifier == 6) {
            return 2;
        } else if (classifier == 7) {
            return 6;
        } else if (classifier == 8) {
            return 10;
        } else if (classifier == 9) {
            return 14;
        } else if (classifier == 10) {
            return 18;
        }
        return 0;
    }
    /*

    public static void main(String[] args) throws Exception {

        Helper tr.edu.gazi.bm.bitirme2018.flink.helper= new Helper();

        BufferedReader datafile = tr.edu.gazi.bm.bitirme2018.flink.helper.readResourceFileAsBufferedReader("data/iris.csv");
        Instances data = new Instances(datafile);
        data.setClassIndex(data.numAttributes() - 1);

*//*
        //do not use first and second
        Instance first = data.instance(0);
        Instance second = data.instance(1);


        Classifier ibk = new J48();
        ibk.buildClassifier(data);

        double class1 = ibk.classifyInstance(first);
        double class2 = ibk.classifyInstance(second);


        System.out.println("first: " + class1 + "\nsecond: " + class2);

*//*

        //network variables
        String backPropOptions =
                "-L "+0.1 //learning rate
                        +" -M "+0 //momentum
                        +" -N "+10000 //epoch
                        +" -V "+0 //validation
                        +" -S "+0 //seed
                        +" -E "+0 //error
                        +" -H "+"3"; //hidden nodes.
        //e.g. use "3,3" for 2 level hidden layer with 3 nodes

        //network training
        MultilayerPerceptron mlp = new MultilayerPerceptron();
        mlp.setOptions(Utils.splitOptions(backPropOptions));
        mlp.buildClassifier(data);
        System.out.println("final weights:");
        System.out.println(mlp);

        System.out.println("\nSuccess Metrics: ");
        Evaluation eval = new Evaluation(data);
        eval.evaluateModel(mlp, data);

        System.out.println(eval.toSummaryString("\nResults\n======\n", true));


    }

*/


}
