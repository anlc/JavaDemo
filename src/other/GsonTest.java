package other;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class GsonTest {

    private Gson mGson = new Gson();

    static class Result<T> {
        T data;
    }

    static class Bean {
        String name;
    }

    public void print(String json) {
        Result<Bean> result = format(json, new TypeToken<Result<Bean>>(){});
        System.out.println(result.data.name);
    }

    public <T> T format(String json, TypeToken typeToken) {
        return mGson.fromJson(json, typeToken.getType());
    }


    public static void main(String[] args) {

        Result<Bean> result = new Result<>();
        Bean bean = new Bean();
        bean.name = "test";
        result.data = bean;


        String json = new Gson().toJson(result);
        System.out.println(json);
        new GsonTest().print(json);
    }

}
