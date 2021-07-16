import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.regex.Pattern;

public class Game {
    private int turn = -1;
    private final String[] turnName = {"WHITE", "", "BLACK"};
    private static final Pattern fieldPattern = Pattern.compile("[a-hA-H][1-8]");
    private int selectedx = -1;
    private int selectedy = -1;
    private boolean selected = false;
    private boolean gameOver = false;
    private String errorText = " ";
    private String win = " ";

    public String getWin() {
        return win;
    }

    public String[] getTurnName() {
        return turnName;
    }

    public String getErrorText() {
        return errorText;
    }

    public int getTurn() {
        return turn;
    }

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


    public void select(int y, int x){
        if(turn < 0){
            y = 7 - y;
        }else{
            x = 7 - x;
        }
        try {
            int col = board.checkColor(turn, new int[]{y, x});
            if (col == 1) {
                errorText =  "INVALID PIECE CHOSEN";
            } else {
                selected = true;
                selectedy = y;
                selectedx = x;
                board.setChosen(selectedy, selectedx, true);
                errorText =  " ";
            }
        }catch (ArrayIndexOutOfBoundsException e){
            errorText = "WRONG FIELD";
        }
    }
    public void move(int y, int x){
        try {
            if(turn < 0){
                y = 7 - y;
            }
            else{
                x = 7 - x;
            }
            int success = board.movePiece(new int[]{selectedy, selectedx}, new int[]{y, x}, turn);
            board.checkPromotion();
            if (success == 0) {
                int enemyCheck = board.checkCheck(turn * (-1));
                int myCheck = board.checkCheck(turn);
                if (turn < 0) {
                    board.setBlackCheck(enemyCheck);
                    board.setWhiteCheck(myCheck);
                } else {
                    board.setWhiteCheck(enemyCheck);
                    board.setBlackCheck(myCheck);
                }
                if (enemyCheck == 1) {
                    win = "CHECK";
                }
                else{
                    win = " ";
                }
                if (turn < 0) {
                    if (board.getBlackMoved()[0] >= 0) {
                        board.setMoved(turn);
                    }
                    board.setBlackMoved(new int[]{-1, -1});
                }
                if (turn > 0) {
                    if (board.getWhiteMoved()[0] >= 0) {
                        board.setMoved(turn);
                    }
                    board.setWhiteMoved(new int[]{-1, -1});
                }
                turn = turn * -1;
                board.setChosen(y, x, false);
                selectedx = -1;
                selectedy = -1;
                if (turn < 0) {
                    if (board.getBlackCheck() == 1) {
                        gameOver = true;
                        win = "CHECKMATE " + turnName[turn + 1] + " WIN";

                    }
                } else {
                    if (board.getWhiteCheck() == 1) {
                        gameOver = true;
                        win = "CHECKMATE " + turnName[turn + 1] + " WIN";
                    }
                }
                errorText =  " ";
            } else {
                //Prone to change
                board.setChosen(selectedy, selectedx, false);
                errorText =  "CAN'T MOVE THERE";
            }
            selected = false;
        }catch(IndexOutOfBoundsException e){
            errorText = "WRONG FIELD";
        }
    }

    public boolean isGameOver() {
        return gameOver;
    }

}

