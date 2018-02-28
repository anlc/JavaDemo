package pattern.mvc_pattern;

import pattern.mvc_pattern.base.BasePresenter;
import pattern.mvc_pattern.base.BaseView;

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
