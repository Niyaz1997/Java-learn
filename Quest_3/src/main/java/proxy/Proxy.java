package proxy;
import builder.Product;
import java.util.HashMap;
import java.util.Map;

interface ProductRepository {
    Product getProduct(String id);

    void saveProduct(String id, Product product);
}

class RealProductRepository implements ProductRepository {
    private Map<String, Product> database = new HashMap<>();

    @Override
    public Product getProduct(String id) {
        System.out.println("Loading from Database: " + id);
        simulateDelay();
        return database.get(id);
    }

    @Override
    public void saveProduct(String id, Product product) {
        System.out.println("Saving to Database: " + id);
        simulateDelay();
        database.put(id, product);
    }

    private void simulateDelay() {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
    }
}

class CachedProductRepository implements ProductRepository {
    private ProductRepository realRepo;
    private Map<String, Product> cache = new HashMap<>();

    public CachedProductRepository(ProductRepository realRepo) {
        this.realRepo = realRepo;
    }

    @Override
    public Product getProduct(String id) {
        if (cache.containsKey(id)) {
            System.out.println("From cache: " + id);
            return cache.get(id);
        }

        Product product = realRepo.getProduct(id);
        if (product != null) {
            cache.put(id, product);
        }
        return product;
    }

    @Override
    public void saveProduct(String id, Product product) {
        cache.clear();
        realRepo.saveProduct(id, product);
    }
}
public class Proxy {
    public static void main(String[] args) {
        ProductRepository repo = new CachedProductRepository(new RealProductRepository());

        Product iphone = new Product.ProductBuilder("iPhone 15 Pro", "Electronics", 999.99)
                .stock(50)
                .addSpecification("6.1 inch OLED")
                .addAttributes("color", "Black Titanium")
                .setRating(4.8)
                .build();

        System.out.println("Saving Product");
        repo.saveProduct("iPhone 15 Pro", iphone);
        System.out.println();

        System.out.println("First request (from DB)");
        System.out.println(repo.getProduct("iPhone 15 Pro"));
        System.out.println();

        System.out.println("Second request (from cache)");
        System.out.println(repo.getProduct("iPhone 15 Pro"));
        System.out.println();

        System.out.println("Third request (from cache)");
        System.out.println(repo.getProduct("iPhone 15 Pro"));
    }
}
