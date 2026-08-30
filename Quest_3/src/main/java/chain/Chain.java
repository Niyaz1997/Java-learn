package chain;

abstract class DiscountHandler {
    protected DiscountHandler next;
    public void setNext(DiscountHandler next) {
        this.next=next;
    }
    public abstract  double applyDiscount(double price, User user, String weekDay);
}
class User {
    private String name;
    private int ordersCount;
    private double totalSpent;

    public User(String name, int ordersCount, double totalSpent) {
        this.name=name;
        this.ordersCount=ordersCount;
        this.totalSpent=totalSpent;
    }
    public int getOrdersCount() {
        return ordersCount;
    }
    public double getTotalSpent() {
        return totalSpent;
    }
    public String getName() {
        return name;
    }
}
class NewUserDiscount extends DiscountHandler {
    @Override
    public double applyDiscount(double price, User user, String weekDay) {
        if (user.getOrdersCount()==0 && !weekDay.equals("Holiday")) {
            double discount = price * 0.05;
            System.out.println("Discount for new user: 5% (saved " + discount + " peso)");
            return price-discount;
        } else if (next!=null) {
            return next.applyDiscount(price, user, weekDay);
        } return price;
    }
}
class VipCustomerDiscount extends DiscountHandler {
    @Override
    public double applyDiscount(double price, User user, String weekDay) {
        if (user.getOrdersCount()>=10 && user.getTotalSpent()>10000 && !weekDay.equals("Holiday")) {
            double discount = price * 0.10;
            System.out.println("VIP customer discount: 10% (saved " + discount + " peso");
            return price-discount;
        } else if (next!=null) {
            return next.applyDiscount(price, user, weekDay);
        } return price;
    }
}
class HolidayDiscount extends DiscountHandler {
    @Override
    public double applyDiscount(double price, User user, String weekDay) {
        if (weekDay.equals("Holiday")) {
            double discount = price *0.07;
            System.out.println("Holiday discount: 7% (saved " + discount + " peso");
            return price-discount;
        } else if (next!=null) {
            return next.applyDiscount(price, user, weekDay);
        } return price;
    }
}
public class Chain {
    public static void main(String[] args) {
        System.out.println("Chain of Responsibility Demo \n");

        DiscountHandler newUser = new NewUserDiscount();
        DiscountHandler vip = new VipCustomerDiscount();
        DiscountHandler holiday = new HolidayDiscount();

        newUser.setNext(vip);
        vip.setNext(holiday);

        System.out.println("Test 1: New User");
        User user1 = new User("Alice", 0, 0);
        double price1 = newUser.applyDiscount(100, user1, "Monday");
        System.out.println("Final price: " + price1);
        System.out.println();

        System.out.println("Test 2: VIP User");
        User user2 = new User("Bob", 15, 15000);
        double price2 = newUser.applyDiscount(100, user2, "Monday");
        System.out.println("Final price: " + price2);
        System.out.println();

        System.out.println("Test 3: Holiday");
        User user3 = new User("Charlie", 5, 5000);
        double price3 = newUser.applyDiscount(100, user3, "Holiday");
        System.out.println("Final price: " + price3);
    }
}