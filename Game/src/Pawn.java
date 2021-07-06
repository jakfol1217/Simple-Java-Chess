public class Pawn extends Piece {
    private int color;

    public Pawn(int color) {
        this.color = color;
    }

    @Override
    public void draw() {
        if(color == -1){
            System.out.print("Wp");
        }
        else{
            System.out.print(("Bp"));
        }
    }
}
