package MainServer;

import java.io.*;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


public class JsonSerialize {
    public static String workingDir = System.getProperty("user.dir")
                                        + File.separator + "src" + File.separator
                                        +  File.separator + "MainServer" + File.separator
                                        +  "deposit.json";


    public static void saveToJsonFile(DepositObj depositObj) {
        List<DepositObj> toJsonfile = new ArrayList<>();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        if (loadFromJsonFile() != null) {
            toJsonfile = loadFromJsonFile();
            toJsonfile.add(depositObj);
        } else {
            toJsonfile.add(depositObj);
        }

        try (Writer writer = new FileWriter(workingDir)) {
            gson.toJson(toJsonfile, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void saveToJsonFile(List<DepositObj> depositObjList) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (Writer writer = new FileWriter(workingDir)) {
            gson.toJson(depositObjList, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<DepositObj> loadFromJsonFile() {
        List<DepositObj> fromJsonFile = new ArrayList<>();
        Type depositObjType = new TypeToken<List<DepositObj>>(){}.getType();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        if (new File(workingDir).exists()) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(workingDir));
                fromJsonFile = gson.fromJson(bufferedReader, depositObjType);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("non such file ...");
            fromJsonFile = null;
        }
        return fromJsonFile;
    }

    public static DepositObj deserialzeFromClient(String json) {
        DepositObj depositObj;
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        depositObj = gson.fromJson(json, DepositObj.class);
        return depositObj;
    }

    public static String serialzeDepositList(List<DepositObj> depositObjList) {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(depositObjList, depositObjList.getClass());
    }
}
