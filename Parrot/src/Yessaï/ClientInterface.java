package Yessaï;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientInterface extends Remote {
    public void showYourName() throws RemoteException;
}
