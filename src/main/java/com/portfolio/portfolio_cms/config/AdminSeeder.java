package com.portfolio.portfolio_cms.config;

import com.portfolio.portfolio_cms.model.Role;
import com.portfolio.portfolio_cms.model.User;
import com.portfolio.portfolio_cms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminSeeder implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Value("${admin.username}")
    private String adminUsername;
    @Value("${admin.password}")
    private String adminPassword;

    public AdminSeeder(UserRepository userRepository,PasswordEncoder encoder){
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Override
    public void run(String... args){
        if (userRepository.count() > 0) {
            return;
        }
        User admin = new User(adminUsername, encoder.encode(adminPassword), Role.ADMIN);
        userRepository.save(admin);
        System.out.println("Admin user seeded: " + adminUsername);
    }
}
