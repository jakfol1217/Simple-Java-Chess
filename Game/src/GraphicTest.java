import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
public class GraphicTest extends JPanel{

    public void paint(Graphics g){
        g.fillRect(0, 0, 400, 400);
        for(int j = 0;j<400;j = j + 100){
            for(int i = 0;i<400;i = i + 100){
                g.clearRect(i, j + 50,50,50);
            }
            for(int k = 50;k<400;k = k + 100){
                g.clearRect(k, j,50,50);
            }
        }
        try {
            g.drawImage(ImageIO.read(new FileInputStream("pieces/whiteRook.jpg")), 0, 0, 50, 50, null);
            g.drawImage(ImageIO.read(new FileInputStream("pieces/whiteBishop.jpg")), 50, 0, 50, 50, null);
            g.drawImage(ImageIO.read(new FileInputStream("pieces/whiteKnight.jpg")), 100, 0, 50, 50, null);
            g.drawImage(ImageIO.read(new FileInputStream("pieces/whiteQueen.jpg")), 150, 0, 50, 50, null);
            g.drawImage(ImageIO.read(new FileInputStream("pieces/whiteKing.jpg")), 200, 0, 50, 50, null);
            g.drawImage(ImageIO.read(new FileInputStream("pieces/whiteKnight.jpg")), 250, 0, 50, 50, null);
            g.drawImage(ImageIO.read(new FileInputStream("pieces/whiteBishop.jpg")), 300, 0, 50, 50, null);
            g.drawImage(ImageIO.read(new FileInputStream("pieces/whiteRook.jpg")), 350, 0, 50, 50, null);
            g.drawImage(ImageIO.read(new FileInputStream("pieces/whitePawn.jpg")), 0, 50, 50, 50, null);
            g.drawImage(ImageIO.read(new FileInputStream("pieces/whitePawn.jpg")), 50, 50, 50, 50, null);
            g.drawImage(ImageIO.read(new FileInputStream("pieces/whitePawn.jpg")), 100, 50, 50, 50, null);
            g.drawImage(ImageIO.read(new FileInputStream("pieces/whitePawn.jpg")), 150, 50, 50, 50, null);
            g.drawImage(ImageIO.read(new FileInputStream("pieces/whitePawn.jpg")), 200, 50, 50, 50, null);
            g.drawImage(ImageIO.read(new FileInputStream("pieces/whitePawn.jpg")), 250, 50, 50, 50, null);
            g.drawImage(ImageIO.read(new FileInputStream("pieces/whitePawn.jpg")), 300, 50, 50, 50, null);
            g.drawImage(ImageIO.read(new FileInputStream("pieces/whitePawn.jpg")), 350, 50, 50, 50, null);
            g.drawImage(ImageIO.read(new FileInputStream("pieces/blackPawn.jpg")), 0, 300, 50, 50, null);
            g.drawImage(ImageIO.read(new FileInputStream("pieces/blackPawn.jpg")), 50, 300, 50, 50, null);
            g.drawImage(ImageIO.read(new FileInputStream("pieces/blackPawn.jpg")), 100, 300, 50, 50, null);
            g.drawImage(ImageIO.read(new FileInputStream("pieces/blackPawn.jpg")), 150, 300, 50, 50, null);
            g.drawImage(ImageIO.read(new FileInputStream("pieces/blackPawn.jpg")), 200, 300, 50, 50, null);
            g.drawImage(ImageIO.read(new FileInputStream("pieces/blackPawn.jpg")), 250, 300, 50, 50, null);
            g.drawImage(ImageIO.read(new FileInputStream("pieces/blackPawn.jpg")), 300, 300, 50, 50, null);
            g.drawImage(ImageIO.read(new FileInputStream("pieces/blackPawn.jpg")), 350, 300, 50, 50, null);
            g.drawImage(ImageIO.read(new FileInputStream("pieces/blackRook.jpg")), 0, 350, 50, 50, null);
            g.drawImage(ImageIO.read(new FileInputStream("pieces/blackBishop.jpg")), 50, 350, 50, 50, null);
            g.drawImage(ImageIO.read(new FileInputStream("pieces/blackBishop.jpg")), 50, 350, 50, 50, null);
            g.drawImage(ImageIO.read(new FileInputStream("pieces/blackKnight.jpg")), 100, 350, 50, 50, null);
            g.drawImage(ImageIO.read(new FileInputStream("pieces/blackQueen.jpg")), 150, 350, 50, 50, null);
            g.drawImage(ImageIO.read(new FileInputStream("pieces/blackKing.jpg")), 200, 350, 50, 50, null);
            g.drawImage(ImageIO.read(new FileInputStream("pieces/blackKnight.jpg")), 250, 350, 50, 50, null);
            g.drawImage(ImageIO.read(new FileInputStream("pieces/blackBishop.jpg")), 300, 350, 50, 50, null);
            g.drawImage(ImageIO.read(new FileInputStream("pieces/blackRook.jpg")), 350, 350, 50, 50, null);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(600, 431);
        frame.getContentPane().add(new GraphicTest());
        frame.setLocationRelativeTo(null);
        frame.setBackground(Color.LIGHT_GRAY);
        Component mouseClick = new MyComponent();
        frame.addMouseListener((MouseListener) mouseClick);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
