package MainServer;

import java.io.IOException;
import java.net.ServerSocket;

public class MainServer {
    private static final int PORT = 6969;

    public static void main(String args[]) throws IOException {
        System.out.println("Starting server on port " +PORT + "...");
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e){
            System.out.println("Could not listen on port: " + PORT);
            System.exit(-1);
        }

        while(true) {
            HandlerServer handlerServer;
            try {
                handlerServer = new HandlerServer(serverSocket.accept());
                Thread thread = new Thread(handlerServer);
                thread.start();
            } catch (IOException e) {
                System.out.println("Accept failed: " + PORT);
            }
        }
    }
}
