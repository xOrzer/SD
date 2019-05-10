package classifiers.services;

import weka.classifiers.functions.LibSVM;

import java.lang.reflect.InvocationTargetException;

public class LibSVMClassifierService extends ClassifierService {

    public static final Class CLASSIFIER_CLASS = LibSVM.class ;
    public static final int PORT = 1100 ;

    public LibSVMClassifierService() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        super(CLASSIFIER_CLASS, PORT);
    }

    public static void main(String[] args) {
        try {
            new LibSVMClassifierService() ;
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
