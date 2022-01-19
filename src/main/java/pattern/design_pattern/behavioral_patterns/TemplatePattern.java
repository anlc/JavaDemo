package pattern.design_pattern.behavioral_patterns;

import org.junit.Test;

/**
 * 模板模式
 *
 * 参考：http://www.runoob.com/design-pattern/template-pattern.html
 * 源码参考：AsyncTask （Android源码设计模式解析与实战 281页）
 */
public class TemplatePattern {

    abstract class Game {
        abstract void init();

        abstract void startPlay();

        abstract void endPlay();

        public final void play() {
            init();
            startPlay();
            endPlay();
        }
    }

    class Cricket extends Game {

        @Override
        void init() {
            System.out.println("Cricket Game Finished!");
        }

        @Override
        void startPlay() {
            System.out.println("Cricket Game Initialized! Start playing.");
        }

        @Override
        void endPlay() {
            System.out.println("Cricket Game Started. Enjoy the game!");
        }
    }

    class FootBall extends Game {

        @Override
        void init() {
            System.out.println("Football Game Finished!");
        }

        @Override
        void startPlay() {
            System.out.println("Football Game Initialized! Start playing.");
        }

        @Override
        void endPlay() {
            System.out.println("Football Game Started. Enjoy the game!");
        }
    }

    @Test
    public void testPattern() {
        new Cricket().play();
        System.out.println();
        new FootBall().play();
    }

}
