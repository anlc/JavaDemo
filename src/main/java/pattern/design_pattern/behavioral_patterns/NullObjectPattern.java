package pattern.design_pattern.behavioral_patterns;

import org.junit.Test;

/**
 * 空对象模式
 *
 * 参考：http://www.runoob.com/design-pattern/null-object-pattern.html
 */
public class NullObjectPattern {

    static abstract class AbstractCustomer {
        protected String name;

        abstract boolean isNil();

        abstract String getName();
    }

    static class RealCustomer extends AbstractCustomer {

        public RealCustomer(String name) {
            this.name = name;
        }

        @Override
        boolean isNil() {
            return false;
        }

        @Override
        String getName() {
            return name;
        }
    }

    static class NullCustomer extends AbstractCustomer {

        @Override
        boolean isNil() {
            return true;
        }

        @Override
        String getName() {
            return "Not Available in Customer Database";
        }
    }

    static class CustomerFactory {
        static final String[] names = {"Rob", "Joe", "Julie"};

        static AbstractCustomer getCustomer(String name) {
            for (int i = 0; i < names.length; i++) {
                if (names[i].equalsIgnoreCase(name)) {
                    return new RealCustomer(name);
                }
            }
            return new NullCustomer();
        }
    }

    @Test
    public void testPattern(){
        AbstractCustomer customer1 = CustomerFactory.getCustomer("Rob");
        AbstractCustomer customer2 = CustomerFactory.getCustomer("Bob");
        AbstractCustomer customer3 = CustomerFactory.getCustomer("Julie");
        AbstractCustomer customer4 = CustomerFactory.getCustomer("Laura");

        System.out.println("Customers");
        System.out.println(customer1.getName());
        System.out.println(customer2.getName());
        System.out.println(customer3.getName());
        System.out.println(customer4.getName());
    }
}
