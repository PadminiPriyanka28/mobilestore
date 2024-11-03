package controller;

import model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class UserController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;

    @GetMapping("/signup")
    public String showSignupForm() {
        return "signup";
    }

    @PostMapping("/signup")
    public String processSignup(@ModelAttribute User user) {
        userService.save(user);
        return "redirect:/login";
    }
    @PostMapping("/signup")
    public String signUp(@RequestParam String username, @RequestParam String password, Model model) {
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);  // In reality, you should hash the password before saving it
        userService.saveUser(newUser);
        model.addAttribute("message", "User registered successfully!");
        return "signupSuccess";
    }
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        User user = userService.findByUsername(username); // Fetch user by username
        if (user == null) {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }

        // Verify if the entered password matches the hashed password in the database
        if (passwordEncoder.matches(password, user.getPassword())) {
            model.addAttribute("message", "Login successful");
            return "home";  // Redirect to the home page after successful login
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }

}
