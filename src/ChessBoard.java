import ChessPieces.ChessPiece;
import ChessPieces.King;
import ChessPieces.Queen;
import ChessPieces.Bishop;
import ChessPieces.Knight;
import ChessPieces.Rook;
import ChessPieces.Pawn;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.exit;


public class ChessBoard extends StackPane {

    // board data
    private final int ROWS = 8;
    private final int COLUMNS = 8;
    private ChessPiece[][] board = new ChessPiece[COLUMNS][ROWS];
    // panes and nodes
    private ArrayList<ArrayList<Rectangle>> selections;
    private GridPane background = new GridPane();
    private GridPane pieceGrid = new GridPane();
    private GridPane selectionGrid = new GridPane();
    // sizing variables
    private int size;
    private int cellSize;
    // active game data
    private ChessPiece activePiece = null;
    private ArrayList<int[]> validMoves = new ArrayList<>();
    private boolean whiteTurn = true;
    private boolean isWhite;
    // multiplayer data


    /**
     * Create a new chessboard
     * @param size  the size in pixels of the board
     * @param team  true to pick white team, false to pick black
     */
    public ChessBoard(int size, boolean team){
        this.size = size;
        this.cellSize = size / COLUMNS;

        // add the checkered background, piece grid, and selections grid to the pane
        super.getChildren().add(this.background);
        Insets gridPadding = new Insets(5, 5, 5, 5);
        this.background.setPadding(gridPadding);
        this.pieceGrid.setPadding(gridPadding);
        this.selectionGrid.setPadding(gridPadding);
        super.getChildren().add(pieceGrid);
        // create the selection rectangles
        this.selections = createSelections();
        for(int column = 0; column < COLUMNS; column++){
            for(int row = 0; row < ROWS; row++){
                this.selectionGrid.add(selections.get(column).get(row), column, row);
            }
        }
        super.getChildren().add(this.selectionGrid);

        createBoard();
        addPieces();

        isWhite = team;
    }

    public void play(){
        gameLoop();
    }

    private ServerSocket sv;
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private Scanner scanner;

    public void createServer(int port){
        try{
            this.sv = new ServerSocket(port);
            System.out.println("Waiting for opponent...");
            this.socket = sv.accept();
            System.out.println("Opponent found");
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.scanner = new Scanner(System.in);
        } catch(IOException e){
            System.out.println("Cannot open server on port " + port);
        }
    }

