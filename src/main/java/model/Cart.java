package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carts")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String itemName;
    private int quantity;
    // Add other fields as necessary, for example, a list of products
    @OneToMany
    private List<Product> products = new ArrayList<>();
    // Default constructor
    public Cart() {}

    // Parameterized constructor
    public Cart(String itemName, int quantity) {
        this.itemName = itemName;
        this.quantity = quantity;

    }
    // Method to add a product to the cart
    public void addProduct(Product product) {
        this.products.add(product);
    }
    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
    // Add other getters and setters as needed
}
