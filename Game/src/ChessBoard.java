import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ChessBoard {
    private Piece[][] board = new Piece[8][8];
    private int[] whiteMoved = {-1, -1};
    private int[] blackMoved = {-1, -1};

    public int[] getWhiteMoved() {
        return whiteMoved;
    }
    public void setMoved(int color){

        if(color>0){
            if(board[whiteMoved[0]][whiteMoved[1]].getColor() != 0){
                board[whiteMoved[0]][whiteMoved[1]].setMoved(0);
            }

        }
        if(color<0){
            if(board[blackMoved[0]][blackMoved[1]].getColor() != 0){
                board[blackMoved[0]][blackMoved[1]].setMoved(0);
            }
        }
    }
    public void setWhiteMoved(int[] whiteMoved) {
        this.whiteMoved = whiteMoved;
    }

    public int[] getBlackMoved() {
        return blackMoved;
    }

    public void setBlackMoved(int[] blackMoved) {
        this.blackMoved = blackMoved;
    }
    public void enPassant(int color, int type, int loc0, int loc1){
        if(type>0){
            board[loc0][loc1 - color] = new Blank();

        }
        else{
            board[loc0][loc1 + color] = new Blank();
        }

    }
    public ChessBoard() {
        this.setBoard();
    }

    public Piece[][] getBoard() {
        return board;
    }

    private void setBoard(){
        //Set up whites
        board[0][0] = new Rook(-1, new int[]{0, 0});
        board[0][1] = new Knight(-1, new int[]{0, 1});
        board[0][2] = new Bishop(-1, new int[]{0, 2});
        board[0][3] = new Queen(-1, new int[]{0, 3});
        board[0][4] = new King(-1, new int[]{0, 4});
        board[0][5] = new Bishop(-1, new int[]{0, 5});
        board[0][6] = new Knight(-1, new int[]{0, 6});
        board[0][7] = new Rook(-1, new int[]{0, 7});
        for(int i = 0; i<8;i++){
            board[1][i] = new Pawn(-1, new int[]{1,i});
        }
        //fill the rest with blanks
        for(int i = 2;i<6;i++){
            for(int j = 0;j<8;j++){
                board[i][j] = new Blank();
            }
        }
        //set up blacks
        for(int i = 0; i<8;i++){
            board[6][i] = new Pawn(1, new int[]{6,i});
        }
        board[7][0] = new Rook(1, new int[]{7, 0});
        board[7][1] = new Knight(1, new int[]{7, 1});
        board[7][2] = new Bishop(1, new int[]{7, 2});
        board[7][3] = new Queen(1, new int[]{7, 3});
        board[7][4] = new King(1, new int[]{7, 4});
        board[7][5] = new Bishop(1, new int[]{7, 5});
        board[7][6] = new Knight(1, new int[]{7, 6});
        board[7][7] = new Rook(1, new int[]{7, 7});
    }
    public void drawBoard(){
        System.out.print(1);
        System.out.print(" |");
        for(int i =0;i<8;i++){
            if(i != 0){
                System.out.print("\n");
                System.out.print(i + 1);
                System.out.print(" |");
            }
            for(int j=0;j<8;j++){
                board[i][j].draw();
                System.out.print("|");
            }

        }
        System.out.println(" ");
        System.out.println("   a  b  c  d  e  f  g  h");

    }
    public int movePiece(int[] from, int[] to, int turn){
        if(turn * board[from[0]][from[1]].getColor() <=0){
            System.out.println("INVALID PIECE CHOSEN");
            return 1;
        }
        if(board[from[0]][from[1]].checkMove(to) == 1){
            System.out.println("CAN'T MOVE THERE");
            return 1;
        }
        Piece piece = board[from[0]][from[1]];
        board[to[0]][to[1]] = piece;
        board[from[0]][from[1]] =  new Blank();
        board[to[0]][to[1]].setLocation(to);
        return 0;
    }
    public void checkPromotion(){
        for(int i = 0;i<8;i++){
            if(board[0][i] instanceof Pawn){
                promote(new int[]{0, i}, board[0][i].getColor());
                break;
            }
        }
        for(int i = 0;i<8;i++){
            if(board[7][i] instanceof Pawn){
                promote(new int[]{7, i}, board[7][i].getColor());
                break;
            }
        }
    }
    private void promote(int[] position, int color){
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
                    board[position[0]][position[1]] = new Queen(color, new int[]{position[0], position[1]});
                    break;
                case 1:
                    board[position[0]][position[1]] = new Rook(color, new int[]{position[0], position[1]});
                    break;
                case 2:
                    board[position[0]][position[1]] = new Bishop(color, new int[]{position[0], position[1]});
                    break;
                case 3:
                    board[position[0]][position[1]] = new Knight(color, new int[]{position[0], position[1]});
                    break;
            }
        } catch (Exception e) {
            System.out.println("SOMETHING WENT WRONG. TRY CHOOSING AGAIN");
            checkPromotion();
        }
    }

    public int castle(int color, int type){
        if(color < 0 && type > 0){
            if(board[0][4].getCastleable() * board[0][7].getCastleable() == 1 && board[0][5].getColor() == 0 && board[0][6].getColor() == 0){
                board[0][5] =  new Rook(color, new int[]{0,5});
                board[0][6] =  new King(color, new int[]{0,6});
                board[0][5].setCastleable(0);
                board[0][6].setCastleable(0);
                board[0][4] = new Blank();
                board[0][7] = new Blank();
                return 0;
            }
        }
        if(color <0 && type < 0){
            if(board[0][4].getCastleable() * board[0][0].getCastleable() == 1 && board[0][1].getColor() == 0 && board[0][2].getColor() == 0 && board[0][3].getColor() == 0){
                board[0][3] =  new Rook(color, new int[]{0,3});
                board[0][2] =  new King(color, new int[]{0,2});
                board[0][3].setCastleable(0);
                board[0][2].setCastleable(0);
                board[0][0] = new Blank();
                board[0][4] = new Blank();
                return 0;
            }
        }
        if(color < 0 && type > 0){
            if(board[7][4].getCastleable() * board[7][7].getCastleable() == 1 && board[7][5].getColor() == 0 && board[7][6].getColor() == 0){
                board[7][5] =  new Rook(color, new int[]{7,5});
                board[7][6] =  new King(color, new int[]{7,6});
                board[7][5].setCastleable(0);
                board[7][6].setCastleable(0);
                board[7][4] = new Blank();
                board[7][7] = new Blank();
                return 0;
            }
        }
        if(color <0 && type < 0){
            if(board[7][4].getCastleable() * board[7][0].getCastleable() == 1 && board[7][1].getColor() == 0 && board[7][2].getColor() == 0 && board[7][3].getColor() == 0){
                board[7][3] =  new Rook(color, new int[]{7,3});
                board[7][2] =  new King(color, new int[]{7,2});
                board[7][3].setCastleable(0);
                board[7][2].setCastleable(0);
                board[7][0] = new Blank();
                board[7][4] = new Blank();
                return 0;
            }
        }
        return 1;
    }

    private abstract class Piece{

        private int color;
        private int moved = -1;

        public int getMoved() {
            return moved;
        }

        public void setMoved(int moved) {
            this.moved = moved;
        }

        public abstract int getCastleable();
        public abstract void setCastleable(int castleable);
        public int getColor() {
            return color;
        }

        public void setColor(int color) {
            this.color = color;
        }

        public int[] getLocation() {
            return location;
        }

        public void setLocation(int[] location) {
            this.location = location;
        }

        private int[] location;
        //0-OK, 1- sth wrong
        public abstract int checkMove(int[] position);
        public abstract void draw();
    }


    /*
    BLANK
     */
    private  class Blank extends Piece {
        private int color;
        private int castleable = 0;
        private int moved = -1;

        public int getMoved() {
            return moved;
        }

        public void setMoved(int moved) {
            this.moved = moved;
        }
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
        public int checkMove(int[] position) {
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
        private int[] location;
        private int castleable = 1;
        private int moved = -1;

        public int getMoved() {
            return moved;
        }

        public void setMoved(int moved) {
            this.moved = moved;
        }
        public void setCastleable(int castleable) {
            this.castleable = castleable;
        }
        public int getCastleable() {
            return castleable;
        }

        public King(int color, int[] location) {
            this.color = color;
            this.location = location;
        }

        @Override
        public int checkMove(int[] position) {
            if(ChessBoard.this.board[position[0]][position[1]].getColor() * this.color > 0){
                return 1;
            }
            if (Math.abs(position[0] - location[0]) == 1 || Math.abs(position[1] - location[1]) == 1 || (Math.abs(position[0] - location[0]) == Math.abs(position[1] - location[1]) && Math.abs(position[0] - location[0]) == 1)) {
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

        public int[] getLocation() {
            return location;
        }

        public void setLocation(int[] location) {
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
        private int[] location;
        private int castleable = 0;
        private int moved = -1;

        public int getMoved() {
            return moved;
        }

        public void setMoved(int moved) {
            this.moved = moved;
        }
        public void setCastleable(int castleable) {
            this.castleable = castleable;
        }
        public int getCastleable() {
            return castleable;
        }

        public Queen(int color, int[] location) {
            this.color = color;
            this.location = location;
        }

        @Override
        public int checkMove(int[] position) {
            if(ChessBoard.this.board[position[0]][position[1]].getColor() * this.color > 0){
                return 1;
            }
            if(position[0] == location[0]){
                if(position[1]<location[1]){
                    for(int i = position[1] + 1;i<location[1];i++){
                        if(board[position[0]][i].getColor()!= 0){
                            return 1;
                        }
                    }

                    return 0;
                }
                else{
                    for(int i = location[1] + 1;i<position[1];i++){
                        if(board[position[0]][i].getColor()!= 0){
                            return 1;
                        }
                    }

                    return 0;
                }
            }
            else if (position[1] == location[1]){
                if(position[0]<location[0]){
                    for(int i = position[0] + 1;i<location[0];i++){
                        if(board[i][position[1]].getColor()!= 0){
                            return 1;
                        }
                    }

                    return 0;
                }
                else{
                    for(int i = location[0] + 1;i<position[0];i++){
                        if(board[i][position[1]].getColor()!= 0){
                            return 1;
                        }
                    }

                    return 0;
                }
            }
            else if(Math.abs(position[0]-location[0]) == Math.abs(position[1] - location[1])){
                if(location[0]< position[0] && location[1]<position[1]){
                    for(int i=1;i<Math.abs(position[0]-location[0]);i++){
                        if(board[location[0] + i][location[1] + i].getColor() != 0){
                            return 1;
                        }
                    }
                    return 0;
                }
                else if(location[0]> position[0] && location[1]<position[1]){
                    for(int i=1;i<Math.abs(position[0]-location[0]);i++){
                        if(board[location[0] - i][location[1] + i].getColor() != 0){
                            return 1;
                        }
                    }
                    return 0;
                }
                else if(location[0]<position[0] && location[1]>position[1]){
                    for(int i=1;i<Math.abs(position[0]-location[0]);i++){
                        if(board[location[0] + i][location[1] - i].getColor() != 0){
                            return 1;
                        }
                    }
                    return 0;
                }
                else if(location[0] > position[0] && location[1] > position[1]){
                    for(int i=1;i<Math.abs(position[0]-location[0]);i++){
                        if(board[location[0] - i][location[1] - i].getColor() != 0){
                            return 1;
                        }
                    }
                    return 0;
                }
            }


            return 1;

        }

        public int getColor() {
            return color;
        }

        public void setColor(int color) {
            this.color = color;
        }

        public int[] getLocation() {
            return location;
        }

        public void setLocation(int[] location) {
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
        private int[] location;
        private int castleable = 1;
        private int moved = -1;

        public int getMoved() {
            return moved;
        }

        public void setMoved(int moved) {
            this.moved = moved;
        }
        public void setCastleable(int castleable) {
            this.castleable = castleable;
        }
        public int getCastleable() {
            return castleable;
        }

        public Rook(int color, int[] location) {
            this.color = color;
            this.location = location;
        }

        public int getColor() {
            return color;
        }

        public void setColor(int color) {
            this.color = color;
        }

        public int[] getLocation() {
            return location;
        }

        public void setLocation(int[] location) {
            this.location = location;
        }

        @Override
        public int checkMove(int[] position) {
            if(ChessBoard.this.board[position[0]][position[1]].getColor() * this.color > 0){
                return 1;
            }
            if(position[0] == location[0]){
                if(position[1]<location[1]){
                    for(int i = position[1] + 1;i<location[1];i++){
                        if(board[position[0]][i].getColor()!= 0){
                            return 1;
                        }
                    }
                    castleable = 0;
                    return 0;
                }
                else{
                    for(int i = location[1] + 1;i<position[1];i++){
                        if(board[position[0]][i].getColor()!= 0){
                            return 1;
                        }
                    }
                    castleable = 0;
                    return 0;
                }
            }
            else if (position[1] == location[1]){
                if(position[0]<location[0]){
                    for(int i = position[0] + 1;i<location[0];i++){
                        if(board[i][position[1]].getColor()!= 0){
                            return 1;
                        }
                    }
                    castleable = 0;
                    return 0;
                }
                else{
                    for(int i = location[0] + 1;i<position[0];i++){
                        if(board[i][position[1]].getColor()!= 0){
                            return 1;
                        }
                    }
                    castleable = 0;
                    return 0;
                }
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
        private int[] location;
        private int castleable = 0;
        private int moved = -1;

        public int getMoved() {
            return moved;
        }

        public void setMoved(int moved) {
            this.moved = moved;
        }
        public void setCastleable(int castleable) {
            this.castleable = castleable;
        }
        public int getCastleable() {
            return castleable;
        }

        public Bishop(int color, int[] location) {
            this.color = color;
            this.location = location;
        }


        @Override
        public int checkMove(int[] position) {
            if(ChessBoard.this.board[position[0]][position[1]].getColor() * this.color > 0){
                return 1;
            }
            if(Math.abs(position[0]-location[0]) == Math.abs(position[1] - location[1])){
                if(location[0]< position[0] && location[1]<position[1]){
                    for(int i=1;i<Math.abs(position[0]-location[0]);i++){
                        if(board[location[0] + i][location[1] + i].getColor() != 0){
                            return 1;
                        }
                    }
                    return 0;
                }
                else if(location[0]> position[0] && location[1]<position[1]){
                    for(int i=1;i<Math.abs(position[0]-location[0]);i++){
                        if(board[location[0] - i][location[1] + i].getColor() != 0){
                            return 1;
                        }
                    }
                    return 0;
                }
                else if(location[0]<position[0] && location[1]>position[1]){
                    for(int i=1;i<Math.abs(position[0]-location[0]);i++){
                        if(board[location[0] + i][location[1] - i].getColor() != 0){
                            return 1;
                        }
                    }
                    return 0;
                }
                else if(location[0] > position[0] && location[1] > position[1]){
                    for(int i=1;i<Math.abs(position[0]-location[0]);i++){
                        if(board[location[0] - i][location[1] - i].getColor() != 0){
                            return 1;
                        }
                    }
                    return 0;
                }
            }

            return 1;


        }

        public int getColor() {
            return color;
        }

        public void setColor(int color) {
            this.color = color;
        }

        public int[] getLocation() {
            return location;
        }

        public void setLocation(int[] location) {
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
        private int[] location;
        private int castleable = 0;
        private int moved = -1;

        public int getMoved() {
            return moved;
        }

        public void setMoved(int moved) {
            this.moved = moved;
        }
        public void setCastleable(int castleable) {
            this.castleable = castleable;
        }
        public int getCastleable() {
            return castleable;
        }

        public Knight(int color, int[] location) {
            this.color = color;
            this.location = location;
        }


        @Override
        public int checkMove(int[] position) {
            if(ChessBoard.this.board[position[0]][position[1]].getColor() * this.color > 0){
                return 1;
            }
            if ((Math.abs(position[0] - location[0]) == 2 && Math.abs(position[1] - location[1]) == 1) || (Math.abs(position[0] - location[0]) == 1 && Math.abs(position[1] - location[1]) == 2)) {
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

        public int[] getLocation() {
            return location;
        }

        public void setLocation(int[] location) {
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
        private int[] location;
        private int moved = 2;
        private int castleable = 0;

        public int getMoved() {
            return moved;
        }

        public void setMoved(int moved) {
            this.moved = moved;
        }

        public void setCastleable(int castleable) {
            this.castleable = castleable;
        }
        public int getCastleable() {
            return castleable;
        }

        public Pawn(int color, int[] location) {
            this.color = color;
            this.location = location;
        }


        @Override
        public int checkMove(int[] position) {
            if(ChessBoard.this.board[position[0]][position[1]].getColor() * this.color > 0){
                return 1;
            }
            if(location[1] - this.color >= 0 && location[1] - this.color < 8) {
                if ((position[0] - location[0]) * (-1 * color) == 1 && position[1] - location[1] == 1 && board[location[0]][location[1] - this.color].getColor() * this.color == -1 && board[location[0]][location[1] - this.color].getMoved() == 1) {
                    enPassant(this.color, 1, location[0], location[1]);
                    System.out.println("EN PASSANT");
                    return 0;
                }
            }
            if(location[1] + this.color >= 0 && location[1] + this.color < 8){
                if ((position[0] - location[0]) * (-1 * color) == 1 && position[1] - location[1] == -1 && board[location[0]][location[1] + this.color].getColor() * this.color == -1 && board[location[0]][location[1] + this.color].getMoved() == 1) {
                    enPassant(this.color, -1, location[0], location[1]);
                    System.out.println("EN PASSANT");
                    return 0;
                }
            }

            if((position[0]-location[0]) * (-1 * color) == 1 && (Math.abs(position[1]-location[1])==1)){
                if(board[position[0]][position[1]].getColor() * this.color >= 0){
                    return 1;
                }
                moved = 0;
                return 0;
            }
            else if((position[0] - location[0]) * (-1 * color) == 1 && location[1] == position[1] && board[position[0]][position[1]].getColor() == 0){
                moved = 0;
                return 0;
            }
            else if((position[0] - location[0]) * (-1 * color) == 2 && location[1] == position[1] && moved == 2 && board[position[0]][position[1]].getColor() == 0){
                if(board[location[0]+ (-1 * color)][location[1] ].getColor() != 0){
                    return 1;
                }
                moved = 1;
                if(board[location[0]][location[1]].getColor() < 0){
                    whiteMoved = position;
                }
                else{
                    blackMoved = position;
                }
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

        public int[] getLocation() {
            return location;
        }

        public void setLocation(int[] location) {
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