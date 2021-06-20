package lab.pkg2;

public abstract class Employee {
    private String fullname;
    
    public Employee(String fullname) {
        this.fullname = fullname;
    }
    
    public abstract double getSalary();
    
    public void print() {
        System.out.println("Fullname: "+this.fullname);
        System.out.println("Salary: "+this.getSalary());
    }
}

class EmployeeManager {
    
    public static void main(String[] args) {
        Employee e1 = new Employee("Thái") {
            @Override
            public double getSalary() {
                return 7000000;
            }
        };
        e1.print();
        
        Employee e2 = new Employee("Nam") {
            @Override
            public double getSalary() {
                return 5000000;
            }
        };
        e2.print();
    }
}
