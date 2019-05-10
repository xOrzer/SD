package classifiers.services;

import weka.classifiers.functions.MultilayerPerceptron;

import java.lang.reflect.InvocationTargetException;

public class MLPClassifierService extends ClassifierService{

    public static final Class CLASSIFIER_CLASS = MultilayerPerceptron.class ;
    public static final int PORT = 1103 ;

    public MLPClassifierService() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        super(CLASSIFIER_CLASS, PORT);
    }

    public static void main(String[] args) {
        try {
            new MLPClassifierService() ;
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
