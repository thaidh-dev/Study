package CallableAndFuture;

import java.util.concurrent.Callable;

public class Calculator implements Callable<Integer>{
    private int a;
    private int b;
 
    public Calculator(int a, int b) {
        this.a = a;
        this.b = b;
    }
    
    @Override
    public Integer call() throws Exception {
        return a + b;
    }
    
}
