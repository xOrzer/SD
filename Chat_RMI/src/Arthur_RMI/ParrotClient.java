package Arthur_RMI;

import java.rmi.Naming;

public class ParrotClient {

    public ParrotClient(){
        try{
            Parrot p = (Parrot) Naming.lookup("rmi://127.0.0.1/Jacquot");
            // on peut aussi : LocateRegistry.getRegistry().lookup("rmi://localhost/Jacquot") ;
            // en cas de changement de numéro de port : Naming.lookup("rmi://localhost:1099/Jacquot") ;

            p.repete("Yoo man !");
            System.out.println("Chez mon perroquet il est : "+p.quelleHeureEstIl());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ParrotClient();
    }
}
