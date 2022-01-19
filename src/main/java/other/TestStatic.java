package other;

public class TestStatic {

    public interface OnChangeListener {
        void onChange();
    }

    static class AppContext {
        OnChangeListener onChangeListener;

        void registerListener(OnChangeListener onChangeListener) {
            this.onChangeListener = onChangeListener;
        }

        void change(){
            onChangeListener.onChange();
        }
    }

    public static void init(AppContext context) {
        context.registerListener(new OnChangeListener() {
            @Override
            public void onChange() {
                System.out.println("onChange");
            }
        });
    }

    public static void main(String[] args) {
        AppContext context = new AppContext();
        init(context);
        context.change();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        context.change();
    }
}
