public class Bishop extends Piece {
    private int color = -1;


    @Override
    public void draw() {
        if(color == -1){
            System.out.print("WB");
        }
        else{
            System.out.print(("BB"));
        }
    }
}
