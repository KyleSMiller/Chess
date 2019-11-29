package ChessPieces;

import java.util.ArrayList;

public class Pawn extends ChessPiece {

    private Moves standardMoves;
    private Moves starterMoves;  // pawn has different movement options at start of game
    private Moves attacks; // pawn cannot attack in the directions it moves
    private boolean hasMoved;

    public Pawn(int[] position, Color color){
        super("https://i.imgur.com/0NJgyIC.png", position, color);
        hasMoved = false;
        if(color == Color.WHITE) {
            standardMoves = new Moves(new int[]{1, 0, 0, 0, 0}, this.getColor());
            starterMoves = new Moves(new int[]{2, 0, 0, 0, 0}, this.getColor());
            attacks = new Moves(new int[]{0, 0, 0, 1, 0}, this.getColor());
        }
        else{
            standardMoves = new Moves(new int[]{0, 1, 0, 0, 0}, this.getColor());
            starterMoves = new Moves(new int[]{0, 2, 0, 0, 0}, this.getColor());
            attacks = new Moves(new int[]{0, 0, 0, 0, 1}, this.getColor());
        }
        setName("Pawn");
    }

    public String toString(){
        return this.generateName();
    }

    @Override
    public void setPosition(int[] newPosition){
        this.hasMoved = true;
        super.setPosition(newPosition);
    }

    public ArrayList<int[]> getMoves(ChessPiece[][] board){
        if(hasMoved){
            return this.standardMoves.getValidMoves(board, this.getPosition());
        }
        else{
            return this.starterMoves.getValidMoves(board, this.getPosition());
        }
    }

    public ArrayList<int[]> getAttacks(ChessPiece[][] board){
        return this.attacks.getValidAttacks(board, this.getPosition());
    }

    public String generateName(){
        return (getPieceColor() + getName() + " @" + getPositionOnBoard());
    }

}
