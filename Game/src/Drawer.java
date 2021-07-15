import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

public class Drawer {
    private JFrame frame;
    private GraphicTest graphicTest = new GraphicTest();

    public JFrame getFrame() {
        return frame;
    }

    public GraphicTest getGraphicTest() {
        return graphicTest;
    }

    public Drawer(MyComponent mouseClick) {
        frame = new JFrame();
        frame.setSize(600,431);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().add(graphicTest);
        frame.setBackground(Color.LIGHT_GRAY);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addMouseListener((MouseListener) mouseClick);
        frame.setVisible(true);
    }
}
