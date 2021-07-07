import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.regex.Pattern;

public class Game {
    private int turn = -1;
    private static final String[] turnName = {"WHITE", "", "BLACK"};
    private static final Pattern fieldPattern = Pattern.compile("[a-hA-H][1-8]");
    private static final Map<String, Integer> fieldNames = Map.of(
            "a",1,
            "b", 2,
            "c", 3,
            "d", 4,
            "e", 5,
            "f", 6,
            "g", 7,
            "h", 8);
    private ChessBoard board;

    public Game() {
        this.board = new ChessBoard();
    }

    //Converts the field number passed by the player to array position for ChessBoard
    public static int translateField(String position) throws Exception {
        if(fieldPattern.matcher(position).matches()) {
            String position1 = position.substring(0, 1);
            int position2 = Integer.parseInt(position.substring(1, 2));
            return fieldNames.get(position1) + (position2 - 1) * 8 - 1;
        } else{
            throw new Exception("WRONG FIELD");
        }
    }


    public void play(){
        try {
            while(true) {
                System.out.println(turnName[turn + 1] + " MOVE");
                board.drawBoard();
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String piece = br.readLine();
                if(piece.equals("exit")){
                    System.out.println(turnName[(-1 * turn) + 1] + " WIN");
                    break;
                }
                System.out.println("WHERE TO?");
                String pos = br.readLine();
                if(pos.equals("exit")){
                    System.out.println(turnName[(-1 * turn) + 1] + " WIN");
                    break;
                }
                int from = translateField(piece);
                int to = translateField(pos);
                int success = board.movePiece(from, to, turn);
                board.checkPromotion();
                if(success == 0){
                    turn = turn * -1;
                }
            }
        }catch (Exception e){
            System.out.println("WRONG FIELD");
            this.play();
        }
    }

}