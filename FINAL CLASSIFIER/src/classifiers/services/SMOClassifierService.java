package classifiers.services;

import classifiers.implementation.ClassifierServerImpl;
import weka.classifiers.functions.SMO;

import java.lang.reflect.InvocationTargetException;

public class SMOClassifierService extends ClassifierService {

    public static final Class CLASSIFIER_CLASS = SMO.class ;
    public static final int PORT = 1102 ;

    public SMOClassifierService() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        super(CLASSIFIER_CLASS, PORT);
    }

    public static void main(String[] args) {
        try {
            new ClassifierServerImpl(PORT, CLASSIFIER_CLASS) ;
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
