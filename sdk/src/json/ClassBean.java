package json;

@ClassDef
public class ClassBean {

    @StringDef("abc")
    private String name;

    @StringDef("name is abc")
    private String des;

    public ClassBean() {
    }

    public ClassBean(String name, String des) {
        this.name = name;
        this.des = des;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    @Override
    public String toString() {
        return "ClassBean{" +
                "name='" + name + '\'' +
                ", des='" + des + '\'' +
                '}';
    }
}
