import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;

public class ChessBoard extends StackPane {

    private final int ROWS = 8;
    private final int COLUMNS = 8;
    private int size;
    private int cellSize;

    private int[][] board = new int[COLUMNS][ROWS];


    GridPane grid = new GridPane();

    public ChessBoard(int size){
        this.size = size;
    }

    /**
     * Create an indexed layer of rectangles over each cell on the board
     * @return  the rectangles
     */
    private ArrayList<ArrayList<Integer>> createSelections(){

    }

    /**
     * Create a chess board with all pieces in starting position
     */
    private void createBoard(){

    }

    /**
     * update the visual board to match the array representation of the board
     */
    private void updateBoard(){

    }

    /**
     * Toggle piece highlighting on mouse-over
     * @param highlight  true if highlighting active, false if not
     */
    private void doPieceHighlight(boolean highlight){

    }

    /**
     * Toggle highlighting of possible moves for selected piece
     * @param highlight  true if highlighting active, false if not
     */
    private void doMoveHighlight(boolean highlight){

    }

    /**
     * Select a piece in a given cell
     * @param column  the column of the piece
     * @param row     the row of the piece
     */
    private void selectPiece(int column, int row){

    }


    /**
     * Wait for a Mouse event and perform the according action on the board
     */
    private void gameLoop(){

    }

}
