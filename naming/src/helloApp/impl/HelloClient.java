//AVANT DE DEMARRER LE SERVER -> DEMARRER LE SERVICE DE NOMMAGE :
package helloApp.impl;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import helloApp.Hello;
import helloApp.HelloHelper;

public class HelloClient {

	static Hello hello;

	public static void main(String args[])
	{
		try{
			// create and initialize the ORB
			ORB orb = ORB.init(args, null);

			// get the root naming context
			org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
			NamingContextExt namingRef = NamingContextExtHelper.narrow(objRef);

			// resolve the Object Reference in Naming
			String name = "Hello";
			hello = HelloHelper.narrow(namingRef.resolve_str(name));

			System.out.println("Obtained a handle on server object: " + hello);
			System.out.println(hello.sayHello());

		} catch (Exception e) {
			System.out.println("ERROR : " + e) ;
			e.printStackTrace(System.out);
		}
	}


}