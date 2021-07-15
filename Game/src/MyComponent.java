import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MyComponent extends JComponent implements MouseListener {
    private Drawer drawer = new Drawer(this);

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        //System.out.println( + ", " + );
        int movex = (mouseEvent.getX() - 7) / 50;
        int movey = (mouseEvent.getY() - 31) / 50;

        if(!drawer.getGraphicTest().getGame().isGameOver()) {
            if (!drawer.getGraphicTest().getGame().isSelected()) {
                drawer.getGraphicTest().getGame().select(movey, movex);
            } else {
                System.out.println(movex + ", " + movey);
                drawer.getGraphicTest().getGame().move(movey, movex);
                drawer.getFrame().repaint();
            }
        }
        else {

        }




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
