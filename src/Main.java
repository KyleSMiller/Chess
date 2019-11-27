import Chat.ChatServer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {

        primaryStage.setResizable(false);

        ChessBoard chessBoard = new ChessBoard(500);
        chessBoard.play();

        Scene scene = new Scene(chessBoard);
        primaryStage.setScene(scene);
        primaryStage.show();

        new Thread() {
            public void run() {
                ChatServer chatServer = new ChatServer(7777);
                chatServer.runServer();
            }
        }.start();
    }

}