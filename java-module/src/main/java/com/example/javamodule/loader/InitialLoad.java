package com.example.javamodule.loader;

import com.example.javamodule.domain.entity.Language;
import com.example.javamodule.domain.entity.Role;
import com.example.javamodule.domain.entity.User;
import com.example.javamodule.domain.repository.LanguageRepository;
import com.example.javamodule.domain.repository.RoleRepository;
import com.example.javamodule.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class InitialLoad implements CommandLineRunner {
    @Autowired
    private LanguageRepository languageRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void run(String... args) throws Exception {
        Language language = new Language(null, "English", "Hello World");
        languageRepository.save(language);

        User user = new User();
        Role role = new Role();
        role.setRoleName("ADMIN");
        roleRepository.save(role);
        user.setUsername("nikola");
        user.setPassword(bCryptPasswordEncoder.encode("cokolada"));
        user.setRoles(Set.of(role));
        userRepository.save(user);


    }



}
