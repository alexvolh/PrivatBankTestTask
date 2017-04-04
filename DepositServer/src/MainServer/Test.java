package MainServer;

public class Test {
    public static void main(String[] args) {
        DepositObj depositObj1 = new DepositObj(13434,
                "PrivatBank",
                "Ukraine",
                "Alex",
                DepositObj.Type.DEMAND,
                4356.75,
                12.5,
                "30/03/2017");
        DepositObj depositObj2 = new DepositObj(788834,
                "Alfa-Bank",
                "USA",
                "John Dou",
                 DepositObj.Type.METAL,
                11457.75,
                25.75,
                "01/01/2018");
    }
}
