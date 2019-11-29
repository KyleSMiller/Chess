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

    public String getPieceColor(){
        String color = "";
        switch (getColor()){
            case WHITE: color = "White"; break;
            case BLACK: color = "Black"; break;
            default: color = "White";
        }
        return color;
    }

    public String getName(){
        return this.name;
    }

    public abstract ArrayList<int[]> getMoves(ChessPiece[][] board);

    public abstract ArrayList<int[]> getAttacks(ChessPiece[][] board);

    public void setPosition(int[] newPosition){
        this.position = newPosition;
    }

    public void setName(String name){
        this.name = name;
    }

    public abstract String generateName();

    public String getPositionOnBoard(){
        String column;
        String row;
        switch (getPosition()[1]){
            case 0: column = "A"; break;
            case 1: column = "B"; break;
            case 2: column = "C"; break;
            case 3: column = "D"; break;
            case 4: column = "E"; break;
            case 5: column = "F"; break;
            case 6: column = "G"; break;
            case 7: column = "H"; break;
            default: column = "E";
        }
        switch (getPosition()[1]){
            case 0: row = "8"; break;
            case 1: row = "7"; break;
            case 2: row = "6"; break;
            case 3: row = "5"; break;
            case 4: row = "4"; break;
            case 5: row = "3"; break;
            case 6: row = "2"; break;
            case 7: row = "1"; break;
            default: row = "1";
        }

        return column + "" + row;

    }

    public enum Color{
        WHITE, BLACK
    }

}
