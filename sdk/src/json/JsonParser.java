package json;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class JsonParser {

    public <T> T parser(String string, Class<T> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        List<String> names = new ArrayList<>();
        List<String> values = new ArrayList<>();
        for (Field field : fields) {
            String name = field.getName();
            names.add(name);
        }

        StringBuilder key = new StringBuilder();
        StringBuilder value = new StringBuilder();

        boolean isReadValue = false;
        for (char c : string.toCharArray()) {
            if (Character.isLowerCase(c) || Character.isUpperCase(c)) {
                if (isReadValue) {
                    value.append(c);
                } else {
                    key.append(c);
                }
            } else {
                key.replace(0, key.length(), "");
                if (isReadValue && value.length() != 0) {
                    values.add(value.toString());
                    value.replace(0, value.length(), "");
                    isReadValue = false;
                }
            }

            if (names.contains(key.toString())) {
                isReadValue = true;
            }
        }

        T t = null;
        try {
            t = clazz.newInstance();
            int index = -1;
            for (String item : names) {
                index++;
                Field field = t.getClass().getDeclaredField(item);
                field.setAccessible(true);
                if (index < values.size()) {
                    field.set(t, values.get(index));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }
}
