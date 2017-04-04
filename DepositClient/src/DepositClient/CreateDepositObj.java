package DepositClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class CreateDepositObj {
    private long id;
    private String name;
    private String country;
    private DepositObj.Type type;
    private String depositor;
    private double amountDeposit;
    private double profitability;
    private LocalDate finishDate;

    private BufferedReader bufferedReader;
    private boolean isWrongType;

    public CreateDepositObj() {
        this.id = generateId();
        this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        isWrongType = true;
    }
    private long generateId() {
        System.out.println("generate your id ...");
        long lowerRange = 1000;
        long upperRange = 10000;
        Random random = new Random();

        long randomValue = lowerRange + (long)(random.nextDouble() * (upperRange - lowerRange));
        System.out.println("your id is: " + randomValue);
        return randomValue;
    }

    public long getId() {
        return id;
    }

    public void setName() {
        System.out.print("enter the name of a bank: ");
        String nameOfBank = null;
        try {
            nameOfBank = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        name = nameOfBank;
    }

    public String getName() {
        return name;
    }

    public void setCountry() {
        System.out.print("enter country of registration of a bank: ");
        String countryOfBank = null;
        try {
            countryOfBank = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        country = countryOfBank;
    }

    public String getCountry() {
        return country;
    }

    public void setType() {
        do {
            System.out.println("enter type of deposit: |DEMAND| |TERM| |SETTELMET| |ACCUMULATIVE| |SAVINGS| |METAL|");
            String typeOfDeposit = null;
            try {
                typeOfDeposit = bufferedReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (typeOfDeposit != null) {
                switch (typeOfDeposit) {
                    case "DEMAND":
                        type = DepositObj.Type.DEMAND;
                        isWrongType = false;
                        break;
                    case "TERM":
                        type = DepositObj.Type.TERM;
                        isWrongType = false;
                        break;
                    case "SETTELMENT":
                        type = DepositObj.Type.SETTELMENT;
                        isWrongType = false;
                        break;
                    case "ACCUMULATIVE":
                        type = DepositObj.Type.ACCUMULATIVE;
                        isWrongType = false;
                        break;
                    case "SAVINGS":
                        type = DepositObj.Type.SAVINGS;
                        isWrongType = false;
                        break;
                    case "METAL":
                        type = DepositObj.Type.METAL;
                        isWrongType = false;
                        break;
                    default:
                        System.out.println("wrong type !!!");
                        break;
                }
            }
        } while (isWrongType);
    }

    public DepositObj.Type getType() {
        return type;
    }

    public void setDepositor() {
        System.out.print("enter name of depositor: ");
        String depositorName = null;
        try {
            depositorName = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.depositor = depositorName;

    }

    public String getDepositor() {
        return depositor;
    }

    public void setAmountDeposit() {
        String amount = null;
        isWrongType = true;
        do {
            System.out.print("enter amount of deposit: ");
            try {
                amount = bufferedReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (amount != null) {
                    amountDeposit = Double.parseDouble(amount);
                    if (this.amountDeposit < 0 || amountDeposit == 0) {
                        System.out.println("value equals 0 or less ! try again ...");
                        isWrongType = true;
                    } else {
                        isWrongType = false;
                    }
                }
            } catch (IllegalArgumentException e) {
                System.out.println("invalid value !  try again ...");
                isWrongType = true;
            }

        } while (isWrongType);
    }

    public double getAmountDeposit() {
        return amountDeposit;
    }

    public void setProfitability() {
        String profit = null;
        isWrongType = true;
        do {
            System.out.print("enter profitability: ");
            try {
                profit = bufferedReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (profit != null) {
                    profitability = Double.parseDouble(profit);
                    if (this.profitability < 0 || profitability == 0) {
                        System.out.println("value equals 0 or less ! try again ...");
                        isWrongType = true;
                    } else {
                        isWrongType = false;
                    }
                }
            } catch (IllegalArgumentException e) {
                System.out.println("invalid value !  try again ...");
                isWrongType = true;
            }

        } while (isWrongType);
    }

    public double getProfitability() {
        return profitability;
    }


    public void setFinishDate() {
        isWrongType = true;
        do {
            System.out.print("enter date in next format\"dd/MM/yyyy\": ");
            try {
                String date = bufferedReader.readLine();
                if (date.matches("\\d\\d/\\d\\d/\\d\\d\\d\\d")) {
                    finishDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    isWrongType = false;
                } else {
                    System.out.println("date not matches !");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while (isWrongType);
    }

    public LocalDate getFinishDate() {
        return finishDate;
    }


    public void run() {
        this.setName();
        this.setCountry();
        this.setType();
        this.setDepositor();
        this.setAmountDeposit();
        this.setProfitability();
        this.setFinishDate();
    }
}
