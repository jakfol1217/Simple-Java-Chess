import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MyComponent extends JComponent implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        //System.out.println(mouseEvent.getX() - 7);
        //System.out.println(mouseEvent.getY() - 31);
        System.out.println(mouseEvent.getPoint());
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
