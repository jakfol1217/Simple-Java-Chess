import java.io.BufferedReader;
import java.io.InputStreamReader;
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
        board.add(0, new Rook(-1, 0));
        board.add(1, new Knight(-1,1));
        board.add(2, new Bishop(-1,2));
        board.add(3, new Queen(-1,3));
        board.add(4, new King(-1,4));
        board.add(5, new Bishop(-1,5));
        board.add(6, new Knight(-1,6));
        board.add(7, new Rook(-1,7));
        for(int i = 8; i<16;i++){
            board.add(i, new Pawn(-1, i));
        }
        //fill the rest with blanks
        for(int i = 16;i<48;i++){
            board.add(i, new Blank());
        }
        //set up blacks
        for(int i = 48; i<56;i++){
            board.add(i, new Pawn(1, i));
        }
        board.add(56, new Rook(1, 56));
        board.add(57, new Knight(1, 57));
        board.add(58, new Bishop(1, 58));
        board.add(59, new Queen(1, 59));
        board.add(60, new King(1, 60));
        board.add(61, new Bishop(1, 61));
        board.add(62, new Knight(1, 62));
        board.add(63, new Rook(1, 63));
    }
    public void drawBoard(){
        System.out.print(1);
        System.out.print(" |");
        for(int i =0;i<64;i++){
            if(i % 8 == 0 && i != 0){
                System.out.print("\n");
                System.out.print(i/8 + 1);
                System.out.print(" |");
            }
            board.get(i).draw();
            System.out.print("|");
        }
        System.out.println(" ");
        System.out.println("   a  b  c  d  e  f  g  h");
    }
    public int movePiece(int from, int to, int turn){
        if(turn * board.get(from).getColor() <=0){
            System.out.println("INVALID PIECE CHOSEN");
            return 1;
        }
        if(board.get(from).checkMove(to) == 1){
            System.out.println("CAN'T MOVE THERE");
            return 1;
        }
        Piece piece = board.get(from);
        board.set(to, piece);
        board.set(from, new Blank());
        board.get(to).setLocation(to);
        return 0;
    }
    public void checkPromotion(){
        for(int i = 0;i<8;i++){
            if(board.get(i) instanceof Pawn){
                promote(i, board.get(i).getColor());
                break;
            }
        }
        for(int i = 56;i<64;i++){
            if(board.get(i) instanceof Pawn){
                promote(i, board.get(i).getColor());
                break;
            }
        }
    }
    private void promote(int position, int color){
        try {
            System.out.println("CHOOSE FIGURE");
            System.out.println("0- QUEEN");
            System.out.println("1- ROOK");
            System.out.println("2- BISHOP");
            System.out.println("3- KNIGHT");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int choice = Integer.parseInt(br.readLine());
            if (choice < 0 || choice > 3) {
                throw new Exception("STH WENT WRONG");
            }
            switch (choice){
                case 0:
                    board.set(position, new Queen(color, position));
                    break;
                case 1:
                    board.set(position, new Rook(color, position));
                    break;
                case 2:
                    board.set(position, new Bishop(color, position));
                    break;
                case 3:
                    board.set(position, new Knight(color, position));
                    break;
            }
        } catch (Exception e) {
            System.out.println("SOMETHING WENT WRONG. TRY CHOOSING AGAIN");
            checkPromotion();
        }
    }

    public int castle(int color, int type){
        if(color < 0 && type > 0){
            if(board.get(4).getCastleable() * board.get(7).getCastleable() == 1 && board.get(5).getColor() == 0 && board.get(6).getColor() == 0){
                board.set(5, new Rook(color, 5));
                board.set(6, new King(color, 6));
                board.get(5).setCastleable(0);
                board.get(6).setCastleable(0);
                board.set(4, new Blank());
                board.set(7, new Blank());
                return 0;
            }
        }
        if(color <0 && type < 0){
            if(board.get(4).getCastleable() * board.get(0).getCastleable() == 1 && board.get(1).getColor() == 0 && board.get(2).getColor() == 0 && board.get(3).getColor() == 0){
                board.set(3, new Rook(color, 5));
                board.set(2, new King(color, 6));
                board.get(3).setCastleable(0);
                board.get(2).setCastleable(0);
                board.set(0, new Blank());
                board.set(4, new Blank());
                return 0;
            }
        }
        if(color > 0 && type > 0){
            if(board.get(60).getCastleable() * board.get(63).getCastleable() == 1 && board.get(61).getColor() == 0 && board.get(62).getColor() == 0){
                board.set(61, new Rook(color, 5));
                board.set(62, new King(color, 6));
                board.get(61).setCastleable(0);
                board.get(62).setCastleable(0);
                board.set(60, new Blank());
                board.set(63, new Blank());
                return 0;
            }
        }
        if(color > 0 && type < 0){
            if(board.get(60).getCastleable() * board.get(56).getCastleable() == 1 && board.get(57).getColor() == 0 && board.get(58).getColor() == 0 && board.get(59).getColor() == 0){
                board.set(59, new Rook(color, 5));
                board.set(58, new King(color, 6));
                board.get(59).setCastleable(0);
                board.get(58).setCastleable(0);
                board.set(56, new Blank());
                board.set(60, new Blank());
                return 0;
            }
        }
        return 1;
    }

    private abstract class Piece{
        private int color;

        public abstract int getCastleable();
        public abstract void setCastleable(int castleable);
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
        private int castleable = 0;

        public void setCastleable(int castleable) {
            this.castleable = castleable;
        }

        public int getCastleable() {
            return castleable;
        }

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
        private int castleable = 1;

        public void setCastleable(int castleable) {
            this.castleable = castleable;
        }
        public int getCastleable() {
            return castleable;
        }

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
                castleable = 0;
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
        private int castleable = 0;

        public void setCastleable(int castleable) {
            this.castleable = castleable;
        }
        public int getCastleable() {
            return castleable;
        }

        public Queen(int color, int location) {
            this.color = color;
            this.location = location;
        }

        @Override
        public int checkMove(int position) {
            if(ChessBoard.this.board.get(position).getColor() * this.color > 0){
                return 1;
            }
            int row = (location/8) * 8;
            if(Math.abs(position - location) % 9 == 0){
                if(position > location){
                    for(int i = location + 9;i < position;i=i+9){
                        if(ChessBoard.this.board.get(i).getColor() * this.color != 0){
                            return 1;
                        }
                    }
                }
                else{
                    for(int i = location - 9;i > position;i=i-9){
                        if(ChessBoard.this.board.get(i).getColor() * this.color != 0){
                            return 1;
                        }
                    }
                }
                return 0;
            }
            else if(Math.abs(position - location) % 7 == 0) {
                if (position > location) {
                    for (int i = location + 7; i < position; i = i + 7) {
                        if (ChessBoard.this.board.get(i).getColor() * this.color != 0) {
                            return 1;
                        }
                    }
                } else {
                    for (int i = location - 7; i > position; i = i - 7) {
                        if (ChessBoard.this.board.get(i).getColor() * this.color != 0) {
                            return 1;
                        }
                    }
                }
                return 0;
            }
            else if(Math.abs(position - location) % 8 == 0){
                if(position > location){
                    for(int i = location + 8;i < position;i=i+8){
                        if(ChessBoard.this.board.get(i).getColor() * this.color != 0){
                            return 1;
                        }
                    }
                }
                else{
                    for(int i = location - 8;i > position;i=i-8){
                        if(ChessBoard.this.board.get(i).getColor() * this.color != 0){
                            return 1;
                        }
                    }
                }
                return 0;
            }
            else if(row <= position && row + 7 >= position){
                if(position > location){
                    for(int i = location + 1;i < position;i++){
                        if(ChessBoard.this.board.get(i).getColor() * this.color != 0){
                            return 1;
                        }
                    }
                }
                else{
                    for(int i = location - 1;i > position;i--){
                        if(ChessBoard.this.board.get(i).getColor() * this.color != 0){
                            return 1;
                        }
                    }
                }
                return 0;
            }

            else{
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
        private int castleable = 1;

        public void setCastleable(int castleable) {
            this.castleable = castleable;
        }
        public int getCastleable() {
            return castleable;
        }

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
            int row = (location/8) * 8;
            if(Math.abs(position - location) % 8 == 0){
                if(position > location){
                    for(int i = location + 8;i < position;i=i+8){
                        if(ChessBoard.this.board.get(i).getColor() * this.color != 0){
                            return 1;
                        }
                    }
                }
                else{
                    for(int i = location - 8;i > position;i=i-8){
                        if(ChessBoard.this.board.get(i).getColor() * this.color != 0){
                            return 1;
                        }
                    }
                }
                castleable = 0;
                return 0;
            }
            else if(row <= position && row + 7 >= position){
                if(position > location){
                    for(int i = location + 1;i < position;i++){
                        if(ChessBoard.this.board.get(i).getColor() * this.color != 0){
                            return 1;
                        }
                    }
                }
                else{
                    for(int i = location - 1;i > position;i--){
                        if(ChessBoard.this.board.get(i).getColor() * this.color != 0){
                            return 1;
                        }
                    }
                }
                castleable = 0;
                return 0;
            }
            else{
                return 1;
            }

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
        private int castleable = 0;

        public void setCastleable(int castleable) {
            this.castleable = castleable;
        }
        public int getCastleable() {
            return castleable;
        }

        public Bishop(int color, int location) {
            this.color = color;
            this.location = location;
        }


        @Override
        public int checkMove(int position) {
            if(ChessBoard.this.board.get(position).getColor() * this.color > 0){
                return 1;
            }
            if(Math.abs(position - location) % 9 == 0){
                if(position > location){
                    for(int i = location + 9;i < position;i=i+9){
                        if(ChessBoard.this.board.get(i).getColor() * this.color != 0){
                            return 1;
                        }
                    }
                }
                else{
                    for(int i = location - 9;i > position;i=i-9){
                        if(ChessBoard.this.board.get(i).getColor() * this.color != 0){
                            return 1;
                        }
                    }
                }
                return 0;
            }
            else if(Math.abs(position - location) % 7 == 0){
                if(position > location){
                    for(int i = location + 7;i < position;i=i+7){
                        if(ChessBoard.this.board.get(i).getColor() * this.color != 0){
                            return 1;
                        }
                    }
                }
                else{
                    for(int i = location - 7;i > position;i=i-7){
                        if(ChessBoard.this.board.get(i).getColor() * this.color != 0){
                            return 1;
                        }
                    }
                }
                return 0;
            }
            else{
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
        private int castleable = 0;

        public void setCastleable(int castleable) {
            this.castleable = castleable;
        }
        public int getCastleable() {
            return castleable;
        }

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
        private int moved = 2;
        private int castleable = 0;

        public void setCastleable(int castleable) {
            this.castleable = castleable;
        }
        public int getCastleable() {
            return castleable;
        }

        public Pawn(int color, int location) {
            this.color = color;
            this.location = location;
        }


        @Override
        public int checkMove(int position) {
            if(ChessBoard.this.board.get(position).getColor() * this.color > 0){
                return 1;
            }
            if((position-location) * (-1 * color) == 9 || (position-location) * (-1 * color) == 7){
                if(ChessBoard.this.board.get(position).getColor() * this.color >= 0){
                    return 1;
                }
                moved = 0;
                return 0;
            }
            else if((position - location) * (-1 * color) == 8){
                moved = 0;
                return 0;
            }
            else if((position - location) * (-1 * color) == 16 && moved == 2){
                if(ChessBoard.this.board.get(location + (8 * (-1 * color))).getColor() * this.color != 0){
                    return 1;
                }
                moved = 1;
                return 0;
            }
            else {
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
                System.out.print("Wp");
            }
            else{
                System.out.print(("Bp"));
            }
        }
    }

}