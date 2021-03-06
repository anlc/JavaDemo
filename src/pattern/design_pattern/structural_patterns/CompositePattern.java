package pattern.design_pattern.structural_patterns;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 组合模式 / 部分整体模式
 *
 * 参考：http://www.runoob.com/design-pattern/composite-pattern.html
 * 源码参考：View 和 ViewGroup 的嵌套组合
 */
public class CompositePattern {

    class Employee {
        String name;
        String dept;
        int salary;
        List<Employee> subordinates;

        Employee(String name, String dept, int sal) {
            this.name = name;
            this.dept = dept;
            this.salary = sal;
            subordinates = new ArrayList<>();
        }

        void add(Employee e) {
            subordinates.add(e);
        }

        void remove(Employee e) {
            subordinates.remove(e);
        }

        List<Employee> getSubordinates() {
            return subordinates;
        }

        public String toString() {
            return ("Employee :[ Name : " + name
                    + ", dept : " + dept + ", salary :"
                    + salary + " ]");
        }
    }

    @Test
    public void testPattern(){
        Employee CEO = new Employee("John","CEO", 30000);
        Employee headSales = new Employee("Robert","Head Sales", 20000);
        Employee headMarketing = new Employee("Michel","Head Marketing", 20000);

        Employee clerk1 = new Employee("Laura","Marketing", 10000);
        Employee clerk2 = new Employee("Bob","Marketing", 10000);
        Employee salesExecutive1 = new Employee("Richard","Sales", 10000);
        Employee salesExecutive2 = new Employee("Rob","Sales", 10000);

        CEO.add(headSales);
        CEO.add(headMarketing);

        headSales.add(salesExecutive1);
        headSales.add(salesExecutive2);

        headMarketing.add(clerk1);
        headMarketing.add(clerk2);

        System.out.println(CEO);
        System.out.println();
        for (Employee headEmployee : CEO.getSubordinates()) {
            System.out.println(headEmployee);
            for (Employee employee : headEmployee.getSubordinates()) {
                System.out.println(employee);
            }
            System.out.println();
        }
    }
}
