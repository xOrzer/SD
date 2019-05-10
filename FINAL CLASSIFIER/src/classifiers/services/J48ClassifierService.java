package classifiers.services;

import weka.classifiers.trees.J48;

import java.lang.reflect.InvocationTargetException;

public class J48ClassifierService extends ClassifierService {

    public static final Class CLASSIFIER_CLASS = J48.class ;
    public static final int PORT = 1099 ;

    public J48ClassifierService() throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        super(CLASSIFIER_CLASS, PORT);
    }

    public static void main(String[] args) {
        try {
            new J48ClassifierService() ;
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
