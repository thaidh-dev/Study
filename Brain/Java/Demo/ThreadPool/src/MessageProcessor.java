public class MessageProcessor implements Runnable {
    private int message;

    public MessageProcessor(int message) {
        this.message = message;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " [RECEIVED] Message = " + message);
        respondToMessage();
        System.out.println(Thread.currentThread() + " (DONE) Processing Message = " + message);
    }

    public void respondToMessage() {
        try {
            Thread.sleep(3000);
        }
        catch (Exception e) {
            System.out.println(e);
            System.out.println("Unable to process message " + message);
        }
    }
}
