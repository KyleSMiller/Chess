package ChessPieces;

public class King extends ChessPiece {

    public King(int[] position, Color color){
        super("https://i.imgur.com/0NJgyIC.png", position, color);
    }

    public int[] getMoveRange(){
        return new int[] {1, 1, 1, 1, 1, 1, 1, 1};  // [up, down, left, right, leftUp, rightUp, leftDown, rightDown]
    }

    public int[] getAttackRange(){
        return this.getMoveRange();  // King can attack in any direction he can move
    }

}
