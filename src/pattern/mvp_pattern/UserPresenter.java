package pattern.mvp_pattern;

import pattern.mvp_pattern.base.BasePresenter;

public class UserPresenter implements BasePresenter {

    UserModel model;
    UserView view;

    public UserPresenter(UserModel model, UserView view) {
        this.model = model;
        this.view = view;
    }

    public void start() {
        view.showMsg(model.getData());
    }

    @Override
    public void deleteData(String data) {
        String result = model.getData().replace(data, "");
        view.showMsg(result);
    }
}
