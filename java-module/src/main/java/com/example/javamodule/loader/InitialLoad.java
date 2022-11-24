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
        languageRepository.save(new Language.Builder().createLanguage("en").createValue("Hello World").build());
        languageRepository.save(new Language.Builder().createLanguage("gr").createValue("Hallo Welt").build());
        languageRepository.save(new Language.Builder().createLanguage("srb").createValue("Zdravo svete").build());
        languageRepository.save(new Language.Builder().createLanguage("por").createValue("Olá Mundo").build());
        languageRepository.save(new Language.Builder().createLanguage("sp").createValue("Hola Mundo").build());
        languageRepository.save(new Language.Builder().createLanguage("swe").createValue("Hej världen").build());
        languageRepository.save(new Language.Builder().createLanguage("tur").createValue("Selam Dünya").build());
        languageRepository.save(new Language.Builder().createLanguage("bos").createValue("Pozdrav svijete").build());
        languageRepository.save(new Language.Builder().createLanguage("ita").createValue("Ciao mondo").build());
        languageRepository.save(new Language.Builder().createLanguage("mcd").createValue("Zdravo svete").build());

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
