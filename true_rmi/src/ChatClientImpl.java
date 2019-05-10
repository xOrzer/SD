import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ChatClientImpl implements ChatClientInterface {

	private ChatClientFrame frm ;
	
	String userName ;
	
	ChatClientInterface stub ;
	ChatServerInteface serv ;
	
	public ChatClientImpl(ChatClientFrame f, String userName) {
		frm = f ;
		this.userName = userName ;
		try {

			stub = (ChatClientInterface) UnicastRemoteObject.exportObject(this, 0) ;

			String name = "server" ;
			Registry registry = LocateRegistry.getRegistry() ;
			serv = (ChatServerInteface) registry.lookup(name) ;
			serv.register(this);
			
			frm.setTitle("Connected as :" + userName);
			
		} catch (Exception e) {
			e.printStackTrace() ;
		}
	}

	@Override
	public String getUserName() throws RemoteException {
		System.out.println(userName + " sending user name !");
		return userName ;
	}

	@Override
	public void addMSG(String msg) throws RemoteException {
		System.out.println(userName + " adding msg : " + msg);
		frm.appendMsg(msg) ;
	}

	@Override
	public void updateConnectedUsers(String[] names) throws RemoteException {
		System.out.println(userName + " updating connected user names !");
		frm.users.setListData(names) ;
	}
	
	public void sendToAll(String msg) {
		try {
			serv.sendToAll(msg, this) ;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void unRegister() {
		try {
			serv.unRegister(this) ;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
