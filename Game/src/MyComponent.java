import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MyComponent extends JComponent implements MouseListener {
    private Drawer drawer = new Drawer(this);
    private int movex;
    private int movey;
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        //System.out.println((mouseEvent.getX() - 7) / 50 + ", " + (mouseEvent.getY() - 31) / 50);

        /*
        if(!drawer.getGraphicTest().getGame().isGameOver()) {
            if (!drawer.getGraphicTest().getGame().isSelected()) {

                drawer.getGraphicTest().getGame().select((mouseEvent.getY() - 31) / 50, (mouseEvent.getX() - 7) / 50);
            } else {
                movex = mouseEvent.getX() / 50;
                movey = mouseEvent.getY() / 50;
                System.out.println(movex + ", " + movey);
                drawer.getGraphicTest().getGame().move(movey, movex);
                drawer.getFrame().repaint();
            }
        }
        else {

        }

         */


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
