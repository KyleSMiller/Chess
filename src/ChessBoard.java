import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class ChessBoard extends StackPane {

    private final int ROWS = 8;
    private final int COLUMNS = 8;
    // panes and nodes
    private ChessPiece[][] board = new ChessPiece[COLUMNS][ROWS];
    private ArrayList<ArrayList<Rectangle>> selections;
    private GridPane background = new GridPane();
    private GridPane grid = new GridPane();
    // sizing variables
    private int size;
    private int cellSize;


    public ChessBoard(int size){
        this.size = size;
        this.cellSize = size / COLUMNS;
        super.setAlignment(Pos.CENTER);
        super.getChildren().add(this.background);
        this.background.setPadding(new Insets(5, 5, 5, 5));
        this.grid.setPadding(new Insets(5, 5, 5, 5));
        createBoard();
        addPieces();

    }

    /**
     * Create an indexed layer of rectangles over each cell on the board
     * @return  the rectangles
     */
//    private ArrayList<ArrayList<Integer>> createSelections(){
//
//    }

    /**
     * Create a blank chess board with no pieces
     */
    private void createBoard(){
        // set up the array
        for (int column = 0; column < this.board.length; column++){
            for (int row = 0; row < this.board[column].length; row++){
                this.board[column][row] = null;
            }
        }
        // set up the background
        for (int column = 0; column < this.board.length; column++){
            for (int row = 0; row < this.board[column].length; row++){
                Rectangle cell = new Rectangle(cellSize, cellSize);
                if((column + row) % 2 == 0){
                    cell.setFill(Color.WHITE);
                }
                else{
                    cell.setFill(Color.BLACK);
                }
                this.background.add(new StackPane(cell), column, row);
            }
        }
    }

    /**
     * Add all chess pieces in default position to the board
     */
    private void addPieces(){
        for (int column = 0; column < this.board.length; column++){
            for (int row = 0; row < this.board[column].length; row++){

            }
        }
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
