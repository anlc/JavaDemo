package reflact;

import org.junit.Test;

import java.lang.reflect.Field;

public class MvpReflectDemo {

    interface IPresenterFactory {
        <T, VIEW> T get(Class<T> clazz, VIEW view);
    }

    static class PresenterFactory implements IPresenterFactory {
        @Override
        public <T, VIEW> T get(Class<T> clazz, VIEW view) {
            try {
                T instance = clazz.newInstance();
                Field field = instance.getClass().getDeclaredField("view");
                field.setAccessible(true);
                field.set(instance, view);

                return instance;

            } catch (InstantiationException | IllegalAccessException | NoSuchFieldException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    interface IHomePresenter {
        void showText();

        IHomeView getView();
    }

    static class HomePresenter implements IHomePresenter {

        IHomeView view;

        @Override
        public void showText() {
            getView().show();
        }

        @Override
        public IHomeView getView() {
            return view;
        }
    }

    interface IHomeView {
        void show();
    }

    static class HomeView implements IHomeView {

        @Override
        public void show() {
            System.out.printf("show method");
        }
    }

    @Test
    public void testNewInstance() {
        IHomeView homeView = new HomeView();

        IPresenterFactory factory = new PresenterFactory();
        IHomePresenter presenter = factory.get(HomePresenter.class, homeView);
        presenter.showText();
    }
}
