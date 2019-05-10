package thread_heritage;

public class RunnableTest implements Runnable {
    private long delay;

    public RunnableTest(long delay){
        this.delay = delay;
    }

    @Override
    public void run(){
        for (int i = 0; i <= 10 ; i++) {
            System.out.println(Thread.currentThread().getName() + " : " + i);

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
        Thread t1 = new Thread(new RunnableTest(300)), t2 = new Thread(new RunnableTest(100));
        t1.start();
        t2.start();
    }
}
