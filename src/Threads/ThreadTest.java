package Threads;

public class ThreadTest extends Thread {
    private long delay;

    public ThreadTest(long delay){
        this.delay = delay;
    }

    @Override
    public void run(){
        for (int i = 0; i <= 10 ; i++) {
            System.out.println(getName()+ " : " + i);

            //Ralentir la boucle..
            try{
                Thread.sleep(delay);
            } catch(InterruptedException e){
                //TODO auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args){
        Thread t1 = new ThreadTest(300), t2 = new ThreadTest(100);
        t1.start();
        t2.start();
    }
}
