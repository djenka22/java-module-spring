package com.example.javamodule.domain.service;

import com.example.javamodule.domain.entity.Language;

public interface LanguageService {
    String findHelloWorld(String language);
    Language saveNewPair(String language, String value);
}
