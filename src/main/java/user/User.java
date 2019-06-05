package user;

public class User {

    private double money;
    private static User instance;

    public static synchronized User getInstanceOfUser() {
        if (instance == null) {
            instance = new User();
            getInstanceOfUser().setMoney(1000);
        }
        return instance;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

}
