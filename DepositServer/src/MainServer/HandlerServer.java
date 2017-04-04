package MainServer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class HandlerServer implements Runnable {
    private Socket client;

    HandlerServer(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        String message;
        boolean isStoped = false;
        BufferedReader in = null;
        PrintWriter out = null;
        try{
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintWriter(client.getOutputStream(),true);
        }catch (IOException e){
            System.out.println("in or out faled");
            System.exit(-1);
        }
        out.println("Greetings you !");

        while(!isStoped){
            try {
                message = in.readLine();
                DepositObjOperation depositObjOperation = new DepositObjOperation();

                if (message != null) {

                    //add new deposit
                    if (message.length() > 11 && message.substring(2, 11).equals("accountId")) { //if string contains accountId
                        out.println(depositObjOperation.addDeposit(message));
                    }
                    //sum of all deposits
                    if (message.equals("sum")) {
                        out.println("the sum of all deposits: " + depositObjOperation.getAllSum());
                    }
                    //count of all deposits
                    if (message.equals("count")) {
                        out.println("the count of all deposits: " + depositObjOperation.getCountDeposits());
                    }
                    //account info
                    if (message.length() > 12 && message.substring(0, 12).equals("info account")) {
                        out.println(depositObjOperation.getInfoAccount(message.substring(12).trim()));
                    }
                    //depositor info
                    if (message.length() > 14 && message.substring(0, 14).equals("info depositor")) {
                        out.println(depositObjOperation.getInfoDepositor(message.substring(14).trim()));
                    }
                    //show type
                    if (message.length() > 9 && message.substring(0, 9).equals("show type")) {
                        out.println(depositObjOperation.getDepositByType(message.substring(10).trim()));
                    }
                    //show bank
                    if (message.length() > 9 && message.substring(0, 9).equals("show bank")) {
                        out.println(depositObjOperation.getDepositByBank(message.substring(10).trim()));
                    }
                    //delete account id
                    if (message.length() > 6 && message.substring(0, 6).equals("delete")) {
                        out.println(depositObjOperation.deleteAccount(message.substring(7).trim()));
                    }
                }
            if (message == null || message.equals("exit")) {
                System.out.println("close current client connection ...");
                isStoped =true;
            }

            }catch (IOException e){
                System.err.println();
                System.out.println("read failed");
                System.exit(-1);
            }
        }
    }
}
