package MainServer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DepositObj {
    private long accountId;
    private String name;
    private String country;
    private String Depositor;
    private Type typeDeposit;
    private double amountDeposit;
    private double profitability;
    private LocalDate finishDate;

    public DepositObj() {
    }

    public DepositObj(long accountId, String name, String country, String depositor, Type typeDeposit, double amountDeposit, double profitability, String finishDate) {
        this.accountId = accountId;
        this.name = name;
        this.country = country;
        this.Depositor = depositor;
        this.typeDeposit = typeDeposit;
        this.amountDeposit = amountDeposit;
        this.profitability = profitability;
        this.finishDate = LocalDate.parse(finishDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }


    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDepositor() {
        return Depositor;
    }

    public void setDepositor(String depositor) {
        this.Depositor = depositor;
    }

    public Type getTypeDeposit() {
        return typeDeposit;
    }

    public void setTypeDeposit(Type typeDeposit) {
        this.typeDeposit = typeDeposit;
    }

    public double getAmountDeposit() {
        return amountDeposit;
    }

    public void setAmountDeposit(double amountDeposit) {
        this.amountDeposit = amountDeposit;
    }

    public double getProfitability() {
        return profitability;
    }

    public void setProfitability(double profitability) {
        this.profitability = profitability;
    }

    public LocalDate getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDate finishDate) {
        this.finishDate = finishDate;
    }

    @Override
    public String toString() {
        return "Info Account: {" +
                "accountId=" + accountId +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", Depositor='" + Depositor + '\'' +
                ", typeDeposit=" + typeDeposit +
                ", amountDeposit=" + amountDeposit +
                ", profitability=" + profitability +
                ", finishDate=" + finishDate +
                '}';
    }

    public enum Type {
        DEMAND,
        TERM,
        SETTELMENT,
        ACCUMULATIVE,
        SAVINGS,
        METAL,
    }
}
