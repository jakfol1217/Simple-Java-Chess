public class Pawn extends Piece {
    private int color = -1;


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
