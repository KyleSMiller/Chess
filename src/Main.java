import Chat.ChatServer;
import Chat.ChatWindow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {

        primaryStage.setResizable(false);

        ChessBoard chessBoard = new ChessBoard(500);
        chessBoard.play();


        ChatWindow chatWindow = new ChatWindow(200, 500);
        chatWindow.run("White");

        BorderPane borderPane = new BorderPane();
        StackPane centerStack = new StackPane();
        centerStack.getChildren().add(new Rectangle(500, 500));
        centerStack.getChildren().add(chessBoard);
        borderPane.setCenter(centerStack);
        borderPane.setRight(chatWindow);

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.show();



//        new Thread() {
//            public void run() {
//                ChatServer chatServer = new ChatServer(7777);
//                chatServer.runServer();
//            }
//        }.start();
    }

}