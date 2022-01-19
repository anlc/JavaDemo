package json;

import com.google.gson.Gson;
import java.lang.reflect.Field;

public class JsonTest {

    public static void main(String[] args) {

        ClassBean beans = newInstance();

        String source = new Gson().toJson(beans);
        System.out.println(source);

        long start = System.currentTimeMillis();
        ClassBean bean = new JsonParser().parser(source, ClassBean.class);
        System.out.println("time: " + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        ClassBean bean2 = new Gson().fromJson(source, ClassBean.class);
        System.out.println(" G son time :" + (System.currentTimeMillis() - start));

        System.out.println(bean == null ? "null" : bean);
        System.out.println(bean2);
    }

    private static ClassBean newInstance() {

        try {
            ClassBean clazz = ClassBean.class.newInstance();
            Field[] fields = ClassBean.class.getDeclaredFields();

            for (Field item : fields) {
                StringDef value = item.getAnnotation(StringDef.class);
                item.setAccessible(true);
                item.set(clazz, value.value());
            }
            return clazz;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
