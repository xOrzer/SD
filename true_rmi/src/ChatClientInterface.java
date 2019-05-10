import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatClientInterface extends Remote {

	public String getUserName() throws RemoteException ;
	
	public void addMSG(String msg) throws RemoteException ;

	public void updateConnectedUsers(String[] names) throws RemoteException ;
	
}
