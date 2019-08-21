package annotation.demo;

import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class UseCaseTracker {

    private static void trackUseCases(List<Integer> useCases, Class<?> cl){
        System.out.println(useCases);
        for (Method m : cl.getDeclaredMethods()) {
            UseCase uc = m.getAnnotation(UseCase.class);
            if (uc != null) {
                System.out.println(uc.id() + " :: " + uc.description());
                useCases.remove(Integer.valueOf(uc.id()));
            }
        }
        System.out.println(useCases);
    }


    public static void main(String[] args) {
        List<Integer> useCase = IntStream.range(47, 51).boxed().collect(Collectors.toList());
        trackUseCases(useCase, PasswordUtils.class);
    }

}
