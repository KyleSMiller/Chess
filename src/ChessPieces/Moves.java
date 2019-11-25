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

    public ArrayList<int[]> getValidMoves(ChessPiece[][] board, int[] position){
        if(this.moveRange == null) {
            this.generateMovesFromDesire(board);
        }
        else{
            this.generateMovesFromRange(board, position);
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
                    break;
                }
            }
        }
    }

    /**
     * Generate a list of legal moves from the given position, given a range array
     * @param board     the chessboard
     * @param position  the position of the piece on the board
     */
    private void generateMovesFromRange(ChessPiece[][] board, int[] position){
        validMoves = new ArrayList<>();
        validAttacks = new ArrayList<>();
        // range array is organized [up, down, side, diagonalUp, diagonalDown]
        upMoves(board, position);
        downMoves(board, position);
        sideMoves(board, position);
        diagonalUpMoves(board, position);
        diagonalDownMoves(board, position);
    }



    /*
                           -------------------------From this point down-------------------------
                                        Methods to generate valid moves from a range
        This is easily one of the messiest collections of methods I have ever made and looking at it causes me physical pain
                           ----------------------------------------------------------------------
     */

    /**
     * Generate the moves a piece can make in the upwards direction
     * @param board     the chessboard
     * @param position  the piece's position on the board
     */
    private void upMoves(ChessPiece[][] board, int[] position){
        if(moveRange[0] == 0) return; // skip pieces that cannot move forwards

        int range;
        final int INFINITE_RANGE = -1;

        if(position[1] != 0){  // if not on top edge
            range = this.moveRange[0];
            if(range == INFINITE_RANGE) {
                range = Math.abs((board.length + 1) - position[1]);  // distance to top edge
            }
            for(int i = 1; i < range; i++){  // start loop at 1 to prevent looking at self
                if (board[position[0]][position[1] - i] == null) {  // if space open
                    validMoves.add(new int[]{position[0], position[1] - i});
                }
                else if (board[position[0]][position[1] - i].getColor() != this.color){  // if blocked by enemy piece
                    validAttacks.add(new int[]{position[0], position[1] - i});
                    break;
                }
                else {  // if blocked by friendly piece
                    break;
                }
            }
        }
    }

    /**
     * Generate the moves a piece can make in the downwards direction
     * @param board     the chessboard
     * @param position  the piece's position on the board
     */
    private void downMoves(ChessPiece[][] board, int[] position){
        if(moveRange[1] == 0) return;  // skip pieces that cannot move backwards

        int range;
        final int INFINITE_RANGE = -1;

        if(position[1] != board.length){  // if not on bottom edge
            range = this.moveRange[1];
            if(range == INFINITE_RANGE) {
                range = Math.abs(position[1] - board.length);  // distance to bottom edge
            }

            for(int i = 1; i < range; i++){  // start loop at 1 to prevent looking at self
                if (board[position[0]][position[1] + i] == null) {  // if space open
                    validMoves.add(new int[]{position[0], position[1] + i});
                }
                else if (board[position[0]][position[1] + i].getColor() != this.color){  // if blocked by enemy piece
                    validAttacks.add(new int[]{position[0], position[1] + i});
                    break;
                }
                else {  // if blocked by friendly piece
                    break;
                }
            }
        }
    }

    /**
     * Generate the moves a piece can make in the side directions
     * @param board     the chessboard
     * @param position  the piece's position on the board
     */
    private void sideMoves(ChessPiece[][] board, int[] position){
        if(moveRange[2] == 0) return;  // skip if piece cannot move sideways

        final int INFINITE_RANGE = -1;
        int range;

        // left
        if(position[0] != 0){  // if not on left edge
            range = this.moveRange[2];
            if(range == INFINITE_RANGE){
                range = Math.abs(position[0] - (board.length + 1));  // distance to left edge
            }

            for(int i = 1; i < range; i++){  // start loop at 1 to prevent looking at self
                if (board[position[0] - i][position[1]] == null) {  // if space open
                    validMoves.add(new int[]{position[0] - i, position[1]});
                }
                else if (board[position[0] - i][position[1]].getColor() != this.color){  // if blocked by enemy piece
                    validAttacks.add(new int[]{position[0] - i, position[1]});
                    break;
                }
                else {  // if blocked by friendly piece
                    break;
                }
            }
        }
        // right
        if(position[0] != board.length - 1){  // if not on right edge
            range = this.moveRange[2];
            if(range == INFINITE_RANGE) {
                range = Math.abs(board.length - position[0]);  // distance to right edge
            }

            for(int i = 1; i < range; i++){  // start loop at 1 to prevent looking at self
                if (board[position[0] + i][position[1]] == null) {  // if space open
                    validMoves.add(new int[]{position[0] + i, position[1]});
                }
                else if (board[position[0] + i][position[1]].getColor() != this.color){  // if blocked by enemy piece
                    validAttacks.add(new int[]{position[0] + i, position[1]});
                    break;
                }
                else {  // if blocked by friendly piece
                    break;
                }
            }
        }
    }

    /**
     * Generate the moves a piece can make in the diagonal-up directions
     * @param board     the chessboard
     * @param position  the piece's position on the board
     */
    private void diagonalUpMoves(ChessPiece[][] board, int[] position){
        if(moveRange[3] == 0) return; // skip pieces that cannot move diagonal up

        int range;
        final int INFINITE_RANGE = -1;

        // leftUp
        if(position[0] != 0 && position[1] != 0){  // if not on left edge && not on top edge
            range = this.moveRange[3];
            if(range == INFINITE_RANGE) {
                int distanceToTopEdge = Math.abs((board.length + 1) - position[1]);
                int distanceToLeftEdge = Math.abs(position[0] - (board.length + 1));
                range = Math.min(distanceToTopEdge, distanceToLeftEdge);
            }

            for(int i = 0; i < range; i++){
                if (board[position[0] - i][position[1] - i] == null) {  // if space open
                    validMoves.add(new int[]{position[0] - i, position[1] - i});
                }
                else if (board[position[0] - i][position[1] - i].getColor() != this.color){  // if blocked by enemy piece
                    validAttacks.add(new int[]{position[0] - i, position[1] - i});
                }
            }
        }

        // rightUp
        if(position[0] != board.length && position[1] != 0){  // if not on right edge && not on top edge
            range = this.moveRange[3];
            if(range == INFINITE_RANGE) {
                int distanceToTopEdge = Math.abs((board.length + 1) - position[1]);
                int distanceToRightEdge = Math.abs(board.length - position[0]);
                range = Math.min(distanceToTopEdge, distanceToRightEdge);
            }

            for(int i = 0; i < range; i++){
                if (board[position[0] + i][position[1] - i] == null) {  // if space open
                    validMoves.add(new int[]{position[0] + i, position[1] - i});
                }
                else if (board[position[0] + i][position[1] - i].getColor() != this.color){  // if blocked by enemy piece
                    validAttacks.add(new int[]{position[0] + i, position[1] - i});
                }
            }
        }
    }

    /**
     * Generate the moves a piece can make in the diagonal-up directions
     * @param board     the chessboard
     * @param position  the piece's position on the board
     */
    private void diagonalDownMoves(ChessPiece[][] board, int[] position){
        if(moveRange[4] == 0) return; // skip if piece cannot move diagonal down

        int range;
        final int INFINITE_RANGE = -1;

        // leftDown
        if(position[0] != 0 && position[1] != board.length){  // if not on left edge && not on bottom edge
            range = this.moveRange[4];
            if(range == INFINITE_RANGE) {
                int distanceToBottomEdge = Math.abs(position[1] - board.length);
                int distanceToLeftEdge = Math.abs(position[0] - (board.length + 1));
                range = Math.min(distanceToBottomEdge, distanceToLeftEdge);
            }

            for(int i = 0; i < range; i++){
                if (board[position[0] - i][position[1] + i] == null) {  // if space open
                    validMoves.add(new int[]{position[0] - i, position[1] + i});
                }
                else if (board[position[0] - i][position[1] + i].getColor() != this.color){  // if blocked by enemy piece
                    validAttacks.add(new int[]{position[0] - i, position[1] + i});
                }
            }
        }

        // rightDown
        if(position[0] != board.length && position[1] != board.length){  // if not on right edge && not on bottom edge
            range = this.moveRange[4];
            if(range == -1) {
                int distanceToBottomEdge = Math.abs(position[1] - board.length);
                int distanceToRightEdge = Math.abs(board.length - position[0]);
                range = Math.min(distanceToBottomEdge, distanceToRightEdge);
            }

            for(int i = 0; i < range; i++){
                if (board[position[0] + i][position[1] + i] == null) {  // if space open
                    validMoves.add(new int[]{position[0] + i, position[1] + i});
                }
                else if (board[position[0] + i][position[1] + i].getColor() != this.color){  // if blocked by enemy piece
                    validAttacks.add(new int[]{position[0] + i, position[1] + i});
                }
            }
        }
    }

}
