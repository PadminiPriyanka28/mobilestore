package com.example.mobilestore;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import controller.AuthController;
import controller.ProductController;
import controller.UserController;
import model.Product;
import model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import service.CartService;
import service.ProductService;
import service.UserService;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class MobileStoreApplicationTests {
    @Mock
    private UserService userService;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private AuthController authController;
    @Mock
    private ProductService productService;
    @Mock
    private CartService cartService;
    @Mock
    private Model model;

    @InjectMocks
    private ProductController productController;

    @InjectMocks
    private UserController userController;

    private String lastTestName;
    private boolean lastTestResult;

    @AfterEach
    public void printTestResult() {
        System.out.println("Test case: " + lastTestName + " - Result: " + (lastTestResult ? "Passed" : "Failed"));
    }

    @Test
    public void testGetProductById() {
        lastTestName = "testGetProductById"; // Store test case name
        // Arrange
        Long productId = 1L;
        Product mockProduct = new Product();
        mockProduct.setId(productId);
        mockProduct.setName("Sample Phone");
        when(productService.getProductById(productId)).thenReturn(mockProduct);

        // Act
        String viewName = productController.getProductById(productId, model);
        lastTestResult = "productDetail".equals(viewName); // Check if the view name is "productDetail"

        // Assert
        assertEquals("productDetail", viewName); // Check if the view name is "productDetail"
        verify(model).addAttribute("product", mockProduct); // Verify the product is added to the model
        verify(productService).getProductById(productId); // Ensure the service method is called
    }

    @Test
    public void testIndex() {
        lastTestName = "testIndex"; // Store test case name
        // Arrange
        List<Product> mockProducts = Arrays.asList(new Product(), new Product());
        when(productService.getAllProducts()).thenReturn(mockProducts);

        // Act
        String viewName = productController.index(model);
        lastTestResult = "index".equals(viewName); // Check if the view name is "index"

        // Assert
        assertEquals("index", viewName); // Check if the view name is "index"
        verify(model).addAttribute("products", mockProducts); // Verify the products are added to the model
        verify(productService).getAllProducts(); // Ensure the service method is called
    }



    @Test
    public void testLogout() {
        lastTestName = "testLogout"; // Store test case name
        // Arrange
        HttpSession mockSession = mock(HttpSession.class); // Mock the HttpSession

        // Act
        String viewName = AuthController.logout(mockSession, model); // Call the logout function
        lastTestResult = "logoutSuccess".equals(viewName); // Check if the view name is "logoutSuccess"

        // Assert
        assertEquals("logoutSuccess", viewName); // Check if the view name is "logoutSuccess"
        verify(mockSession).invalidate(); // Verify the session is invalidated
        //verify(model).addAttribute("message", "You have been logged out successfully."); // Verify success message
    }


    @Test
    public void testAddToCart() {
        lastTestName = "testAddToCart"; // Store test case name
        // Arrange
        Long productId = 1L;
        Product mockProduct = new Product();
        mockProduct.setId(productId);
        mockProduct.setName("iPhone 13");
        when(productService.getProductById(productId)).thenReturn(mockProduct);

        // Act
        String viewName = productController.addToCart(productId, model);
        lastTestResult = "cart".equals(viewName); // Check if the view name is "cart"

        // Assert
        assertEquals("cart", viewName); // Check if the view name is "cart"
        verify(cartService).addProductToCart(mockProduct); // Verify that the product is added to the cart
        verify(model).addAttribute("message", "Product added to cart"); // Verify that the message is added to the model
        verify(productService).getProductById(productId); // Ensure the service method is called
    }



    @Test
    public void testSignUp() {
        lastTestName = "testSignUp"; // Store test case name
        // Arrange
        String username = "testUser";
        String password = "password123";
        User mockUser = new User();
        mockUser.setUsername(username);
        mockUser.setPassword(password);

        // Act
        String viewName = userController.signUp(username, password, model);
        lastTestResult = "signupSuccess".equals(viewName); // Check if the view name is "signupSuccess"

        // Assert
        assertEquals("signupSuccess", viewName);  // Check if the view name is "signupSuccess"
        verify(userService).saveUser(any(User.class));  // Verify that the userService.saveUser method is called
        verify(model).addAttribute("message", "User registered successfully!");  // Verify the success message
    }

    @Test
    public void testLogin_Success() {
        lastTestName = "testLogin_Success"; // Store test case name
        // Arrange
        String username = "testUser";
        String password = "password123";
        String hashedPassword = "hashedPassword123";

        User user = new User();
        user.setUsername(username);
        user.setPassword(hashedPassword);

        // Mock the UserService and PasswordEncoder
        when(userService.findByUsername(username)).thenReturn(user);
        when(passwordEncoder.matches(password, hashedPassword)).thenReturn(true);

        // Act
        String viewName = userController.login(username, password, model);
        lastTestResult = "home".equals(viewName); // Check if the view name is "home" on successful login

        // Assert
        assertEquals("home", viewName);  // Check if the view name is "home" on successful login
        verify(model).addAttribute("message", "Login successful");  // Verify success message
    }

    @Test
    public void testLogin_Failure_InvalidUser() {
        lastTestName = "testLogin_Failure_InvalidUser"; // Store test case name
        // Arrange
        String username = "invalidUser";
        String password = "password123";

        // Mock the UserService to return null for an invalid user
        when(userService.findByUsername(username)).thenReturn(null);

        // Act
        String viewName = userController.login(username, password, model);
        lastTestResult = "login".equals(viewName); // Check if the view name is "login" for invalid user

        // Assert
        assertEquals("login", viewName);  // Check if the view name is "login" for invalid user
        verify(model).addAttribute("error", "Invalid username or password");  // Verify error message
    }

    @Test
    public void testLogin_Failure_InvalidPassword() {
        lastTestName = "testLogin_Failure_InvalidPassword"; // Store test case name
        // Arrange
        String username = "testUser";
        String password = "wrongPassword";
        String hashedPassword = "hashedPassword123";

        User user = new User();
        user.setUsername(username);
        user.setPassword(hashedPassword);

        // Mock the UserService and PasswordEncoder
        when(userService.findByUsername(username)).thenReturn(user);
        when(passwordEncoder.matches(password, hashedPassword)).thenReturn(false);

        // Act
        String viewName = userController.login(username, password, model);
        lastTestResult = "login".equals(viewName); // Check if the view name is "login" for invalid password

        // Assert
        assertEquals("login", viewName);  // Check if the view name is "login" for invalid password
        verify(model).addAttribute("error", "Invalid username or password");  // Verify error message
    }
}
