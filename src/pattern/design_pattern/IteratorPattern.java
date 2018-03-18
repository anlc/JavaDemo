package pattern.design_pattern;

/**
 * Created by n on 2018/3/18.
 * 参考：
 * http://www.runoob.com/design-pattern/iterator-pattern.html
 *
 * 源码参考：java中的list，map。android中的SQLiteDatabase的query返回cursor
 */
public class IteratorPattern {

    interface Iterator {
        boolean hasNext();

        Object next();
    }

    interface Container {
        Iterator getIterator();
    }

    static class NameRepository implements Container {
        public String names[] = {"123", "321", "789"};

        @Override
        public Iterator getIterator() {
            return new NameIterator();
        }

        class NameIterator implements Iterator {

            int index;

            @Override
            public boolean hasNext() {
                if (index < names.length) {
                    return true;
                }
                return false;
            }

            @Override
            public Object next() {
                if (hasNext()) {
                    return names[index++];
                }
                return null;
            }
        }
    }

    public static void main(String[] args) {
        NameRepository repository = new NameRepository();
        Iterator iterator = repository.getIterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
