package nhap;

public class Xe {

    Lop l;

    public Xe() {
        l = new Lop("alo");
    }

    public Xe(Lop l) {
        this.l = l;
    }

    public void getLop() {
        l.getLop();
    }
}
