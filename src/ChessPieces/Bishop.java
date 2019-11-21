package ChessPieces;

public class Bishop extends ChessPiece {

    public Bishop(int[] position, Color color){
        super("Bishop image", position, color, 1, 1, 1, 1, 1);
    }

    public int[][] getMoves(){
        int[][] moves = new int[8][2];  // [up, down, left, right, leftUp, rightUp, leftDown, rightDown][column, row]
        return moves;
    }

    public int[][] getAttacks(){
        return this.getMoves();  // Bishop can attack in any direction he can move
    }

}
