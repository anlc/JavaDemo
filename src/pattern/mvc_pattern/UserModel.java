package pattern.mvc_pattern;

import pattern.mvc_pattern.base.BaseModel;

public class UserModel implements BaseModel {
    @Override
    public String getData() {
        return "this is msg";
    }
}
