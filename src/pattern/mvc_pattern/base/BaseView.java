package pattern.mvc_pattern.base;

public interface BaseView {

    void setPresenter(BasePresenter presenter);

    void showMsg(String data);
}
