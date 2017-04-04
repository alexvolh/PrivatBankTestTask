package DepositClient;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class DepositClient {
    public static void main(String[] args) {
        Socket socket;
        PrintWriter out = null;
        BufferedReader in = null;
        String fromServer;
        String fromUser;
        try {
            socket = new Socket("localhost",6969);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            System.out.println("Could not connect to the server ...");
            e.printStackTrace();
        }
        try {
            if ((fromServer = in.readLine()) != null){
                System.out.println(fromServer);
            }
            do {
                fromUser = MenuClient.DisplayMenu();
                if (fromUser != null){
                    assert out != null;
                    out.println(fromUser);
                    System.out.println("client: " + fromUser);
                    if (fromUser.equals("exit")) {
                        System.exit(-1);
                    }
                }
                if ((fromServer = in.readLine()) != null){
                        if (fromServer.length() > 12 && fromServer.substring(3, 12).equals("accountId")) {
                            Gson gson = new GsonBuilder().setPrettyPrinting().create();
                            JsonParser jsonParser = new JsonParser();
                            JsonElement jsonElement = jsonParser.parse(fromServer);
                            String toJson = gson.toJson(jsonElement);
                            System.out.println(toJson);
                        } else {
                            System.out.println("server: " + fromServer);
                        }
                }
                System.out.println();
            } while (true);

        }catch (IOException e) {
            System.out.println("I/O error ...");
            e.printStackTrace();
        }
    }
}
