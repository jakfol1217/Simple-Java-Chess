import javax.swing.*;
import java.awt.*;

public class ChoosePanel extends JPanel {
    public void paint(Graphics g){
        g.setFont(new Font("Comic Sans", Font.PLAIN, 20));
        g.drawString("QUEEN", 30, 20);
        g.drawString("ROOK", 30, 70);
        g.drawString("BISHOP", 30, 120);
        g.drawString("KNIGHT", 30, 170);

    }
}
