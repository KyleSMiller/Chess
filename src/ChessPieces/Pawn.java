package ChessPieces;

import java.util.ArrayList;

public class Pawn extends ChessPiece {

    private Moves standardMoves;
    private Moves starterMoves;  // pawn has different movement options at start of game
    private Moves attacks; // pawn cannot attack in the directions it moves
    private boolean hasMoved;

    public Pawn(int[] position, Color color){
        super("https://i.imgur.com/0NJgyIC.png", position, color);
        hasMoved = false;
        if(color == Color.WHITE) {
            standardMoves = new Moves(new int[]{1, 0, 0, 0, 0}, this.getColor());
            starterMoves = new Moves(new int[]{2, 0, 0, 0, 0}, this.getColor());
            attacks = new Moves(new int[]{0, 0, 0, 1, 0}, this.getColor());
        }
        else{
            standardMoves = new Moves(new int[]{0, 1, 0, 0, 0}, this.getColor());
            starterMoves = new Moves(new int[]{0, 2, 0, 0, 0}, this.getColor());
            attacks = new Moves(new int[]{0, 0, 0, 0, 1}, this.getColor());
        }

        if(this.getColor() == Color.BLACK){
            this.setImage("https://i.imgur.com/CHMqrBg.png");
        }
        else{
            this.setImage("https://i.imgur.com/AECadnb.png");
        }

    }

    public String toString(){
        this.generateName();
        return this.getName;
    }

    @Override
    public void setPosition(int[] newPosition){
        this.hasMoved = true;
        super.setPosition(newPosition);
    }

    public ArrayList<int[]> getMoves(ChessPiece[][] board){
        if(hasMoved){
            return this.standardMoves.getValidMoves(board, this.getPosition());
        }
        else{
            return this.starterMoves.getValidMoves(board, this.getPosition());
        }
    }

    public ArrayList<int[]> getAttacks(ChessPiece[][] board){
        return this.attacks.getValidAttacks(board, this.getPosition());
    }

    public void generateName(){
        String column;
        String row;
        String color;
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
        switch (getColor()){
            case WHITE: color = "White"; break;
            case BLACK: color = "Black"; break;
            default: color = "White";
        }

        this.setName(color + " Pawn @" + column + row);
    }

}
