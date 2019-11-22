package ChessPieces;

public class Bishop extends ChessPiece {

    public Bishop(int[] position, Color color){
        super("https://i.imgur.com/0NJgyIC.png", position, color);
    }

    public int[] getMoveRange(){
        return new int[] {0, 0, 0, 0, -1, -1, -1, -1};  // [up, down, left, right, leftUp, rightUp, leftDown, rightDown]
    }

    public int[] getAttackRange(){
        return this.getMoveRange();  // Bishop can attack in any direction he can move
    }

}
