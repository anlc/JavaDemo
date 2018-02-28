package pattern.mvp_pattern;

import pattern.mvp_pattern.base.BaseModel;

public class UserModel implements BaseModel {
    @Override
    public String getData() {
        return "this is msg";
    }
}
