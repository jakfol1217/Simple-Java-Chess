public class King extends Piece {
    private int color = -1;


    public King(int color) {
        this.color = color;
    }


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
