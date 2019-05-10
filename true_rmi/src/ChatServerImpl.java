import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Vector;


public class ChatServerImpl implements ChatServerInteface {

	HashMap<ChatClientInterface, String> clients =  new HashMap<ChatClientInterface, String>() ;

	public ChatServerImpl() {
		try{
			String name = "server" ;
			ChatServerInteface stub = (ChatServerInteface) UnicastRemoteObject.exportObject(this, 0) ;
			
			Registry registry = LocateRegistry.createRegistry(1099) ;
			registry.rebind(name, stub) ;
			
			System.out.println("Server bound") ;
		}catch (Exception e) {
			e.printStackTrace() ;
		}
	}
	
	
	private void updateConnectedUsers(){

		Object objs[] = clients.values().toArray() ; 
		String[] names = new String[objs.length] ;
		for (int i=0 ; i<objs.length ; i++) names[i] = (String)objs[i] ;

		ChatClientInterface tmp = null ; // pour retirer l'objet deconnecte en cas d'exception...
		try{
			for (ChatClientInterface client : clients.keySet()){
				tmp = client ;
				tmp.updateConnectedUsers(names) ;
			}
		} catch (RemoteException e) {
			System.out.println(clients.get(tmp) + " est deconnecte !");
			clients.remove(tmp) ;
			updateConnectedUsers() ;
		}
	}
	
	@Override
	public void register(ChatClientInterface c) throws RemoteException {
		System.out.println("Server registering " + c.getUserName());
		clients.put(c, c.getUserName()) ;
		updateConnectedUsers() ;
	}

	@Override
	public void unRegister(ChatClientInterface c) throws RemoteException {
		System.out.println("Server unregistering " + c.getUserName());
		clients.remove(c) ;
		updateConnectedUsers() ;
	}

	@Override
	public void sendToAll(String msg, ChatClientInterface from) throws RemoteException {
		String senderName = clients.get(from) ;
		System.out.println("Server sending " + msg + " from " + senderName);
		
		Vector<ChatClientInterface> disconnected = new Vector<ChatClientInterface>() ;
		for(ChatClientInterface c : clients.keySet()){
			try{
				c.addMSG(senderName + " > " + msg + "\n") ;
			}catch(RemoteException e){
				disconnected.add(c) ; // gestion des clients d???connect???s
			}
		}
		
		// nettoyage de la liste
		if (!disconnected.isEmpty()) {
			for(ChatClientInterface c : disconnected){
				System.out.println(c + " est deconnecte !");
				clients.remove(c) ;
			}
			updateConnectedUsers() ;
		}
	}

	public static void main(String[] args) {
		new ChatServerImpl() ;
	}
	
	
}
