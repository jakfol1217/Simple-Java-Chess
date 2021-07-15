import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.regex.Pattern;

public class Game {
    private int turn = -1;
    private static final String[] turnName = {"WHITE", "", "BLACK"};
    private static final Pattern fieldPattern = Pattern.compile("[a-hA-H][1-8]");
    private int selectedx = -1;
    private int selectedy = -1;
    private boolean selected = false;
    private boolean gameOver = false;

    public boolean isSelected() {
        return selected;
    }

    private static final Map<String, Integer> fieldNames = Map.of(
            "a",0,
            "b", 1,
            "c", 2,
            "d", 3,
            "e", 4,
            "f", 5,
            "g", 6,
            "h", 7);
    private ChessBoard board;

    public ChessBoard getBoard() {
        return board;
    }

    public Game() {
        this.board = new ChessBoard();
    }

    //Converts the field number passed by the player to array position for ChessBoard
    public static int[] translateField(String position) throws Exception {
        if(fieldPattern.matcher(position).matches()) {
            String position1 = position.substring(0, 1);
            int position2 = Integer.parseInt(position.substring(1, 2));
            return new int[]{position2-1, fieldNames.get(position1)};
        } else{
            throw new Exception("WRONG FIELD");
        }
    }
    public String select(int y, int x){
        if(x < 0 || x >8 || y <0 || y > 8){
            return "WRONG FIELD";
        }
        int col = board.checkColor(turn, new int[]{y, x});
        if(col == 1){
            return "INVALID PIECE CHOSEN";
        }
        else{
            selected = true;
            selectedy = y;
            selectedx = x;
            return "";
        }
    }
    public String move(int y, int x){
        if(x < 0 || x >8 || y <0 || y > 8) {
            return "WRONG FIELD";
        }
            int success = board.movePiece(new int[]{selectedy, selectedx}, new int[]{y,x}, turn);
        board.checkPromotion();
        if(success == 0){
            int enemyCheck = board.checkCheck(turn * (-1));
            int myCheck = board.checkCheck(turn);
            if(turn < 0){
                board.setBlackCheck(enemyCheck);
                board.setWhiteCheck(myCheck);
            }
            else{
                board.setWhiteCheck(enemyCheck);
                board.setBlackCheck(myCheck);
            }
            if(enemyCheck == 1){
                System.out.println("CHECK");
            }
            if(turn <0){
                if(board.getBlackMoved()[0] >=0){
                    board.setMoved(turn);
                }
                board.setBlackMoved(new int[] {-1, -1});
            }
            if(turn >0){
                if(board.getWhiteMoved()[0] >=0){
                    board.setMoved(turn);
                }
                board.setWhiteMoved(new int[] {-1, -1});
            }
            turn = turn * -1;
            selected = false;
            selectedx = -1;
            selectedy = -1;
            if(turn < 0){
                if(board.getBlackCheck() == 1){
                    gameOver = true;
                    return "CHECKMATE " + turnName[turn + 1] + " WIN";

                }
            }
            else {
                if (board.getWhiteCheck() == 1) {
                    gameOver = true;
                    return "CHECKMATE " + turnName[turn + 1] + " WIN";
                }
            }
            return "";
        }
        else{
            //Prone to change
            selected = false;
            return "CAN'T MOVE THERE";
        }
    }
    /*
    public void play(){
        try {
            while(true) {
                if(turn < 0){
                    if(board.getBlackCheck() == 1){
                        System.out.println("CHECKMATE");
                        System.out.println(turnName[turn + 1] + " WIN");
                        break;
                    }
                }
                else{
                    if(board.getWhiteCheck() == 1){
                        System.out.println("CHECKMATE");
                        System.out.println(turnName[turn + 1] + " WIN");
                        break;
                    }
                }
                System.out.println(turnName[turn + 1] + " MOVE");
                System.out.println("MOVE OR CASTLE");
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String piece = br.readLine();
                if(piece.equals("exit")){
                    System.out.println(turnName[(-1 * turn) + 1] + " WIN");
                    break;
                }
                if(piece.equals("cs")){
                    if(board.castle(turn, 1) == 0){
                        turn = turn * -1;
                        continue;
                    }
                    else{
                        System.out.println("CAN'T CASTLE");
                        continue;
                    }
                }
                if(piece.equals("cl")){
                    if(board.castle(turn, -1) == 0){
                        turn = turn * -1;
                        continue;
                    }
                    else{
                        System.out.println("CAN'T CASTLE");
                        continue;
                    }
                }
                int[] from = translateField(piece);
                if(board.checkColor(turn, from)>0){continue;}
                System.out.println("WHERE TO?");
                String pos = br.readLine();
                if(pos.equals("exit")){
                    System.out.println(turnName[(-1 * turn) + 1] + " WIN");
                    break;
                }
                if(pos.equals("cs")){
                    if(board.castle(turn, 1) == 0){
                        turn = turn * -1;
                        continue;
                    }
                    else{
                        System.out.println("CAN'T CASTLE");
                        continue;
                    }
                }
                if(pos.equals("cl")){
                    if(board.castle(turn, -1) == 0){
                        turn = turn * -1;
                        continue;
                    }
                    else{
                        System.out.println("CAN'T CASTLE");
                        continue;
                    }
                }
                int[] to = translateField(pos);
                int success = board.movePiece(from, to, turn);
                board.checkPromotion();
                if(success == 0){
                    int enemyCheck = board.checkCheck(turn * (-1));
                    int myCheck = board.checkCheck(turn);
                    if(turn < 0){
                        board.setBlackCheck(enemyCheck);
                        board.setWhiteCheck(myCheck);
                    }
                    else{
                        board.setWhiteCheck(enemyCheck);
                        board.setBlackCheck(myCheck);
                    }
                    if(enemyCheck == 1){
                        System.out.println("CHECK");
                    }
                    if(turn <0){
                        if(board.getBlackMoved()[0] >=0){
                            board.setMoved(turn);
                        }
                        board.setBlackMoved(new int[] {-1, -1});
                    }
                    if(turn >0){
                        if(board.getWhiteMoved()[0] >=0){
                            board.setMoved(turn);
                        }
                        board.setWhiteMoved(new int[] {-1, -1});
                    }
                    turn = turn * -1;
                }
            }
        }catch (Exception e){
            System.out.println("WRONG FIELD");
            this.play();
        }
    }
*/

    public boolean isGameOver() {
        return gameOver;
    }

}

