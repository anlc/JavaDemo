package other;

public class EnumTest {

    enum PlayMode {
        SINGLE_LOOP,
        LIST_LOOP,
        SINGLE;

        public static PlayMode fromOrdinal(int ordinal) {
            for (PlayMode mode : PlayMode.values()) {
                if (mode.ordinal() == ordinal) {
                    return mode;
                }
            }
            return null;
        }
    }

    public static void main(String[] args) {
        int ordinal = PlayMode.LIST_LOOP.ordinal();

        for (int i = 0; i < 10; i++) {
            ordinal += 1;
            if (ordinal == PlayMode.values().length) {
                ordinal = 0;
            }

            PlayMode next = PlayMode.fromOrdinal(ordinal);

            System.out.println(ordinal);
            System.out.println(next.name());
        }
    }
}