    public void connectToServer(String ip, int port){
        try {
            System.out.println("Connecting to opponent...");
            socket = new Socket(ip, port);
            System.out.println("Connected");
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch(IOException e){
            System.out.println("Cannot connect to server " + ip + ":" + port);
        }
    }



    private ArrayList<ArrayList<Rectangle>> getSelections() {
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
        for (int column = 0; column < this.board.length; column++){
            for (int row = 0; row < this.board[column].length; row++){
                // set up the array
                this.board[column][row] = null;
                // set up the background
                Rectangle cell = new Rectangle(cellSize, cellSize);
                if((column + row) % 2 == 0){
                    cell.setFill(Color.rgb(209, 201, 167));  // white
                }
                else{
                    cell.setFill(Color.rgb(33, 23, 1));  // black
                }
                this.background.add(new StackPane(cell), column, row);
                // set up the width of the piece pieceGrid cells
                Rectangle empty = new Rectangle(cellSize, cellSize);
                empty.setFill(Color.TRANSPARENT);
                this.pieceGrid.add(empty, column, row);
            }
        }
    }

    /**
     * Add all chess pieces in default position to the board
     */
    private void addPieces(){
        // back row of black pieces
        this.board[0][0] = new Rook(new int[] {0, 0}, ChessPiece.Color.BLACK);
        this.board[1][0] = new Knight(new int[] {1, 0}, ChessPiece.Color.BLACK);
        this.board[2][0] = new Bishop(new int[] {2, 0}, ChessPiece.Color.BLACK);
        this.board[3][0] = new Queen(new int[] {3, 0}, ChessPiece.Color.BLACK);
        this.board[4][0] = new King(new int[] {4, 0}, ChessPiece.Color.BLACK);
        this.board[5][0] = new Bishop(new int[] {5, 0}, ChessPiece.Color.BLACK);
        this.board[6][0] = new Knight(new int[] {6, 0}, ChessPiece.Color.BLACK);
        this.board[7][0] = new Rook(new int[] {7, 0}, ChessPiece.Color.BLACK);
        // black pawns
        for(int column = 0; column < COLUMNS; column++){
            this.board[column][1] = new Pawn(new int[] {column, 1}, ChessPiece.Color.BLACK);
        }

        // back row of white pieces
        this.board[0][ROWS - 1] = new Rook(new int[] {0, ROWS - 1}, ChessPiece.Color.WHITE);
        this.board[1][ROWS - 1] = new Knight(new int[] {1, ROWS - 1}, ChessPiece.Color.WHITE);
        this.board[2][ROWS - 1] = new Bishop(new int[] {2, ROWS - 1}, ChessPiece.Color.WHITE);
        this.board[3][ROWS - 1] = new Queen(new int[] {3, ROWS - 1}, ChessPiece.Color.WHITE);
        this.board[4][ROWS - 1] = new King(new int[] {4, ROWS - 1}, ChessPiece.Color.WHITE);
        this.board[5][ROWS - 1] = new Bishop(new int[] {5, ROWS - 1}, ChessPiece.Color.WHITE);
        this.board[6][ROWS - 1] = new Knight(new int[] {6, ROWS - 1}, ChessPiece.Color.WHITE);
        this.board[7][ROWS - 1] = new Rook(new int[] {7, ROWS - 1}, ChessPiece.Color.WHITE);
        // white pawns
        for(int column = 0; column < COLUMNS; column++){
            this.board[column][ROWS - 2] = new Pawn(new int[] {column, ROWS - 2}, ChessPiece.Color.WHITE);
        }

        // display the pieces on the board
        for(int column = 0; column < COLUMNS; column++){
            for(int row = 0; row < ROWS; row++){
                if(board[column][row] != null){
                    this.board[column][row].getImageView().setFitHeight(cellSize);
                    this.board[column][row].getImageView().setFitWidth(cellSize);
                    pieceGrid.add(board[column][row].getImageView(), column, row);
                }
            }
        }
    }

    /**
     * update the visual board to match the array representation of the board
     */
    private void updateBoard(){
        for(int column = 0; column < COLUMNS; column++){
            for(int row = 0; row < ROWS; row++){
                if(board[column][row] != null){
                    pieceGrid.getChildren().remove(board[column][row].getImageView());
                    pieceGrid.add(board[column][row].getImageView(), column, row);
                }
            }
        }
    }

    private Paint cellColor;  // because of lambda limitations, cellColor must be a member variable. I know, it hurts me too
    /**
     * Cell highlighting on mouse-over
     */
    private void doHighlight(){
        for (ArrayList<Rectangle> column : this.getSelections()) {
            for (Rectangle rect : column) {
                rect.setOnMouseEntered(e -> {
                    cellColor = rect.getFill();
                    this.highlightCell(rect, cellColor);
                });
                rect.setOnMouseExited(e -> {
                    this.removeHighlight(rect, cellColor);
                });
            }
        }
    }

    /**
     * Highlight a column on the board
     * @param rectangle  the selection rectangle to highlight
     */
    private void highlightCell(Rectangle rectangle, Paint cellColor){
        int[] index = getIndex(rectangle);
        int col = index[0];
        int row = index[1];

        if(cellColor == Color.GREEN){
            this.selections.get(col).get(row).setFill(Color.rgb(143, 196, 157));
            this.selections.get(col).get(row).setOpacity(0.25);
        }
        if(cellColor == Color.RED) {
            this.selections.get(col).get(row).setFill(Color.rgb(201, 153, 153));
            this.selections.get(col).get(row).setOpacity(0.25);
        }
        else if (cellColor == Color.TRANSPARENT){
            this.selections.get(col).get(row).setFill(Color.WHITE);
            this.selections.get(col).get(row).setOpacity(0.25);
        }
    }

    /**
     * Remove the highlight from a column
     * @param rectangle  the selection rectangle to make transparent
     */
    private void removeHighlight(Rectangle rectangle, Paint cellColor){
        int col = 0;
        int row = 0;
        for(int column = 0; column < selections.size(); column++){
            if(selections.get(column).contains(rectangle)){
                col = column;
                row = selections.get(column).indexOf(rectangle);
            }
        }
        this.selections.get(col).get(row).setFill(cellColor);  // return to original cellColor
    }

    /**
     * Highlight the possible move and attack squares a piece can legally go to
     * @param piece  the piece to display the moves of
     */
    private void highlightMoves(ChessPiece piece){
        ArrayList<int[]> validMoves = piece.getMoves(this.board);
        for(int i = 0; i < validMoves.size(); i++){  // highlight moves
            this.selections.get(validMoves.get(i)[0]).get(validMoves.get(i)[1]).setFill(Color.GREEN);
            this.selections.get(validMoves.get(i)[0]).get(validMoves.get(i)[1]).setOpacity(0.25);
        }
        ArrayList<int[]> validAttacks = piece.getAttacks(this.board);
        for(int i = 0; i < validAttacks.size(); i++){  // highlight attacks
            this.selections.get(validAttacks.get(i)[0]).get(validAttacks.get(i)[1]).setFill(Color.RED);
            this.selections.get(validAttacks.get(i)[0]).get(validAttacks.get(i)[1]).setOpacity(0.25);
        }

        this.validMoves = validMoves;
        this.validMoves.addAll(validAttacks);

    }

    /**
     * Remove the valid move highlights from the board
     */
    private void removeMoveHighlight(){
        for(ArrayList<Rectangle> column : selections){
            for(Rectangle rect : column){
                rect.setFill(Color.TRANSPARENT);
            }
        }
    }

    /**
     * Select a piece in a given cell
     * @param cell  the selections rectangle that shares the cell with the piece to select
     */
    private void selectPiece(Rectangle cell) {
        int[] pieceIndex = getIndex(cell);
        ChessPiece piece = board[pieceIndex[0]][pieceIndex[1]];
        if(piece == null) return;

        if (piece.getColor() == ChessPiece.Color.WHITE && isWhite
            || piece.getColor() == ChessPiece.Color.BLACK && !isWhite) {
            if (piece != activePiece) {
                removeMoveHighlight();
                activePiece = piece;
                highlightMoves(piece);
            } else {
                removeMoveHighlight();
                activePiece = null;
            }
            // System.out.println("Selected " + activePiece);

        }
    }

    /**
     * Get the index of a cell on the board
     * Allows getting the index of cells that are clicked on or hovered over
     * @param selectionRect  the selectionsRectangle that shares the space on the board with the desired index
     * @return  the index of the cell
     */
    private int[] getIndex(Rectangle selectionRect){
        int col = 0;
        int row = 0;
        for(int i = 0; i < selections.size(); i++){
            if(selections.get(i).contains(selectionRect)){
                col = i;
                row = selections.get(col).indexOf(selectionRect);
            }
        }
        return new int[]{col, row};
    }

    /**
     * Check if the position the user chose to move is a valid space to move
     * @param position  the desired position
     * @return          true if valid, false if not
     */
    private boolean canMove(ChessPiece piece, int[] position){
        for(int[] pos : validMoves){
            if(isWhite != whiteTurn){
                return false;
            }
            if((position[0] == piece.getPosition()[0] && position[1] == piece.getPosition()[1])){
                return false;
            }
            if((position[0] == pos[0] && position[1] == pos[1])){
                return true;
            }
        }
        return false;
    }

    /**
     * Move a piece on the board to another cell
     * @param piece          the piece to move
     * @param endPosition    the new position to move to
     */
    private void movePiece(ChessPiece piece, int[] endPosition){
        if(piece == null) return;
        if(!canMove(piece, endPosition)) return;
        int[] startPosition = piece.getPosition();

        if(board[endPosition[0]][endPosition[1]] != null){  // if space occupied
            pieceGrid.getChildren().remove(board[endPosition[0]][endPosition[1]].getImageView());
            board[endPosition[0]][endPosition[1]] = null;
        }

        board[endPosition[0]][endPosition[1]] = piece;
        piece.setPosition(new int[]{endPosition[0], endPosition[1]});
        board[startPosition[0]][startPosition[1]] = null;
        updateBoard();
        removeMoveHighlight();
        whiteTurn = !whiteTurn;
    }

    private JSONObject convertBoardToJson(){
        JSONObject boardJson = new JSONObject();
        for(int column = 0; column < board.length; column++){
            for(int row = 0; row < board[column].length; row++){
                try {
                    String piecePosition = column + " " + row;
                    if (board[column][row] == null) {
                        boardJson.put(piecePosition, "EMPTY");
                    } else {
                        String piece = board[column][row].getPieceColor() + " " + board[column][row].getName();
                        boardJson.put(piecePosition, piece);
                    }
                } catch(JSONException e){
                    System.out.println("Cannot put piece at " + column + ", " + row + " in Json object");
                }
            }
        }
        return boardJson;
    }

    private ChessPiece[][] convertJsonToBoard(JSONObject boardJson){
        ChessPiece[][] board = new ChessPiece[COLUMNS][ROWS];
        for(int column = 0; column < board.length; column++){
            for(int row = 0; row < board[column].length; row++){
                try{
                    String piecePosition = column + " " + row;
                    String pieceInfo = (String) boardJson.get(piecePosition);

                    if(pieceInfo.equals("EMPTY")){
                        board[column][row] = null;
                        continue;
                    }

                    String pieceColor = pieceInfo.split("\\s+")[0];
                    String pieceType = pieceInfo.split("\\s+")[1];
                    ChessPiece.Color color;

                    if(pieceColor.equals("White")){
                        color = ChessPiece.Color.WHITE;
                    }
                    else{
                        color = ChessPiece.Color.BLACK;
                    }

                    switch(pieceType){
                        case "King": board[column][row] = new King(new int[]{column, row}, color); break;
                        case "Queen": board[column][row] = new Queen(new int[]{column, row}, color); break;
                        case "Bishop": board[column][row] = new Bishop(new int[]{column, row}, color); break;
                        case "Knight": board[column][row] = new Knight(new int[]{column, row}, color); break;
                        case "Rook": board[column][row] = new Rook(new int[]{column, row}, color); break;
                        case "Pawn": board[column][row] = new Pawn(new int[]{column, row}, color); break;
                        default: board[column][row] = null;
                    }


                } catch(JSONException e){
                    System.out.println("Error reading in board sent by opponent");
                }
            }
        }
        return board;
    }


    /**
     * Wait for a Mouse event and perform the according action on the board
     */
    private void gameLoop(){
        boolean playGame = true;

        doHighlight();
        for (ArrayList<Rectangle> column : this.getSelections()){
            for (Rectangle rect : column){
                int[] position = getIndex(rect);
                rect.setOnMousePressed(e -> {
                    movePiece(activePiece, position);
                    selectPiece(rect);
                });
            }
        }

        if(this.socket == null){
            System.out.println("You must open or connect to a server before starting the game");
            exit(-1);
        }
        else{
            new Thread(() -> {
                while(playGame) {
                    if (isWhite) {  // white goes first
                        // send move
                        // read move
                    } else {
                        // read move
                        // send move
                    }
                }
            }).start();
        }

    }

}
