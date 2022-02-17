package pattern.head_first.behavioral;

import javax.swing.*;
import java.awt.*;

public class WFrame extends JFrame {

    public WFrame(String s) throws HeadlessException {
        super(s);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setVisible(true);
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        graphics.drawString("xxxx", getWidth() / 2, getHeight() / 2);
    }

    public static void main(String[] args) {
        new WFrame("test");
    }
}
