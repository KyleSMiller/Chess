package ChessPieces;

public class Rook extends ChessPiece{

    public Rook(int[] position, Color color){
        super("https://i.imgur.com/0NJgyIC.png", position, color);
    }

    public int[] getMoveRange(){
        return new int[] {-1, -1, -1, -1, 0, 0, 0, 0};  // [up, down, left, right, leftUp, rightUp, leftDown, rightDown]
    }

    public int[] getAttackRange(){
        return this.getMoveRange();  // Rook can attack in any direction it can move
    }

}
