package service;

import model.Cart;
import model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import repository.CartRepository;
import repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

public class CartService {
    // Simulating a data source
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    private List<Cart> cartDatabase = new ArrayList<>();

    public CartService() {
        // Sample data
        cartDatabase.add(new Cart("Item1", 2));
        cartDatabase.add(new Cart("Item2", 1));
        // Add more sample cart items as needed
    }

    public List<Cart> getCartItemsByUsername(String username) {
        // Implement your logic here. For simplicity, returning all items.
        // You would typically filter based on username.
        return cartDatabase; // Adjust this as per your actual logic
    }
    public void addToCart(Long productId) {
        // Fetch the product by its ID and add it to the cart
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Fetch or create a cart (implement this method to retrieve or create a cart)
        Cart cart = getOrCreateCart();
        cart.addProduct(product);
        cartRepository.save(cart);
    }


    private List<Product> cart = new ArrayList<>();  // Simple in-memory cart for storing products

    // Method to add a product to the cart
    public void addProductToCart(Product product) {
        cart.add(product);  // Add the product to the in-memory cart
    }

    // Method to get all cart items
    public List<Product> getCartItems() {
        return cart;
    }
    private Cart getOrCreateCart() {
        // Retrieve the cart from session or database. Create one if it doesn't exist.
        Long currentUserId = 1L;
        Cart cart = cartRepository.findByUserId(currentUserId);
        if (cart == null) {
            cart = new Cart();
        }
        return cart; // Simplified version. Implement session or user cart retrieval.
    }
}
