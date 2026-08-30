package builder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Builder {
    public static void main(String[] args) {
        System.out.println("=== Builder Pattern Demo ===\n");

        Product phone = new Product.ProductBuilder("iPhone 15", "Electronics", 999.99)
                .stock(50)
                .addSpecification("6.1 inch OLED")
                .addAttributes("color", "Black")
                .setRating(4.8)
                .build();

        System.out.println(phone);
    }
}