
import static java.lang.Thread.currentThread;
import java.util.Scanner;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Java Program to demonstrate how to stop a thread in Java. There is a stop()
 * method in Thread class but its deprecated because of deadlock and other
 * issue, but its easy to write your own stop() method to stop a thread in Java.
 *
 * @author java67
 */
public class ThreadStopDemo {

    static boolean a = true;

    public static void lol() {
        try {
            while (a == true) {
                Thread.sleep(5000);
                
                if (a == true) {
                    System.out.println("alo alo");                    
                }
                //break;
            }
        } catch (InterruptedException ex) {
            //System.out.println(ex);
        }

    }

    public static void main(String args[]) throws InterruptedException {
        Thread t = new Thread() {
            @Override
            public void run() {
                lol();
            }
        };

        Scanner s = new Scanner(System.in);
        while (true) {
            String start = s.nextLine();
            if (start.equals("start")) {
                t.start();
            }
            if (start.equals("stop")) {
                try {
                    t.interrupt();
                }
                catch (Exception ex) {
                }
                a = false;
                break;
            }
        }
    }
}
