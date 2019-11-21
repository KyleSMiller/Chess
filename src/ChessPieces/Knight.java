package ChessPieces;

public class Knight extends ChessPiece {

    public Knight(int[] position, Color color){
        super("Knight image", position, color, 1, 1, 1, 1, 1);
    }

    public int[][] getMoves(){
        int[][] moves = new int[8][2];  // [up, down, left, right, leftUp, rightUp, leftDown, rightDown][column, row]
        return moves;
    }

    public int[][] getAttacks(){
        return this.getMoves();  // Knight can attack in any direction he can move
    }

}
