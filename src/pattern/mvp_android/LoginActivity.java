package pattern.mvp_android;

import pattern.mvp_android.base.BaseActivity;
import pattern.mvp_android.test.Button;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginView {

    Button button;

    public LoginActivity() {
        button = new Button();
        presenter = new LoginPresenter(this);
        presenter.onStart();
    }

    @Override
    public Button getButton() {
        return button;
    }


    public static void main(String[] args) {
        new LoginActivity();
    }
}
