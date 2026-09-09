import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class ShoppingException extends Exception {
    public ShoppingException(String message) {
        super(message);
    }
}

class ProductNotFoundException extends ShoppingException {
    public ProductNotFoundException(String message) {
        super(message);
    }
}

class ProductUnavailableException extends ShoppingException {
    public ProductUnavailableException(String message) {
        super(message);
    }
}

class InvalidQuantityException extends ShoppingException {
    public InvalidQuantityException(String message) {
        super(message);
    }
}

class EmptyCartException extends ShoppingException {
    public EmptyCartException(String message) {
        super(message);
    }
}

class PaymentException extends ShoppingException {
    public PaymentException(String message) {
        super(message);
    }
}

class Product {
    private final int id;
    private final String name;
    private final double price;
    private int stock;

    public Product(int id, String name, double price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public void reduceStock(int quantity) {
        stock -= quantity;
    }
}

class CartItem {
    private final Product product;
    private final int quantity;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public double getTotal() {
        return product.getPrice() * quantity;
    }

    public String getDetails() {
        return product.getName() + " x " + quantity + " = " + getTotal();
    }
}

class ShoppingCart {
    private final List<CartItem> items = new ArrayList<>();

    public void addProduct(Product product, int quantity) throws ShoppingException {
        if (quantity <= 0) {
            throw new InvalidQuantityException("Quantity must be greater than zero");
        }

        if (product.getStock() == 0) {
            throw new ProductUnavailableException("Product is unavailable");
        }

        if (quantity > product.getStock()) {
            throw new ProductUnavailableException("Requested quantity is not available");
        }

        items.add(new CartItem(product, quantity));
        product.reduceStock(quantity);
    }

    public void removeProduct(int index) throws ShoppingException {
        if (items.isEmpty()) {
            throw new EmptyCartException("Cart is empty");
        }

        if (index < 0 || index >= items.size()) {
            throw new ProductNotFoundException("Cart item not found");
        }

        items.remove(index);
    }

    public void searchProduct(Map<Integer, Product> products, int id)
            throws ProductNotFoundException {
        Product product = products.get(id);

        if (product == null) {
            throw new ProductNotFoundException("Product not found");
        }

        System.out.println(product.getId() + " " + product.getName()
                + " " + product.getPrice() + " Stock: " + product.getStock());
    }

    public void displayCart() throws EmptyCartException {
        if (items.isEmpty()) {
            throw new EmptyCartException("Cart is empty");
        }

        double total = 0;

        for (CartItem item : items) {
            System.out.println(item.getDetails());
            total += item.getTotal();
        }

        System.out.println("Cart total: " + total);
    }

    public void payment(double amount) throws ShoppingException {
        if (items.isEmpty()) {
            throw new EmptyCartException("Cart is empty");
        }

        double total = 0;

        for (CartItem item : items) {
            total += item.getTotal();
        }

        if (amount < total) {
            throw new PaymentException("Insufficient payment");
        }

        System.out.println("Payment successful");
        System.out.println("Change: " + (amount - total));
        items.clear();
    }
}

public class Experiment7 {
    public static void main(String[] args) {
        Map<Integer, Product> products = new HashMap<>();

        products.put(1, new Product(1, "Laptop", 60000, 5));
        products.put(2, new Product(2, "Mouse", 800, 10));
        products.put(3, new Product(3, "Keyboard", 1500, 7));

        ShoppingCart cart = new ShoppingCart();

        try {
            cart.searchProduct(products, 1);
            cart.addProduct(products.get(1), 1);
            cart.addProduct(products.get(2), 2);
            cart.displayCart();
            cart.payment(62000);
        } 
        catch (ProductNotFoundException e) {
            System.out.println("ProductNotFoundException: " + e.getMessage());
        } 
        catch (ProductUnavailableException e) {
            System.out.println("ProductUnavailableException: " + e.getMessage());
        } 
        catch (InvalidQuantityException e) {
            System.out.println("InvalidQuantityException: " + e.getMessage());
        } 
        catch (EmptyCartException e) {
            System.out.println("EmptyCartException: " + e.getMessage());
        } 
        catch (PaymentException e) {
            System.out.println("PaymentException: " + e.getMessage());
        } 
        catch (ShoppingException e) {
            System.out.println("ShoppingException: " + e.getMessage());
        }
    }
}
