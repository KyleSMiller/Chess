import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
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
    private GridPane selectionGrid = new GridPane();
    // sizing variables
    private int size;
    private int cellSize;


    public ChessBoard(int size){
        this.size = size;
        this.cellSize = size / COLUMNS;
        super.getChildren().add(this.background);
        Insets gridPadding = new Insets(5, 5, 5, 5);
        this.background.setPadding(gridPadding);
        this.grid.setPadding(gridPadding);
        this.selectionGrid.setPadding(gridPadding);
        createBoard();

        this.selections = createSelections();
        for(int column = 0; column < COLUMNS; column++){
            for(int row = 0; row < ROWS; row++){
                this.selectionGrid.add(selections.get(column).get(row), column, row);
            }
        }
        super.getChildren().add(this.selectionGrid);

        addPieces();
    }

    public void play(){
        doHighlight();
    }


    public ArrayList<ArrayList<Rectangle>> getSelections() {
        return selections;
    }

    /**
     * Create an indexed layer of rectangles over each cell on the board
     * @return  the rectangles
     */
    private ArrayList<ArrayList<Rectangle>> createSelections(){
        ArrayList<ArrayList<Rectangle>> rects = new ArrayList<>();
        for (int column = 0; column < this.board.length; column++){
            rects.add(new ArrayList<>());
            for (int row = 0; row < this.board[column].length; row++){
                rects.get(column).add(new Rectangle(this.size / COLUMNS, this.size / ROWS, Color.TRANSPARENT));
            }
        }
        return rects;
    }

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
                    cell.setFill(Color.rgb(209, 201, 167));  // white
                }
                else{
                    cell.setFill(Color.rgb(33, 23, 1));  // black
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
     * Cell highlighting on mouse-over
     */
    private void doHighlight(){
        for (ArrayList<Rectangle> column : this.getSelections()) {
            for (Rectangle rect : column) {
                rect.setOnMouseEntered(e -> this.highlightCell(rect));
                rect.setOnMouseExited(e -> this.removeHighlight(rect));
            }
        }
    }

    /**
     * Highlight a column on the board
     * @param rectangle  the selection rectangle to highlight
     */
    private void highlightCell(Rectangle rectangle){
        int col = 0;
        int row = 0;
        for(int column = 0; column < selections.size(); column++){
            if(selections.get(column).contains(rectangle)){
                col = column;
                row = selections.get(column).indexOf(rectangle);
            }
        }
        this.selections.get(col).get(row).setFill(Color.WHITE);
        this.selections.get(col).get(row).setOpacity(0.25);
    }

    /**
     * Remove the highlight from a column
     * @param rectangle  the selection rectangle to make transparent
     */
    private void removeHighlight(Rectangle rectangle){//double hoverPosition){
        int col = 0;
        int row = 0;
        for(int column = 0; column < selections.size(); column++){
            if(selections.get(column).contains(rectangle)){
                col = column;
                row = selections.get(column).indexOf(rectangle);
            }
        }
        this.selections.get(col).get(row).setFill(Color.TRANSPARENT);
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
