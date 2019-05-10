package classifiers.common;

import weka.classifiers.Evaluation;
import weka.core.Instance;
import weka.core.Instances;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClassifierServerInterface extends Remote {

    public Class getClassifierClass() throws RemoteException ;
    public void setOptions(String[] options) throws Exception;
    public void buildClassifier(Instances train) throws RemoteException ;
    public Evaluation eval(Instances cross) throws Exception;
    public double classifyInstance(Instance instance) throws Exception ;
}
