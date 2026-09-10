package com.malavika.taskmanager;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/signup")
    public User signup(@RequestBody User user){
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        return userRepository.save(user);
    }
    @PostMapping("/login")
    public String login(@RequestBody User loginRequest){
        User existingUser = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(InvalidCredentialsException :: new);

        boolean passwordMatches = passwordEncoder.matches(loginRequest.getPassword(), existingUser.getPassword());

        if(!passwordMatches){
            throw new InvalidCredentialsException();

        }
        return "Login Successful!(Token generation comes next)";
    }
}
