package lab.pkg6;

public class TestThread {
    public static void main(String[] args) {
        EvenThread e = new EvenThread();
        OddThread o = new OddThread();
        o.start();
        try {
            o.join();
        } 
        catch (Exception ex) {
        }
        e.start();
    }
}

class EvenThread extends Thread{
    @Override
    public void run() {
        for (int i = 0; i <= 10; i += 2) {
            System.out.print(i + " ");
            try {
                Thread.sleep(15);
            }
            catch (Exception e) {
            }
        }
    }
}

class OddThread extends Thread{
    @Override
    public void run() {
        for (int i = 1; i < 10; i += 2) {
            System.out.print(i + " ");
            try {
                Thread.sleep(10);
            }
            catch (Exception e) {
            }
        }
    }
}
