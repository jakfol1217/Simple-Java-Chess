import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MyComponent extends JComponent implements MouseListener {
    private Drawer drawer = new Drawer(this);
    private Rectangle rect = new Rectangle(400,0, 200, 400);
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        int movex = (mouseEvent.getX() - 7) / 50;
        int movey = (mouseEvent.getY() - 31) / 50;

        if(!drawer.getGraphicTest().getGame().isGameOver()) {
            if (!drawer.getGraphicTest().getGame().isSelected()) {
                drawer.getGraphicTest().getGame().select(movey, movex);
            } else {
                drawer.getGraphicTest().getGame().move(movey, movex);
            }
        }
        else {

        }
        drawer.getFrame().repaint();



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
