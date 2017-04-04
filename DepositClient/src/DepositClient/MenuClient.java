package DepositClient;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.org.apache.xpath.internal.SourceTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class MenuClient {
    static String DisplayMenu() {
        String result = "no choice";
        boolean isNotRight;
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

            System.out.printf("%-4s %-30s %-1s", "- 1", "|add|", "create a new deposit\n");
            System.out.printf("%-4s %-30s %-1s", "- 2", "|sum|", "sum of all deposits\n");
            System.out.printf("%-4s %-30s %-1s", "- 3", "|count|", "count of all deposits\n");
            System.out.printf("%-4s %-30s %-1s", "- 4", "|info account <account id>|", "information of personal account by id\n");
            System.out.printf("%-4s %-30s %-1s", "- 5", "|info depositor <depositor>|", "information of an investor\n");
            System.out.printf("%-4s %-30s %-1s", "- 6", "|show type <type>|", "list of all deposits of specified type (|DEMAND| |TERM| |SETTELMET| |ACCUMULATIVE| |SAVINGS| |METAL|)\n");
            System.out.printf("%-4s %-30s %-1s", "- 7", "|show bank <name>|", "list of all deposits of specified bank\n");
            System.out.printf("%-4s %-30s %-1s", "- 8", "|add <deposit info>|", "add additional information to deposit\n");
            System.out.printf("%-4s %-30s %-1s", "- 9", "|delete <account id>|", "remove specified id\n");
            System.out.printf("%-4s %-30s %-1s", "- 10","|exit|", "close program\n");

            try {
                do {
                    System.out.print("Enter command: ");
                    result = stdIn.readLine();
                    if (result.equals("add")) {
                        CreateDepositObj createDepositObj = new CreateDepositObj();
                        createDepositObj.run();

                        DepositObj depositObj = new DepositObj();
                        depositObj.setAccountId(createDepositObj.getId());
                        depositObj.setName(createDepositObj.getName());
                        depositObj.setCountry(createDepositObj.getCountry());
                        depositObj.setTypeDeposit(createDepositObj.getType());
                        depositObj.setDepositor(createDepositObj.getDepositor());
                        depositObj.setAmountDeposit(createDepositObj.getAmountDeposit());
                        depositObj.setProfitability(createDepositObj.getProfitability());
                        depositObj.setFinishDate(createDepositObj.getFinishDate());

                        Gson gson = new GsonBuilder().create();
                        result = gson.toJson(depositObj, DepositObj.class);
                        isNotRight = false;
                    } else if ( result.equals("sum") || result.equals("count") || result.equals("exit") ||
                                result.matches("info account\\s\\d*") ||
                                result.matches("info depositor\\s\\w*") ||
                                result.matches("info depositor\\s\\w*\\s\\w*") ||
                                result.matches("show type\\sDEMAND") ||
                                result.matches("show type\\sTERM") ||
                                result.matches("show type\\sTSETTELMET") ||
                                result.matches("show type\\sACCUMULATIVE") ||
                                result.matches("show type\\sSAVINGS") ||
                                result.matches("show type\\sMETAL") ||
                                result.matches("show bank\\s\\w*") ||
                                result.matches("delete\\s\\d*")) {
                        isNotRight = false;
                    } else {
                        System.out.println("wrong command !");
                        isNotRight = true;
                    }
                } while (isNotRight);
            } catch (IOException e) {
                System.err.println();
            }

            return result;
    }
}
