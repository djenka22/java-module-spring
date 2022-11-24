package com.example.javamodule.domain.service;

import com.example.javamodule.domain.entity.Language;
import com.example.javamodule.domain.repository.LanguageRepository;
import com.example.javamodule.infrastructure.exceptions.custom.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LanguageServiceImpl implements LanguageService {
    private final LanguageRepository repository;

    @Autowired
    public LanguageServiceImpl(LanguageRepository repository) {
        this.repository = repository;
    }

    @Override
    public String findHelloWorld(String lang) {
        Language language = repository.findByLanguage(lang).orElseThrow(() -> new NotFoundException("language not found"));
        return language.getValue();
    }

    @Override
    public Language saveNewPair(String language, String value) {
        return repository.save(new Language.Builder().createLanguage(language).createValue(value).build());
    }
}
