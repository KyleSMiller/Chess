package ChessPieces;

public class Queen extends ChessPiece {

    public Queen(int[] position, Color color){
        super("Queen image", position, color, 1, 1, 1, 1, 1);
    }

    public int[][] getMoves(){
        int[][] moves = new int[8][2];  // [up, down, left, right, leftUp, rightUp, leftDown, rightDown][column, row]
        return moves;
    }

    public int[][] getAttacks(){
        return this.getMoves();  // Queen can attack in any direction she can move
    }

}
