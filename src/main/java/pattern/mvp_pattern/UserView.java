package pattern.mvp_pattern;

import pattern.mvp_pattern.base.BasePresenter;
import pattern.mvp_pattern.base.BaseView;

public class UserView implements BaseView {

    UserPresenter presenter;

    @Override
    public void setPresenter(BasePresenter presenter) {
        this.presenter = (UserPresenter) presenter;
    }

    @Override
    public void showMsg(String data) {
        System.out.println("show data: " + data);
    }
}
