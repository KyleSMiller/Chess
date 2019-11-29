package ChessPieces;

import java.util.ArrayList;

public class Bishop extends ChessPiece {

    private Moves moves;

    public Bishop(int[] position, Color color){
        super("https://i.imgur.com/0NJgyIC.png", position, color);
        moves = new Moves(new int[]{0, 0, 0, -1, -1}, this.getColor());
        setName("Bishop");
    }

    public String toString(){
        return this.generateName();
    }

    public ArrayList<int[]> getMoves(ChessPiece[][] board){
        return moves.getValidMoves(board, this.getPosition());

    }

    public ArrayList<int[]> getAttacks(ChessPiece[][] board){
        return moves.getValidAttacks(board, this.getPosition());
    }

    public String generateName(){
        return(getPieceColor() + getName() + " @ " + getPositionOnBoard());
    }

}
