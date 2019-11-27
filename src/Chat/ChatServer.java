package Chat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ChatServer {

    private int port;
    private ServerSocket sv;
    private BufferedWriter wr;
    private BufferedReader rd;
    private Scanner scanner;
    private Socket socket;
    private String response;
    private String message;


    public ChatServer(int port){
        this.port = port;

    }

    public void runServer(){
        try {
            sv = new ServerSocket(port);
        } catch (Exception e){
            System.out.println("Port taken, please try a different port");
        }

        while(true){
            try { tryForConnection();
            } catch (IOException e) { System.out.println("Cannot establish connection"); }

            while(true){
                readMessage();
                sendMessage();
            }
        }
    }

    private void tryForConnection() throws IOException{
        System.out.println("Waiting for client connection: ");
        socket = sv.accept();
        rd = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        wr = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        scanner = new Scanner(System.in);
        System.out.println("Client connected");
    }

    private void readMessage() {
        try {
            response = rd.readLine().trim();
            System.out.println("From Client: " + response);
        } catch (IOException e) {
            System.out.println("failed to read message");
        }
    }

    private void sendMessage() {
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