package nhap;

public class BienVolatile {

  private volatile static int COUNT = 0;
//    private static int COUNT = 0;

    public static void main(String[] args) {
        new ChangeListener().start();
        new ChangeMaker().start();
    }

    static class ChangeListener extends Thread {
        @Override
        public void run() {
            int value = COUNT;
            // Khi giá trị của biến COUNT nhỏ hơn 5, thread này sẽ lặp mãi mãi để kiểm tra giá trị của biến này
            while (value < 5) {
                
                if (value != COUNT) {
                    System.out.println("Count variable changed to : " + COUNT);
                    value = COUNT;
                }
            }
            System.out.println(COUNT);
        }
    }

    static class ChangeMaker extends Thread {
        @Override
        public void run() {
            int value = COUNT;
            while (COUNT < 5) {
                System.out.println("Increasing value of count variable to " + (value + 1));
                COUNT = ++value;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
