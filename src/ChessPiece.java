public abstract class ChessPiece {

    private String image;
    private int forward;
    private int backwards;
    private int left;
    private int right;
    private int diagonalUp;
    private int diagonalDown;

    public ChessPiece(String image, int forward, int backwards, int left, int right, int diagonalUp, int diagonalDown){
        this.image = image;
        this.forward = forward;
        this.backwards = backwards;
        this.left = left;
        this.right = right;
        this.diagonalUp = diagonalUp;
        this.diagonalDown = diagonalDown;
    }

    public String getImage(){
        return image;
    }

    public int getForward() {
        return forward;
    }

    public int getBackwards() {
        return backwards;
    }

    public int getLeft() {
        return left;
    }

    public int getRight() {
        return right;
    }

    public int getDiagonalUp() {
        return diagonalUp;
    }

    public int getDiagonalDown() {
        return diagonalDown;
    }

    public abstract int getMoves();

    public abstract int getAttacks();

    /**
     * Fade the piece away
     */
    public void fadeOut(){

    }

}
