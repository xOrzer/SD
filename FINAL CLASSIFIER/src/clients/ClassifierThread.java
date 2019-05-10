package clients;

import classifiers.common.ClassifierServerInterface;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.core.Instances;

import java.rmi.Naming;

public class ClassifierThread extends Thread {

    private Class classifierClass ;
    private String[] options ;
    private String serverName ;
    private int port ;
    private Instances train, cross;

    private ClassifierServerInterface classifierServer;
    private Evaluation eval ;

    public <T extends Classifier> ClassifierThread(Class<T> classifierClass, String[] options, String serverName, int port, Instances train, Instances cross) throws Exception {
        this.classifierClass = classifierClass ;
        this.options = options ;
        this.serverName = serverName ;
        this.port = port ;
        this.train = train ;
        this.cross = cross ;
    }

    public <T extends Classifier> ClassifierThread(Class<T> classifierClass, String serverName, int port, Instances train, Instances cross) throws Exception {
        this(classifierClass, null, serverName, port, train, cross) ;
    }

    @Override
    public void run() {
        try {
            System.out.println("Training " + classifierClass.getSimpleName() + "...");
            classifierServer = (ClassifierServerInterface) Naming.lookup(getRmiUrlFor(classifierClass, serverName, port));
            if(this.options != null) classifierServer.setOptions(options);
            classifierServer.buildClassifier(train);
            eval = classifierServer.eval(cross);
            System.out.println();
            System.out.println("--- " + classifierClass.getSimpleName() + " ---");
            System.out.println(eval.toSummaryString());
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private <T extends Classifier> String getRmiUrlFor(Class<T> classifierClass, String serverName, int port){
        String RMI_URL_BASE = "rmi://" ;
        return RMI_URL_BASE + serverName + ":" + port + "/" + classifierClass.getSimpleName() ;
    }

    public ClassifierServerInterface getClassifierServer() {
        return classifierServer;
    }

    public Evaluation getEval() {
        return eval;
    }

    public Class getClassifierClass() {
        return classifierClass;
    }

}
