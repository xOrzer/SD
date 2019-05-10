package CallbackApp;

import callbacks.*;
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

public class CallbackClientImpl extends CallbackClientPOA {
	private String name = null ;
	private ORB orb ;
	private CallbackServer server ;
	private CallbackClient cref ;
	private JFrame frm = new JFrame() ;
	private JButton b ;
	public CallbackClientImpl(String name, String[] args) {
		try{
			this.name = name ;

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
			cref = CallbackClientHelper. narrow (ref);

			// lecture de l'IOR du serveur
			BufferedReader br = new BufferedReader(new FileReader("server_ior.txt")) ;
			String ior = br.readLine() ;
			br.close() ;

			// récupération de la référence sur le serveur (à partir de son IOR)
			org.omg.CORBA.Object o = orb.string_to_object(ior) ;
			server = CallbackServerHelper. narrow (o) ;

			server.register(cref) ;

			buildUI() ;

		}catch (Exception e) {
			e.printStackTrace() ;
		}
	}
	@Override
	public String getName() {
		return name ;
	}
	@Override
	public void showYourName() {
		System. out .println("My name is " + name);
		frm.setTitle(name) ;
		b.setText(name) ;
	}
	private void buildUI() {
		frm.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System. out .println("Bye bye");
				System. exit (0) ;
			}
		}) ;
		b = new JButton("!!! click !!!") ;
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				server.sayHello() ;
			}
		}) ;
		frm.getContentPane().add(b) ;
		frm.setSize(300, 200) ;
		frm.setVisible(true) ;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System. out .println("Client demarre");
		new CallbackClientImpl("Greg", args) ;
	}
}