package decorator;

interface Coffee {
    double getCost();

    String getDescription();
}

class SimpleCoffee implements Coffee {
    @Override
    public double getCost() {
        return 2.0;
    }

    @Override
    public String getDescription() {
        return "Simple coffee";
    }
}

abstract class CoffeeDecorator implements Coffee {
    protected Coffee wrappedCoffee;

    public CoffeeDecorator(Coffee coffee) {
        this.wrappedCoffee = coffee;
    }

    @Override
    public double getCost() {
        return wrappedCoffee.getCost();
    }

    @Override
    public String getDescription() {
        return wrappedCoffee.getDescription();
    }
}

class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public double getCost() {
        return super.getCost() + 0.5;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", milk";
    }
}

class SyrupDecorator extends CoffeeDecorator {
    public SyrupDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public double getCost() {
        return super.getCost() + 0.7;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", syrup";
    }
}

class WhippedCreamDecorator extends CoffeeDecorator {
    public WhippedCreamDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public double getCost() {
        return super.getCost() + 0.8;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", whipped cream";
    }
}

class ChocolateDecorator extends CoffeeDecorator {
    public ChocolateDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public double getCost() {
        return super.getCost() + 0.6;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", chocolate";
    }
}

class CaramelDecorator extends CoffeeDecorator {
    public CaramelDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public double getCost() {
        return super.getCost() + 0.9;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", caramel";
    }
}

class DoubleShotDecorator extends CoffeeDecorator {
    public DoubleShotDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public double getCost() {
        return super.getCost() + 1.5;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", double shot";
    }
}

public class Decorator {
    public static void main(String[] args) {
        System.out.println("Decorator Pattern Demo\n");

        Coffee simple = new SimpleCoffee();
        System.out.println(simple.getDescription() + " = $" + simple.getCost());

        Coffee withMilk = new MilkDecorator(new SimpleCoffee());
        System.out.println(withMilk.getDescription() + " = $" + withMilk.getCost());

        Coffee withMilkSyrup = new SyrupDecorator(new MilkDecorator(new SimpleCoffee()));
        System.out.println(withMilkSyrup.getDescription() + " = $" + withMilkSyrup.getCost());

        Coffee fancy = new WhippedCreamDecorator(
                new SyrupDecorator(
                        new MilkDecorator(
                                new SimpleCoffee())));
        System.out.println(fancy.getDescription() + " = $" + fancy.getCost());

        Coffee mega = new DoubleShotDecorator(
                new CaramelDecorator(
                        new ChocolateDecorator(
                                new SimpleCoffee())));
        System.out.println(mega.getDescription() + " = $" + mega.getCost());
    }
}