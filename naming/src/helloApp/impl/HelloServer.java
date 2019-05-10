
/*
 * AVANT DE DEMARRER LE SERVER -> DEMARRER LE SERVICE DE NOMMAGE :
 * orbd -ORBInitialPort 1050
 *
 * POUR LANCER LE SERVEUR, NE PAS OUBLIER LES PARAMETRES DU PROGRAMME :
 * java HelloServer -ORBInitialPort 1050 -ORBInitialHost localhost
 *
 * POUR LANCER LE CLIENT, NE PAS OUBLIER LES PARAMETRES DU PROGRAMME :
 * java HelloClient -ORBInitialPort 1050 -ORBInitialHost localhost
 *
 */

package helloApp.impl;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import helloApp.Hello;
import helloApp.HelloHelper;

public class HelloServer {

	public static void main(String[] args) {
		try{
			// create and initialize the ORB
			ORB orb = ORB.init(args, null);

			// get reference to rootpoa & activate the POAManager
			POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			rootpoa.the_POAManager().activate();

			// create servant and register it with the ORB
			HelloImpl helloImpl = new HelloImpl();

			// get object reference from the servant
			org.omg.CORBA.Object ref = rootpoa.servant_to_reference(helloImpl);
			Hello href = HelloHelper.narrow(ref);

			// get the root naming context
			// NameService invokes the name service
			org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
			NamingContextExt namingRef = NamingContextExtHelper.narrow(objRef);

			// bind the Object Reference in Naming
			String name = "Hello";
			NameComponent path[] = namingRef.to_name( name );
			namingRef.rebind(path, href);

			System.out.println("HelloServer ready and waiting ...");

			// wait for invocations from clients
			orb.run();
		}

		catch (Exception e) {
			System.err.println("ERROR: " + e);
			e.printStackTrace(System.out);
		}

		System.out.println("HelloServer Exiting ...");

	}
}