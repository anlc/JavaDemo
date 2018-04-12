package pattern.design_pattern.behavioral_patterns;

import org.junit.Test;

/**
 * 访问者模式
 *
 * 参考：http://www.runoob.com/design-pattern/visitor-pattern.html
 */
public class VisitorPattern {

    interface ComputerPart {
        void accept(ComputerPartVisitor computerPartVisitor);
    }

    class Keyboard implements ComputerPart {

        @Override
        public void accept(ComputerPartVisitor computerPartVisitor) {
            computerPartVisitor.visit(this);
        }
    }

    class Mouse implements ComputerPart {

        @Override
        public void accept(ComputerPartVisitor computerPartVisitor) {
            computerPartVisitor.visit(this);
        }
    }

    class Computer implements ComputerPart {

        ComputerPart[] parts = {new Keyboard(), new Mouse()};

        @Override
        public void accept(ComputerPartVisitor computerPartVisitor) {
            for (int i = 0; i < parts.length; i++) {
                parts[i].accept(computerPartVisitor);
            }
            computerPartVisitor.visit(this);
        }
    }

    interface ComputerPartVisitor {
        void visit(Keyboard keyboard);

        void visit(Mouse mouse);

        void visit(Computer computer);
    }

    class ComputerPartDisplayVisitor implements ComputerPartVisitor{

        @Override
        public void visit(Keyboard keyboard) {
            System.out.println("Displaying Keyboard.");
        }

        @Override
        public void visit(Mouse mouse) {
            System.out.println("Displaying Mouse.");
        }

        @Override
        public void visit(Computer computer) {
            System.out.println("Displaying Computer.");
        }
    }

    @Test
    public void testPattern(){
        ComputerPart part = new Computer();
        part.accept(new ComputerPartDisplayVisitor());
    }
}
