package pattern.mvp_android.base;

public abstract class BaseActivity<T extends BasePresenter> implements BaseView {

    protected T presenter;
}
