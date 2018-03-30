package pattern.design_pattern;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 过滤器模式 / 标准模式
 *
 * 参考：http://www.runoob.com/design-pattern/filter-pattern.html
 */
public class FilterPattern {

    interface Criteria {
        List<Person> meetCriteria(List<Person> persons);
    }

    class Person {
        String name;
        String gender;
        String maritalStatus;

        public Person(String name, String gender, String maritalStatus) {
            this.name = name;
            this.gender = gender;
            this.maritalStatus = maritalStatus;
        }
    }

    class CriteriaSingle implements Criteria {

        @Override
        public List<Person> meetCriteria(List<Person> persons) {
            List<Person> singleCriteria = new ArrayList<>();
            for (Person person : persons) {
                if (person.maritalStatus.equalsIgnoreCase("single")) {
                    singleCriteria.add(person);
                }
            }
            return singleCriteria;
        }
    }

    class CriteriaFemale implements Criteria {

        @Override
        public List<Person> meetCriteria(List<Person> persons) {
            List<Person> femaleCriteria = new ArrayList<>();
            for (Person person : persons) {
                if (person.gender.equalsIgnoreCase("female")) {
                    femaleCriteria.add(person);
                }
            }
            return femaleCriteria;
        }
    }

    class CriteriaMale implements Criteria {

        @Override
        public List<Person> meetCriteria(List<Person> persons) {
            List<Person> femaleCriteria = new ArrayList<>();
            for (Person person : persons) {
                if (person.gender.equalsIgnoreCase("male")) {
                    femaleCriteria.add(person);
                }
            }
            return femaleCriteria;
        }
    }

    class AndCriteria implements Criteria {

        private Criteria criteria;
        private Criteria otherCriteria;

        public AndCriteria(Criteria criteria, Criteria otherCriteria) {
            this.criteria = criteria;
            this.otherCriteria = otherCriteria;
        }

        @Override
        public List<Person> meetCriteria(List<Person> persons) {
            List<Person> firstCriteria = criteria.meetCriteria(persons);
            return otherCriteria.meetCriteria(firstCriteria);
        }
    }

    class OrCriteria implements Criteria {

        private Criteria criteria;
        private Criteria otherCriteria;

        public OrCriteria(Criteria criteria, Criteria otherCriteria) {
            this.criteria = criteria;
            this.otherCriteria = otherCriteria;
        }

        @Override
        public List<Person> meetCriteria(List<Person> persons) {
            List<Person> firstCriteriaItems = criteria.meetCriteria(persons);
            List<Person> otherCriteriaItems = otherCriteria.meetCriteria(persons);
            for (Person person : otherCriteriaItems) {
                if (!firstCriteriaItems.contains(person)) {
                    firstCriteriaItems.add(person);
                }
            }
            return firstCriteriaItems;
        }
    }

    @Test
    public void testPattern() {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("Robert","Male", "Single"));
        persons.add(new Person("John","Male", "Married"));
        persons.add(new Person("Laura","Female", "Married"));
        persons.add(new Person("Diana","Female", "Single"));
        persons.add(new Person("Mike","Male", "Single"));
        persons.add(new Person("Bobby","Male", "Single"));

        Criteria male = new CriteriaMale();
        Criteria female = new CriteriaFemale();
        Criteria single = new CriteriaSingle();
        Criteria singleMale = new AndCriteria(single, male);
        Criteria singleOrFemale = new OrCriteria(single, female);

        System.out.println("Males: ");
        printPersons(male.meetCriteria(persons));

        System.out.println("\nFemales: ");
        printPersons(female.meetCriteria(persons));

        System.out.println("\nSingle Males: ");
        printPersons(singleMale.meetCriteria(persons));

        System.out.println("\nSingle Or Females: ");
        printPersons(singleOrFemale.meetCriteria(persons));
    }

    public static void printPersons(List<Person> persons){
        for (Person person : persons) {
            System.out.println("Person : [ Name : " + person.name
                    +", Gender : " + person.gender
                    +", Marital Status : " + person.maritalStatus
                    +" ]");
        }
    }

}
