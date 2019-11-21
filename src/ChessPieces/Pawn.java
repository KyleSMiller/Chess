package ChessPieces;

public class Pawn extends ChessPiece {

    public Pawn(int[] position, Color color){
        super("Pawn image", position, color, 1, 1, 1, 1, 1);
    }

    public int[][] getMoves(){
        int[][] moves = new int[8][2];  // [up, down, left, right, leftUp, rightUp, leftDown, rightDown][column, row]
        return moves;
    }

    public int[][] getAttacks(){
        int[][] attacks = new int[8][2];
        return attacks;
    }

}
