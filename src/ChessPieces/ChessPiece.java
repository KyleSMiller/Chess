package ChessPieces;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public abstract class ChessPiece {

    private String image;
    private int[] position;
    private Color color;
    private String name;

    private ImageView imageView;

    public ChessPiece(String image, int[] position, Color color){
        this.image = image;
        this.position = position;  // [column, row]
        this.color = color;

        imageView = new ImageView(new Image(this.image));
    }

    public abstract String toString();

    public String getImage(){
        return image;
    }

    public ImageView getImageView(){
        return imageView;
    }

    public int[] getPosition(){
        return position;
    }

    public Color getColor(){
        return color;
    }

    public String getName;

    public abstract ArrayList<int[]> getMoves(ChessPiece[][] board);

    public abstract ArrayList<int[]> getAttacks(ChessPiece[][] board);

    public void setPosition(int[] newPosition){
        this.position = newPosition;
    }

    public void setName(String name){
        this.name = name;
    }

    public abstract void generateName();

    /**
     * Fade the piece away
     */
    public void fadeOut(){

    }

    public enum Color{
        WHITE, BLACK
    }

}
