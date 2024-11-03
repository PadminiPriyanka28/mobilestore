package controller;

import model.Cart;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import service.CartService;
import service.UserService;

import java.util.List;

@Controller
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @GetMapping("/cart")
    public String showCart(Model model, Authentication authentication) {
        String username = authentication.getName(); // Get the username

        // Optional: Fetch the User object if needed
        User user = userService.getUserByUsername(username); // Fetch the User object

        // Get cart items based on the username
        List<Cart> cartItems = cartService.getCartItemsByUsername(username); // Correct method call

        // Add cart items to the model
        model.addAttribute("cartItems", cartItems);
        return "cart"; // Return the view name
    }
    @PostMapping("/add-to-cart")
    public String addToCart(@RequestParam("productId") Long productId, Model model) {
        cartService.addToCart(productId);  // Add product to cart using the CartService

        // Optionally, you can redirect to the cart page or back to products page
        return "redirect:/cart";  // Assuming you have a cart page or a products page
    }
}
