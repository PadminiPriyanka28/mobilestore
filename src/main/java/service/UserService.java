package service;

import model.User;
import repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    public void addUser(User user) {
        // Implement logic to add a user (e.g., save to a database)
        System.out.println("User added: " + user.getUsername());
    }

    // Example method to update a user
    public void updateUser(User user) {
        // Implement logic to update user details
        System.out.println("User updated: " + user.getUsername());
    }
        // Example method to fetch a User by username
        public User getUserByUsername(String username) {
            return new User(username); // In a real app, fetch from database
        }
    public void register(User user) {
        // You might want to add validation here
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new RuntimeException("User already exists");
        }
        userRepository.save(user); // Save the user to the repository
    }


    public void saveUser(User user) {
        userRepository.save(user);
    }

}
