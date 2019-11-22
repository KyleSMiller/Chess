package ChessPieces;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public abstract class ChessPiece {

    private String image;
    private int[] position;
    private Color color;

    private ImageView imageView;

    public ChessPiece(String image, int[] position, Color color){
        this.image = image;
        this.position = position;  // [column, row]
        this.color = color;

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

    /**
     * Get the valid moves the piece can make.
     * Negative indicates that all squares along the path to the end of the move range are valid
     * -1 indicates infinite range with all squares along the way as valid moves
     * @return  [up, down, left, right, leftUp, rightUp, leftDown, rightDown] format array
     */
    public abstract int[] getMoveRange();

    /**
     * Get the valid attacks the piece can make.
     * Negative indicates that all squares along the path to the end of the attack range are valid
     * -1 indicates infinite range with all squares along the way as valid attacks spaces
     * @return  [up, down, left, right, leftUp, rightUp, leftDown, rightDown] format array
     */
    public abstract int[] getAttackRange();


    public void setPosition(int[] newPosition){
        this.position = newPosition;
    }

    public void setImage(String image){
        this.image = image;
        this.imageView = new ImageView(new Image(this.image));
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
