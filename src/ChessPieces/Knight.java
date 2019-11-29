package ChessPieces;

import java.util.ArrayList;

public class Knight extends ChessPiece {

    private Moves moves;

    public Knight(int[] position, Color color){
        super("https://i.imgur.com/0NJgyIC.png", position, color);
        moves = new Moves(updateDesiredMoves(), this.getColor());
        setName("Knight");
    }

    public String toString(){
        return this.generateName();
    }

    @Override
    public void setPosition(int[] position){
        super.setPosition(position);
        moves = new Moves(updateDesiredMoves(), this.getColor());
    }

    public ArrayList<int[]> updateDesiredMoves(){
        ArrayList<int[]> desiredMoves = new ArrayList<>();

        desiredMoves.add(new int[]{getPosition()[0] + 1, getPosition()[1] + 2});
        desiredMoves.add(new int[]{getPosition()[0] + 2, getPosition()[1] + 1});
        desiredMoves.add(new int[]{getPosition()[0] - 1, getPosition()[1] + 2});
        desiredMoves.add(new int[]{getPosition()[0] - 2, getPosition()[1] + 1});
        desiredMoves.add(new int[]{getPosition()[0] + 1, getPosition()[1] - 2});
        desiredMoves.add(new int[]{getPosition()[0] + 2, getPosition()[1] - 1});
        desiredMoves.add(new int[]{getPosition()[0] - 1, getPosition()[1] - 2});
        desiredMoves.add(new int[]{getPosition()[0] - 2, getPosition()[1] - 1});

        return desiredMoves;
    }

    public ArrayList<int[]> getMoves(ChessPiece[][] board){
        return moves.getValidMoves(board, getPosition());
    }

    public ArrayList<int[]> getAttacks(ChessPiece[][] board){
        return moves.getValidAttacks(board, getPosition());
    }

    public String generateName(){
        return (getPieceColor() + getName() + " @ " + getPositionOnBoard());
    }

}
