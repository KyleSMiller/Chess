import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class ChessPiece {

    private String image;
    private int[] position;
    private Color color;

    private int forward;
    private int backwards;
    private int side;
    private int diagonalUp;
    private int diagonalDown;

    private ImageView imageView;

    public ChessPiece(String image, int[] position, Color color, int forward, int backwards, int side, int diagonalUp, int diagonalDown){
        this.image = image;
        this.position = position;  // [column, row]
        this.color = color;

        this.forward = forward;
        this.backwards = backwards;
        this.side = side;
        this.diagonalUp = diagonalUp;
        this.diagonalDown = diagonalDown;

        imageView = new ImageView(new Image(this.image));
    }

    public String getImage(){
        return image;
    }

    public ImageView getImageView(){
        return imageView;
    }

    public int[] getPosition(){
        return position;
    }

    public Color color(){
        return color;
    }

    public int getForward() {
        return forward;
    }

    public int getBackwards() {
        return backwards;
    }

    public int getSide() {
        return side;
    }

    public int getDiagonalUp() {
        return diagonalUp;
    }

    public int getDiagonalDown() {
        return diagonalDown;
    }

    public abstract int[][] getMoves();

    public abstract int[][] getAttacks();

    public void setPosition(int[] newPosition){
        this.position = newPosition;
    }

    /**
     * Fade the piece away
     */
    public void fadeOut(){

    }

    public enum Color{
        WHITE, BLACK
    }

}
