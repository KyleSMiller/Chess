package ChessPieces;

import java.util.ArrayList;

/**
 * This class is used to generate valid desiredMoves for a ChessPiece on a board
 * The logic for what desiredMoves are valid is written in ChessPiece child classes.
 * The Moves class translates that logic into positions on a board, paying attention to board edges and occupied spaces
 */
public class Moves {

    private ArrayList<int[]> desiredMoves;
    private ArrayList<int[]> validMoves;
    private ArrayList<int[]> validAttacks;
    private int[] moveRange = null;
    private ChessPiece.Color color;


    /**
     * If used, the Moves class will operate as though the piece can always reach the desired location
     * It will only account for pieces already sitting in the desired location and will ignore pieces in the way
     * EXAMPLE USES: Knight, King
     * @param moves
     */
    public Moves(ArrayList<int[]> moves, ChessPiece.Color color){
        this.desiredMoves = moves;
        this.validMoves = new ArrayList<>();
        this.validAttacks = new ArrayList<>();
        this.color = color;
    }

    /**
     * If used, the Moves class will operate as though the piece can move in an infinite direction as specified in the range
     * It will account for pieces that block the path when generating valid moves
     * EXAMPLE USES: Bishop, Rook, Queen, Pawn
     * @param moveRange
     */
    public Moves(int[] moveRange, ChessPiece.Color color){
        this.moveRange = moveRange;
        this.validMoves = new ArrayList<>();
        this.validAttacks = new ArrayList<>();
        this.color = color;
    }

    public ArrayList<int[]> getValidMoves(ChessPiece[][] board){
        if(this.moveRange == null) {
            this.generateMovesFromDesire(board);
        }
        else{
            this.generateMovesFromRange(board);
        }
        return validMoves;
    }

    public void setDesiredMoves(ArrayList<int[]> desiredMoves){
        this.desiredMoves = desiredMoves;
    }

    private void generateMovesFromDesire(ChessPiece[][] board){
        validMoves = new ArrayList<>();
        validAttacks = new ArrayList<>();

        for(int i = 0; i < desiredMoves.size(); i++){
            if (desiredMoves.get(i)[0] >= 0 && desiredMoves.get(i)[0] <= board.length
                && desiredMoves.get(i)[1] >= 0 && desiredMoves.get(i)[1] < board[0].length
               ){  // if desired move on board

                int column = desiredMoves.get(i)[0];
                int row = desiredMoves.get(i)[1];
                if(board[column][row] == null){  // space open
                    validMoves.add(new int[]{column, row});
                }
                else if(board[column][row].getColor() != this.color){  // opposite color piece blocking space
                    this.validAttacks.add(new int[]{column, row});
                }

            }
        }
    }

    private void generateMovesFromRange(ChessPiece[][] board){

    }

}
