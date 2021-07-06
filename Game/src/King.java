public class King extends Piece {
    private int color = -1;


    @Override
    public void draw() {
        if(color == -1){
            System.out.print("WK");
        }
        else{
            System.out.print(("BK"));
        }
    }
}
