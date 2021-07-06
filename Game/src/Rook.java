public class Rook extends Piece {
    private int color;

    public Rook(int color) {
        this.color = color;
    }

    @Override
    public void draw() {
        if(color == -1){
            System.out.print("WR");
        }
        else{
            System.out.print(("BR"));
        }
    }
}
