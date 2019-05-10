package CallbackApp;

import callbacks.CallbackClient;
import callbacks.CallbackServerPOA;
import org.omg.CORBA.ORB;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import java.io.PrintWriter;

public class CallbackServerImpl extends CallbackServerPOA {

    private ORB orb ;
	private CallbackClient client = null ;

	public CallbackServerImpl(String[] args) {
		try{
			// Initialisation et démarrage de l'ORB :
			// l'appel à orb.run() étant bloquant, on l'exécute dans un Thread.
			orb = ORB. init (args, null);
			Thread orbThread = new Thread(new Runnable() {
				@Override
				public void run() {
					orb.run(); // !!! bloquant !!! donc dans un thread
				}
			});
			orbThread.start();

			// Mise à disposition de l'objet CORBA serveur
			POA rootPOA = POAHelper. narrow (orb.resolve_initial_references("RootPOA"));
			rootPOA.the_POAManager().activate() ;
			byte[] id = rootPOA.activate_object(this) ;

			// Ecriture de l'IOR du serveur dans un fichier (a destination des clients)
			org.omg.CORBA.Object ref = rootPOA.id_to_reference(id) ;
			String ior = orb.object_to_string(ref) ;
			System. out .println(ior) ;
			PrintWriter file = new PrintWriter("server_ior.txt") ;
			file.println(ior) ;
			file.close() ;

		}catch (Exception e) {
			e.printStackTrace() ;
		}
	}
	@Override
	public void sayHello() {
		System. out .println("Le serveur dit Hellooooo !");
		client.showYourName() ;
	}
	@Override
	public void register(CallbackClient client) {
		this.client = client ;
		System. out .println("Enregistrement de " + client.getName());
	}
	public static void main(String[] args) {
		new CallbackServerImpl(args) ;
	}
}