package pattern.mvp_pattern;

public class MainClass {

    public static void main(String[] args){
        UserModel userModel = new UserModel();
        UserView userView = new UserView();
        UserPresenter presenter = new UserPresenter(userModel, userView);
        presenter.start();
        presenter.deleteData("this ");
    }
}
