import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
public class GraphicTest extends JPanel{
    private Game game = new Game();

    public Game getGame() {
        return game;
    }

    public void paint(Graphics g){
        g.fillRect(0, 0, 400, 400);
        for (int j = 0; j < 400; j = j + 100) {
            for (int i = 0; i < 400; i = i + 100) {
                g.clearRect(i, j, 50, 50);
            }
            for (int k = 50; k < 400; k = k + 100) {
                g.clearRect(k, j + 50, 50, 50);
            }
        }
        if(game.getTurn() < 0) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    g.drawImage(game.getBoard().drawPiece(i, j), (7 - j) * 50, (7 - i) * 50, 50, 50, null);
                }
            }
        }
        else{
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    g.drawImage(game.getBoard().drawPiece(i, j), j * 50, i * 50, 50, 50, null);
                }
            }
        }
        g.setFont(new Font("Comic Sans", Font.PLAIN, 20));
        String turnName = game.getTurnName()[game.getTurn() + 1];
        g.drawString(turnName + " MOVE", 420, 20);
        String errorText = game.getErrorText();
        g.setFont(new Font("Comic Sans", Font.PLAIN, 15));
        g.drawString(errorText, 405, 100);

    }


}
