package classifiers.services;

import classifiers.implementation.ClassifierServerImpl;

import java.lang.reflect.InvocationTargetException;

public class ClassifierService {

    public ClassifierService(Class classifierClass, int port) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        new ClassifierServerImpl(port, classifierClass) ;
    }
}
