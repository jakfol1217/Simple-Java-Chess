import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MyComponent extends JComponent implements MouseListener {
    private Drawer drawer = new Drawer(this);
    private int selectedx;
    private int selectedy;
    private int movex;
    private int movey;
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        //System.out.println((mouseEvent.getX() - 7) / 50 + ", " + (mouseEvent.getY() - 31) / 50);


        if(!drawer.getGraphicTest().getGame().isGameOver()) {
            if (!drawer.getGraphicTest().getGame().isSelected()) {
                selectedx = (mouseEvent.getX() - 7) / 50;
                selectedy = (mouseEvent.getY() - 31) / 50;
                drawer.getGraphicTest().getGame().select(selectedy , selectedx );
            } else {
                movex = mouseEvent.getX() / 50;
                movey = mouseEvent.getY() / 50;
                System.out.println(selectedx + ", " + selectedy);
                System.out.println(movex + ", " + movey);
                drawer.getGraphicTest().getGame().move(selectedy, selectedx, movey, movex);
                drawer.getFrame().repaint();
            }
        }
        else {

        };



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
