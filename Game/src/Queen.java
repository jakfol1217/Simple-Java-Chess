public class Queen extends Piece {
    private int color;

    public Queen(int color) {
        this.color = color;
    }

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
