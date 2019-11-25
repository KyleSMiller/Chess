package ChessPieces;

import java.util.ArrayList;

public class Rook extends ChessPiece{

    private Moves moves;

    public Rook(int[] position, Color color){
        super("https://i.imgur.com/0NJgyIC.png", position, color);
        moves = new Moves(new int[]{-1, -1, -1, 0, 0}, this.getColor());
    }

    public ArrayList<int[]> getMoves(ChessPiece[][] board){
        return moves.getValidMoves(board, this.getPosition());
    }

    public ArrayList<int[]> getAttacks(ChessPiece[][] board){
        return moves.getValidAttacks(board, this.getPosition());

    }

}
