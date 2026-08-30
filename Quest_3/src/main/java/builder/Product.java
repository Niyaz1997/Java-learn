package builder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Product {
    private String name;
    private String category;
    private double price;
    private int stockQuantity;
    private List<String> specifications;
    private Map<String, String> attributes;
    private boolean isAvailable;
    private double rating;
    private List<String> images;

    public static class ProductBuilder {
        private String name;
        private String category;
        private double price;
        private int stockQuantity;
        private List<String> specifications = new ArrayList<>();
        private Map<String, String> attributes = new HashMap<>();
        private boolean isAvailable = true;
        private double rating = 0.0;
        private List<String> images = new ArrayList<>();

        public ProductBuilder(String name, String category, double price) {
            this.name=name;
            this.category=category;
            this.price=price;
        }
        public ProductBuilder stock(int quantity) {
            this.stockQuantity=quantity;
            return this;
        }
        public ProductBuilder addSpecification(String spec) {
            this.specifications.add(spec);
            return this;
        }
        public ProductBuilder addAttributes(String key, String value) {
            this.attributes.put(key, value);
            return this;
        }
        public ProductBuilder setAvailability(boolean available) {
            this.isAvailable=available;
            return this;
        }
        public ProductBuilder setRating(double rating) {
            this.rating=rating;
            return this;
        }
        public ProductBuilder addImage(String imageUrl) {
            this.images.add(imageUrl);
            return this;
        }
        public Product build() {
            return new Product(this);
        }

    }
    private Product(ProductBuilder builder) {
        this.name=builder.name;
        this.category=builder.category;
        this.price=builder.price;
        this.stockQuantity=builder.stockQuantity;
        this.specifications=builder.specifications;
        this.attributes=builder.attributes;
        this.isAvailable=builder.isAvailable;
        this.rating=builder.rating;
        this.images=builder.images;
    }
    @Override
    public String toString() {
        return "Product{\n" +
                " category='" + category + '\'' + ",\n" +
                " price=" + price + ",\n" +
                " stock=" + stockQuantity + ",\n" +
                " specifications=" + specifications + ",\n" +
                " attributes=" + attributes + ",\n" +
                " available=" + isAvailable + ",\n" +
                " rating=" + rating + ",\n" +
                " images=" + images + ",\n" +
                '}';
    }
}
