public class Knight extends Piece {
    private int color;

    public Knight(int color) {
        this.color = color;
    }

    @Override
    public void draw() {
        if(color == -1){
            System.out.print("WN");
        }
        else{
            System.out.print(("BN"));
        }
    }
}
