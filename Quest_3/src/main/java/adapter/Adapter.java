package adapter;

interface ModernPayment {
    void pay(double amount);
}
class OldPayment {
    public void transfer(String account, double money) {
        System.out.println("Old system: transfer " + money + " to " + account);
    }
}

class PaymentAdapter implements ModernPayment {
    private OldPayment oldPayment;

    public PaymentAdapter(OldPayment oldPayment) {
        this.oldPayment = oldPayment;
    }

    @Override
    public void pay(double amount) {
        oldPayment.transfer("default-account", amount);
    }
}
public class Adapter {
    public static void main(String[] args) {
        System.out.println("Adapter Pattern Demo \n");

        ModernPayment payment = new PaymentAdapter(new OldPayment());
        payment.pay(100.50);
    }
}