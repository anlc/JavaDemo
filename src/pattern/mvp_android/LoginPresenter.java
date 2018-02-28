package pattern.mvp_android;

import pattern.mvp_android.base.BasePresenter;

public class LoginPresenter extends BasePresenter<LoginView> {

    public LoginPresenter(LoginView view) {
        super(view);
    }

    @Override
    protected void onStart() {
        System.out.println("start load data");

        view.getButton().onClick();
    }
}
