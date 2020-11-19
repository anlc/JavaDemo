package annotation.demo;

import java.lang.reflect.Method;
import java.util.List;

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

    private static final int STATE_DEFAULT = 0;
    private static final int STATE_INI = 1 << 1;
    private static final int STATE_PREVIEW = 1 << 2;
    private static final int STATE_RECORD_AUDIO = 1 << 3;
    private static final int STATE_RECORD_IMAGE = 1 << 4;
    private static final int STATE_RECORD_AUDIO_STOPPED = 1 << 5;
    private static final int STATE_RECORD_IMAGE_STOPPED = 1 << 6;
    private static final int STATE_RELEASED = 1 << 7;
    private static final int STATE_RELEASED1 = 1 << 8;
    private static final int STATE_RELEASED2 = 1 << 9;
    private static final int STATE_RELEASED3 = 1 << 10;

    private static int state = 0;


    public static void main(String[] args) {
//        List<Integer> useCase = IntStream.range(47, 51).boxed().collect(Collectors.toList());
//        trackUseCases(useCase, PasswordUtils.class);

        System.out.println("state: " + Integer.toBinaryString(state));
        state |= STATE_INI;
        System.out.println("state: " + Integer.toBinaryString(state));
        state |= STATE_PREVIEW;
        System.out.println("state: " + Integer.toBinaryString(state));
        state |= STATE_RECORD_AUDIO;
        System.out.println("state: " + Integer.toBinaryString(state));
        state |= STATE_RECORD_IMAGE;
        System.out.println("state: " + Integer.toBinaryString(state));
        state |= STATE_RECORD_AUDIO_STOPPED;
        System.out.println("state: " + Integer.toBinaryString(state));
        state |= STATE_RECORD_IMAGE_STOPPED;
        System.out.println("state: " + Integer.toBinaryString(state));
        state |= STATE_RELEASED;
        System.out.println("state: " + Integer.toBinaryString(state));
        state |= STATE_RELEASED1;
        System.out.println("state: " + Integer.toBinaryString(state));
        state |= STATE_RELEASED2;
        System.out.println("state: " + Integer.toBinaryString(state));
        state |= STATE_RELEASED3;
        System.out.println("state: " + Integer.toBinaryString(state));
    }

}
