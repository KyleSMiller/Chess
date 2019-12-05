package ChessPieces;

import java.util.ArrayList;

public class Bishop extends ChessPiece {

    private Moves moves;

    public Bishop(int[] position, Color color){
        super("https://i.imgur.com/0NJgyIC.png", position, color);
        moves = new Moves(new int[]{0, 0, 0, -1, -1}, this.getColor());

        if(this.getColor() == Color.BLACK){
            this.setImage("https://i.imgur.com/TcMrkbQ.png");
        }
        else{
            this.setImage("https://i.imgur.com/URqjsi9.png");
        }

    }

    public String toString(){
        this.generateName();
        return this.getName;
    }

    public ArrayList<int[]> getMoves(ChessPiece[][] board){
        return moves.getValidMoves(board, this.getPosition());

    }

    public ArrayList<int[]> getAttacks(ChessPiece[][] board){
        return moves.getValidAttacks(board, this.getPosition());
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

        this.setName(color + " Bishop @" + column + row);
    }

}
