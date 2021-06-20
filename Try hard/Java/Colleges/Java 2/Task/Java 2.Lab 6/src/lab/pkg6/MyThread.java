package lab.pkg6;

public class MyThread implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                System.out.print(i + " ");
                Thread.sleep(500);
            }
            catch (Exception e) {
                break;
            }
        }
    }
     
    public static void main(String[] args) {
        Thread a = new Thread(new MyThread());
        a.start();
        
        MyThread thread1 = new MyThread();
        Thread a1 = new Thread(thread1);
        a1.setPriority(Thread.MAX_PRIORITY);
        a1.start();

        MyThread thread2 = new MyThread();
        Thread a2 = new Thread(thread2);
        a2.setPriority(Thread.MIN_PRIORITY);
        a2.start();
    }
}


