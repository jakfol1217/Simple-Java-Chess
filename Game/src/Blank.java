public class Blank extends Piece {
    private int color;

    public Blank() {
        this.color = 0;
    }

    @Override
    public void draw() {
        System.out.print("  ");
    }
}
