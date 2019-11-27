package Chat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {

    private int port;
    private Socket socket;
    private BufferedReader rd;
    private BufferedWriter wr;
    private Scanner scanner;
    private String response;
    private String message;


    public ChatClient(int port){
        this.port = port;
    }

    public void runServer(){
        try {
            socket = new Socket("192.168.0.16", port);
            rd = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            wr = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            scanner = new Scanner(System.in);
        } catch (Exception e){
            System.out.println("ERROR: Cannot connect to host");
        }

        while(true){
            sendMessage();
            readMessage();
        }
    }

    private void readMessage() {
        try {
            response = rd.readLine().trim();
            System.out.println("From Client: " + response);
        } catch (IOException e) {
            System.out.println("failed to read message");
        }
    }

    private void sendMessage(){
        try {
            System.out.print("> ");
            message = scanner.nextLine();
            wr.write(message + "\r\n");
            wr.flush();
        } catch (IOException e){
            System.out.println("failed to send message");
        }
    }

}