package Arthur_RMI;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;

public class ParrotImlp implements Parrot {

    @Override
    public void repete(String msg) throws RemoteException {
        System.out.println("je repete "+msg);
    }

    @Override
    public Date quelleHeureEstIl() throws RemoteException {
        Date d = new Date();
        System.out.println("j'envoie la date : " +d);
        return d;
    }

    public ParrotImlp(){
        try{
            String name ="Jacquot";
            Parrot stub = (Parrot) UnicastRemoteObject.exportObject(this,0); // 0 utilise le port par défaut : 1099
            Registry registry = LocateRegistry.createRegistry(1099); // si port changé -> le spécifier dans le client
            registry.rebind(name, stub);

            System.out.println("Parrot à l'écoute.");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ParrotImlp();
    }
}
