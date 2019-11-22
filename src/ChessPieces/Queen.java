package ChessPieces;

public class Queen extends ChessPiece {

    public Queen(int[] position, Color color){
        super("https://i.imgur.com/0NJgyIC.png", position, color);
    }

    public int[] getMoveRange(){
        return new int[] {-1, -1, -1, -1, -1, -1, -1, -1};  // [up, down, left, right, leftUp, rightUp, leftDown, rightDown]
    }

    public int[] getAttackRange(){
        return this.getMoveRange();  // Queen can attack in any direction she can move
    }

}
