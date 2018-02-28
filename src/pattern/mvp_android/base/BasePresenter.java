package pattern.mvp_android.base;

public abstract class BasePresenter<T extends BaseView> {

    protected T view;

    public BasePresenter(T view) {
        this.view = view;
    }

    protected abstract void onStart();
}
