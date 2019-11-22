package ChessPieces;

public class Pawn extends ChessPiece {

    private boolean hasMoved;

    public Pawn(int[] position, Color color){
        super("https://i.imgur.com/0NJgyIC.png", position, color);
        hasMoved = false;
    }

    @Override
    public void setPosition(int[] newPosition){
        this.hasMoved = true;
        super.setPosition(newPosition);
    }

    public int[] getMoveRange(){
        // [up, down, left, right, leftUp, rightUp, leftDown, rightDown]
        int [] startingMoves = new int[] {-2, 0, 0, 0, 0, 0, 0, 0};
        int[] normalMoves = new int[] {1, 0, 0, 0, 0, 0, 0, 0};
        if(hasMoved){
            return normalMoves;
        }
        else{
            return startingMoves;
        }
    }

    public int[] getAttackRange(){
        return new int[] {0, 0, 0, 0, 1, 1, 0, 0};
    }

}
