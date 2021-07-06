import java.util.ArrayList;

public class ChessBoard {
    private ArrayList<Piece> board = new ArrayList<>(64);

    public ChessBoard() {
        this.setBoard();
    }

    private void setBoard(){
        //Set up whites
        board.add(0, new Rook(1));
        board.add(1, new Knight(1));
        board.add(2, new Bishop(1));
        board.add(3, new Queen(1));
        board.add(4, new King(1));
        board.add(5, new Bishop(1));
        board.add(6, new Knight(1));
        board.add(7, new Rook(1));
        for(int i = 8; i<16;i++){
            board.add(i, new Pawn(1));
        }
        //fill the rest with blanks
        for(int i = 16;i<48;i++){
            board.add(i, new Blank());
        }
        //set up blacks
        for(int i = 48; i<56;i++){
            board.add(i, new Pawn(-1));
        }
        board.add(56, new Rook(-1));
        board.add(57, new Knight(-1));
        board.add(58, new Bishop(-1));
        board.add(59, new Queen(-1));
        board.add(60, new King(-1));
        board.add(61, new Bishop(-1));
        board.add(62, new Knight(-1));
        board.add(63, new Rook(-1));


    }
    public void drawBoard(){
        for(int i =0;i<64;i++){
            if(i % 8 == 0){
                System.out.print("\n");
                System.out.print("|");
            }
            board.get(i).draw();
            System.out.print("|");
        }
    }
}