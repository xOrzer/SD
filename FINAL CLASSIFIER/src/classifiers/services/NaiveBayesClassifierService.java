package classifiers.services;

import weka.classifiers.bayes.NaiveBayes;

import java.lang.reflect.InvocationTargetException;

public class NaiveBayesClassifierService extends ClassifierService {

    public static final Class CLASSIFIER_CLASS = NaiveBayes.class ;
    public static final int PORT = 1101 ;

    public NaiveBayesClassifierService() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        super(CLASSIFIER_CLASS, PORT);
    }

    public static void main(String[] args) {
        try {
            new NaiveBayesClassifierService() ;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

}
