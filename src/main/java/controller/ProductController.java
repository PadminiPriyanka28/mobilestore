package controller;

import model.Product;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import service.CartService;
import service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private CartService cartService;
    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String index(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "index";
    }
    @GetMapping("/product/{id}")
    public String getProductById(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "productDetail";
    }
    @PostMapping("/cart/add")
    public String addToCart(@RequestParam Long productId, Model model) {
        Product product = productService.getProductById(productId);
        if (product != null) {
            cartService.addProductToCart(product);  // Add the product to the cart
            model.addAttribute("message", "Product added to cart");
        } else {
            model.addAttribute("error", "Product not found");
        }
        return "cart";
    }




}
