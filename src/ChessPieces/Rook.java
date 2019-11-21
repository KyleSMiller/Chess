package ChessPieces;

public class Rook extends ChessPiece{

    public Rook(int[] position, Color color){
        super("Rook image", position, color, 1, 1, 1, 1, 1);
    }

    public int[][] getMoves(){
        int[][] moves = new int[8][2];  // [up, down, left, right, leftUp, rightUp, leftDown, rightDown][column, row]
        return moves;
    }

    public int[][] getAttacks(){
        return this.getMoves();  // Rook can attack in any direction it can move
    }

}
