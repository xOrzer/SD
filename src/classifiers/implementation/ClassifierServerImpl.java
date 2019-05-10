package classifiers.implementation;

import classifiers.common.ClassifierServerInterface;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.OptionHandler;

import java.lang.reflect.InvocationTargetException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ClassifierServerImpl implements ClassifierServerInterface {

    private Classifier classifier ;

    public <P extends Classifier> ClassifierServerImpl(int port, Class<P> classifierClass) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        classifier = classifierClass.getDeclaredConstructor().newInstance() ; //new J48() ;

        try{
            String name = classifierClass.getSimpleName() ;
            ClassifierServerInterface stub = (ClassifierServerInterface) UnicastRemoteObject.exportObject(this, 0) ;
            Registry registry = LocateRegistry.createRegistry(port) ;
            registry.rebind(name, stub) ;

            System.out.println(classifier.getClass().getSimpleName() +  " Server bound") ;
        }catch (Exception e) {
            e.printStackTrace() ;
        }
    }

    public Class getClassifierClass() throws RemoteException {
        return classifier.getClass() ;
    }

    public void setOptions(String[] options) throws Exception {
        ((OptionHandler)classifier).setOptions(options) ;
    }

    public void buildClassifier(Instances train) throws RuntimeException {
        try {
            System.out.println();
            System.out.println("Training " + classifier.getClass().getSimpleName() + " ...");
            classifier.buildClassifier(train);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Evaluation eval(Instances cross) throws Exception {
        Evaluation eval = new Evaluation(cross);
        eval.evaluateModel(classifier, cross);
        System.out.println();
        System.out.println("--- " + classifier.getClass().getSimpleName() + " ---");
        System.out.println(eval.toSummaryString());
//        System.out.println(eval.toClassDetailsString());
//        System.out.println(eval.toMatrixString());
        return eval ;
    }

    public double classifyInstance(Instance instance) throws Exception {
        return classifier.classifyInstance(instance) ;
    }

}
