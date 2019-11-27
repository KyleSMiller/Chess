package Chat;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class ChatWindow extends BorderPane {

    private int width;
    private int height;
    private ArrayList<String> messages = new ArrayList<>();
    private TextField msgField = new TextField();
    // private ArrayList<Label> messageLabels = new ArrayList<>();
    private VBox messageVbox = new VBox();
    ScrollPane messagePane = new ScrollPane();

    public ChatWindow(int width, int height){
        this.width = width;
        this.height = height;

        StackPane bottomStack = new StackPane();
        bottomStack.getChildren().add(msgField);
        msgField.setText("");
        msgField.setPadding(new Insets(5, 5, 5, 5));

        messagePane.setContent(messageVbox);

        super.setPadding(new Insets(5, 5, 5, 0));
        super.setWidth(this.width);
        super.setHeight(this.height);
        super.setCenter(messagePane);
        super.setBottom(bottomStack);
    }

    public void run(String username){
        msgField.textProperty().addListener(obs -> {});  // TODO: "user typing" message
        msgField.setOnAction(e -> {
            addUserMessage(username, msgField.getText());
            updateMessages();
            msgField.clear();
        });
    }

    public void addUserMessage(String user, String message){
        this.messages.add(user + ": " + message);
//        messageLabels.add(new Label(user + ": " + message));
    }

    private void updateMessages(){
        messageVbox.getChildren().clear();
        for(String msg : messages) {
            Label message = new Label(msg);
            message.setMaxWidth(width - 52);  // magic number, I know. It's late and I'm tired. Sue me
            message.setWrapText(true);
            messageVbox.getChildren().add(message);
        }
    }

}
