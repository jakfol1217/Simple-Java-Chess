import java.util.ArrayList;

public class ChessBoard {
    private ArrayList<Piece> board = new ArrayList<>(64);

    public ChessBoard() {
        this.setBoard();
    }

    public ArrayList<Piece> getBoard() {
        return board;
    }

    private void setBoard(){
        //Set up whites
        board.add(0, new Rook(1, 0));
        board.add(1, new Knight(1,1));
        board.add(2, new Bishop(1,2));
        board.add(3, new Queen(1,3));
        board.add(4, new King(1,4));
        board.add(5, new Bishop(1,5));
        board.add(6, new Knight(1,6));
        board.add(7, new Rook(1,7));
        for(int i = 8; i<16;i++){
            board.add(i, new Pawn(1, i));
        }
        //fill the rest with blanks
        for(int i = 16;i<48;i++){
            board.add(i, new Blank());
        }
        //set up blacks
        for(int i = 48; i<56;i++){
            board.add(i, new Pawn(-1, i));
        }
        board.add(56, new Rook(-1, 56));
        board.add(57, new Knight(-1, 57));
        board.add(58, new Bishop(-1, 58));
        board.add(59, new Queen(-1, 59));
        board.add(60, new King(-1, 60));
        board.add(61, new Bishop(-1, 61));
        board.add(62, new Knight(-1, 62));
        board.add(63, new Rook(-1, 63));


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


    private abstract class Piece{
        private int color;

        public int getColor() {
            return color;
        }

        public void setColor(int color) {
            this.color = color;
        }

        public int getLocation() {
            return location;
        }

        public void setLocation(int location) {
            this.location = location;
        }

        private int location;
        //0-OK, 1- sth wrong
        public abstract int checkMove(int position);
        public abstract void draw();
    }


    /*
    BLANK
     */
    private  class Blank extends Piece {
        private int color;
        public Blank() {
            int color = 0;
        }

        @Override
        public int checkMove(int position) {
            return 1;
        }

        public int getColor() {
            return color;
        }

        public void setColor(int color) {
            this.color = color;
        }

        @Override
        public void draw() {
            System.out.print("  ");
        }
    }

    /*
    KING
     */
    private class King extends Piece {
        private int color;
        private int location;

        public King(int color, int location) {
            this.color = color;
            this.location = location;
        }

        @Override
        public int checkMove(int position) {
            if(ChessBoard.this.board.get(position).getColor() * this.color > 0){
                return 1;
            }
            if (Math.abs(position - location) == 1 || Math.abs(position - location) == 8 || Math.abs(position - location) == 9 || Math.abs(position - location) == 7) {
                return 0;
            } else {
                return 1;
            }
        }

        public int getColor() {
            return color;
        }

        public void setColor(int color) {
            this.color = color;
        }

        public int getLocation() {
            return location;
        }

        public void setLocation(int location) {
            this.location = location;
        }

        @Override
        public void draw() {
            if (color == -1) {
                System.out.print("WK");
            } else {
                System.out.print(("BK"));
            }
        }
    }

    /*
    QUEEN
     */
    private class Queen extends Piece {
        private int color;
        private int location;

        public Queen(int color, int location) {
            this.color = color;
            this.location = location;
        }

        @Override
        public int checkMove(int position) {
            if(ChessBoard.this.board.get(position).getColor() * this.color > 0){
                return 1;
            }
            return 0;
        }

        public int getColor() {
            return color;
        }

        public void setColor(int color) {
            this.color = color;
        }

        public int getLocation() {
            return location;
        }

        public void setLocation(int location) {
            this.location = location;
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


    /*
    ROOK
     */
    private class Rook extends Piece {
        private int color;
        private int location;

        public Rook(int color, int location) {
            this.color = color;
            this.location = location;
        }

        public int getColor() {
            return color;
        }

        public void setColor(int color) {
            this.color = color;
        }

        public int getLocation() {
            return location;
        }

        public void setLocation(int location) {
            this.location = location;
        }

        @Override
        public int checkMove(int position) {
            if(ChessBoard.this.board.get(position).getColor() * this.color > 0){
                return 1;
            }
            return 0;
        }

        @Override
        public void draw() {
            if(color == -1){
                System.out.print("WR");
            }
            else{
                System.out.print(("BR"));
            }
        }
    }

    /*
    BISHOP
     */
    private class Bishop extends Piece {
        private int color;
        private int location;

        public Bishop(int color, int location) {
            this.color = color;
            this.location = location;
        }


        @Override
        public int checkMove(int position) {
            if(ChessBoard.this.board.get(position).getColor() * this.color > 0){
                return 1;
            }
            return 0;
        }

        public int getColor() {
            return color;
        }

        public void setColor(int color) {
            this.color = color;
        }

        public int getLocation() {
            return location;
        }

        public void setLocation(int location) {
            this.location = location;
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

    /*
    KNIGHT
     */
    private class Knight extends Piece {
        private int color;
        private int location;

        public Knight(int color, int location) {
            this.color = color;
            this.location = location;
        }


        @Override
        public int checkMove(int position) {
            if(ChessBoard.this.board.get(position).getColor() * this.color > 0){
                return 1;
            }
            if (Math.abs(position - location) == 10 || Math.abs(position - location) == 6 || Math.abs(position - location) == 17 || Math.abs(position - location) == 15) {
                return 0;
            } else {
                return 1;
            }


        }

        public int getColor() {
            return color;
        }

        public void setColor(int color) {
            this.color = color;
        }

        public int getLocation() {
            return location;
        }

        public void setLocation(int location) {
            this.location = location;
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

    /*
    PAWN
     */
    private class Pawn extends Piece {
        private int color;
        private int location;

        public Pawn(int color, int location) {
            this.color = color;
            this.location = location;
        }


        @Override
        public int checkMove(int position) {
            if(ChessBoard.this.board.get(position).getColor() * this.color > 0){
                return 1;
            }
            return 0;
        }

        public int getColor() {
            return color;
        }

        public void setColor(int color) {
            this.color = color;
        }

        public int getLocation() {
            return location;
        }

        public void setLocation(int location) {
            this.location = location;
        }

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





}