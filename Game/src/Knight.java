public class Knight extends Piece {
    private int color = -1;

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
