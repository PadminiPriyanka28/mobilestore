package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @GetMapping("/signup")
    public String showSignUpForm(Model model) {
        return "redirect:/`login"; // Return the sign-up template name
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String username, @RequestParam String email, @RequestParam String password) {
        // Logic to save the user to the database goes here
        return "redirect:/`login"; // Redirect to login after successful registration
    }
}
