package ChessPieces;

public class Knight extends ChessPiece {

    public Knight(int[] position, Color color){
        super("https://i.imgur.com/0NJgyIC.png", position, color);
    }

    public int[] getMoveRange(){
        return null;  // Knight's moves cannot be represented in a range
    }

    public int[] getAttackRange(){
        System.out.println("The Knight's moves cannot be represented an array of ranges");
        return null;
    }

}
