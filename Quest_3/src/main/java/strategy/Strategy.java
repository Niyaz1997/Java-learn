package strategy;

interface DeliveryStrategy {
    double calculateCost(double weight, String destination);

    String getDeliveryTime();

    String getDescription();
}

class ExpressDelivery implements DeliveryStrategy {
    @Override
    public double calculateCost(double weight, String destination) {
        return 100 + weight * 10;
    }

    @Override
    public String getDeliveryTime() {
        return "Within 2 hours";
    }

    @Override
    public String getDescription() {
        return "Express delivery (priority)";
    }
}

class StandardDelivery implements DeliveryStrategy {
    @Override
    public double calculateCost(double weight, String destination) {
        return 20 + weight * 10;
    }

    @Override
    public String getDeliveryTime() {
        return "1-2 days";
    }

    @Override
    public String getDescription() {
        return "Standard delivery";
    }
}

class PickupDelivery implements DeliveryStrategy {
    @Override
    public double calculateCost(double weight, String destination) {
        return weight * 10;
    }

    @Override
    public String getDeliveryTime() {
        return "Within 6 hours";
    }

    @Override
    public String getDescription() {
        return "Pickup from the store";
    }
}

class InternationalDelivery implements DeliveryStrategy {
    @Override
    public double calculateCost(double weight, String destination) {
        return 1000 + weight * 10;
    }

    @Override
    public String getDeliveryTime() {
        return "2 weeks";
    }

    @Override
    public String getDescription() {
        return "International delivery";
    }
}

class Order {
    private DeliveryStrategy deliveryStrategy;
    private double weight;
    private String destination;

    public Order(double weight, String destination) {
        this.weight = weight;
        this.destination = destination;
    }

    public void setDeliveryStrategy(DeliveryStrategy strategy) {
        this.deliveryStrategy = strategy;
    }

    public void calculateDeliveryCost() {
        if (deliveryStrategy == null) {
            System.out.println("The delivery method is not selected");
            return;
        }
        double cost = deliveryStrategy.calculateCost(weight, destination);
        System.out.println("Delivery method: " + deliveryStrategy.getDescription());
        System.out.println("Timing: " + deliveryStrategy.getDeliveryTime());
        System.out.println("Cost of delivery " + cost + " peso\n");
    }
}
public class Strategy {
    public static void main(String[] args) {
        System.out.println("Strategy Pattern Demo\n");

        Order order = new Order(2.5, "New York");

        System.out.println("Test 1: Express Delivery");
        order.setDeliveryStrategy(new ExpressDelivery());
        order.calculateDeliveryCost();

        System.out.println("Test 2: Standard Delivery");
        order.setDeliveryStrategy(new StandardDelivery());
        order.calculateDeliveryCost();

        System.out.println("Test 3: Pickup Delivery");
        order.setDeliveryStrategy(new PickupDelivery());
        order.calculateDeliveryCost();

        System.out.println("Test 4: International Delivery");
        order.setDeliveryStrategy(new InternationalDelivery());
        order.calculateDeliveryCost();
    }
}


