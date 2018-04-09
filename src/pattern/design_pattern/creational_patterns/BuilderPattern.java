package pattern.design_pattern.creational_patterns;

import org.junit.Test;

/**
 * 建造者模式
 */
public class BuilderPattern {

    static class Dialog {

        Build build;

        private Dialog(Build build) {
            this.build = build;
        }

        public void show() {
            System.out.println("msg:" + build.msg + "  title:" + build.title);
        }

        static class Build {
            String msg;
            String title;

            public Build() {
                msg = "default msg";
                title = "default title";
            }

            public Build msg(String msg) {
                this.msg = msg;
                return this;
            }

            public Build title(String title) {
                this.title = title;
                return this;
            }

            Dialog build() {
                return new Dialog(this);
            }
        }
    }

    @Test
    public void testPattern() {
        new Dialog.Build().build().show();
        new Dialog.Build().title("new title").build().show();
    }
}
