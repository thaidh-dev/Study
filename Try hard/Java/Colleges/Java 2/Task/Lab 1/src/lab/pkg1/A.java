package lab.pkg1;

class A {
    protected int x, y;
    
    public A(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public A(int x) {
        this(x, x);
    }
    
    public void print() {
        System.out.printf("x = %d, y = %d\n", x, y);
    }
}

class B extends A {
    int z;
    
    public B(int x, int y, int z) {
        super(x, y);
        this.z = z;
    }
    
    public void print() {
        System.out.printf("x = %d, y = %d, z = %d\n", x, y, z);
    }
}
    
class C {

    public static void main(String[] args) {
        A o1 = new A(5);
        A o2 = new A(5, 9);
        B o3 = new B(5, 6, 7);
        o1.print();
        o2.print();
        o3.print();
    }
}

