public class Bishop extends Piece {
    private int color;

    public Bishop(int color) {
        this.color = color;
    }

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
