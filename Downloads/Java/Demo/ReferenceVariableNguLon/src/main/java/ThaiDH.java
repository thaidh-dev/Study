import java.util.ArrayList;
import java.util.List;

public class ThaiDH {
    private int age;
    private int weight;
    private Integer height;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public ThaiDH(int age, int weight, Integer height) {
        this.age = age;
        this.weight = weight;
        this.height = height;
    }

    public ThaiDH() {
    }

//    public static void main(String[] args) {
//        ThaiDH t = new ThaiDH(24, 65, 165);
//        ThaiDH t2 = new ThaiDH();
//        ThaiDH t3 = new ThaiDH(24, 55, 160);
//        t2 = t;
//        t.setWeight(70);
//        t2.setWeight(60);
//        t = t3;
//        System.out.println("");
//    }

    public static void main(String[] args) {
        ThaiDH t = new ThaiDH(24, 65, 165);
        ThaiDH t2 = new ThaiDH(20, 55, 160);
        List<ThaiDH> list = new ArrayList<>();
        list.add(t);
        t.setAge(30);
        t = t2;
        t2.setAge(111);
        System.out.println("");
    }
}