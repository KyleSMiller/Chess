package ChessPieces;

public class King extends ChessPiece {

    public King(int[] position, Color color){
        super("https://i.imgur.com/0NJgyIC.png", position, color, 1, 1, 1, 1, 1);
    }

    public int[][] getMoves(){
        int[][] moves = new int[8][2];  // [up, down, left, right, leftUp, rightUp, leftDown, rightDown][column, row]
        // up
        moves[0][0] = getPosition()[0];
        moves[0][1] = getPosition()[1] - getForward();
        // down
        moves[1][0] = getPosition()[0];
        moves[1][1] = getPosition()[1] + getForward();
        // left
        moves[2][0] = getPosition()[0] - getSide();
        moves[2][1] = getPosition()[1];
        // right
        moves[3][0] = getPosition()[0] + getSide();
        moves[3][1] = getPosition()[1];
        // leftUp
        moves[4][0] = getPosition()[0] - getDiagonalUp();
        moves[4][1] = getPosition()[1] - getDiagonalUp();
        // rightUp
        moves[5][0] = getPosition()[0] + getDiagonalUp();
        moves[5][1] = getPosition()[1] - getDiagonalUp();
        // leftDown
        moves[6][0] = getPosition()[0] - getDiagonalDown();
        moves[6][1] = getPosition()[1] + getDiagonalDown();
        // rightDown
        moves[7][0] = getPosition()[0] + getDiagonalDown();
        moves[7][1] = getPosition()[1] + getDiagonalDown();

        return moves;
    }

    public int[][] getAttacks(){
        return this.getMoves(); // King can attack in any direction he can move
    }

}
