package CallbackApp;

import corba.*;
import org.omg.CORBA.ORB;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileReader;

public class ChatClientImpl extends ChatClientPOA {
	private String name = null ;
	private ORB orb ;
	private ChatServer server ;
	private ChatClient cref ;
	private ChatClientFrame frm ;

	public ChatClientImpl(ChatClientFrame f, String name, String[] args) {
		this.name = name ;
		frm = f ;

		try{



			// Initialisation et démarrage de l'ORB :
			// l'appel à orb.run() étant bloquant, on l'exécute dans un Thread.
			orb = ORB. init (args, null) ;
			Thread orbThread = new Thread(new Runnable() {
				@Override
				public void run() {
					orb.run() ; // !!! bloquant !!! donc dans un thread
				}
			}) ;
			orbThread.start() ;

			// creation de la r�f�rence CORBA sur l'impl�mentation du client
			POA rootPOA = POAHelper. narrow (orb.resolve_initial_references("RootPOA")) ;
			rootPOA.the_POAManager().activate() ;
			org.omg.CORBA.Object ref = rootPOA.servant_to_reference(this);
			cref = ChatClientHelper. narrow (ref);

			// lecture de l'IOR du serveur
			BufferedReader br = new BufferedReader(new FileReader("server_ior.txt")) ;
			String ior = br.readLine() ;
			br.close() ;

			// récupération de la référence sur le serveur (à partir de son IOR)
			org.omg.CORBA.Object o = orb.string_to_object(ior) ;
			server = ChatServerHelper. narrow (o) ;

			server.register(cref) ;
			frm.setTitle(name + " is connected") ;

		}catch (Exception e) {
			e.printStackTrace() ;
		}
	}
	@Override
	public String getUserName() {
		System.out.println(name + " sending user name !");
		return name ;
	}

	@Override
	public void addMSG(String msg){
		System.out.println(name + " adding msg : " + msg);
		frm.appendMsg(msg) ;
	}

	@Override
	public void updateConnectedUsers(String[] names) {
		System.out.println(name + " updating connected user names !");
		frm.users.setListData(names) ;
	}

	public void sendToAll(String msg) {
		try {
			server.sendToAll(msg, cref) ;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void unRegister() {
		try {
			server.unRegister(cref) ;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}