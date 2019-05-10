package test_synchronized;

import Threads.ThreadTest;

public class Test_SansSynchronized {
    private static double taux=0.05;

    static Double solde = new Double(0);

    public void crediter(double val){
        print("Solde avant crédit : " + solde);
        try {
            print("Fait un gros calcul et va créditer le compte de : " + val);
            Thread.sleep(4000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        solde += val;
        print("Solde après crédit : " + solde);
    }

    public void calculInterets(){
        print("Calcul des interets sur solde à : " + solde);
        solde = solde + solde * taux;
        print("Solde apres interets : " + solde);
    }

    public static void print(String msg){
        System.out.println(Thread.currentThread().getName() + " : " + msg);
    }

    public static void main(String[] args) throws Exception{
        final Test_SansSynchronized t = new Test_SansSynchronized();

        Runnable runA = new Runnable() {
            public void run(){
                t.crediter(1000);
            }
        };

        Thread ta = new Thread(runA, "Site internet");
        System.out.println("Lancement du credit par internet");
        ta.start();

        Thread.sleep(500);

        Runnable runB = new Runnable() {
            public void run() {
                t.calculInterets();
            }
        };

        Thread tb = new Thread(runB, "Agence");
        System.out.println("Lancement calcul des interets");
        tb.start();
    }
}
