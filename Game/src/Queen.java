public class Queen extends Piece {
    private int color = -1;


    @Override
    public void draw() {
        if(color == -1){
            System.out.print("WQ");
        }
        else{
            System.out.print(("BQ"));
        }
    }
}
