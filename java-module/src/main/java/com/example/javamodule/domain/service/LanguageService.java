package com.example.javamodule.domain.service;

import com.example.javamodule.domain.entity.Language;

import java.util.Map;

public interface LanguageService {
    String findHelloWorld(String language);
    Language saveNewPair(String language, String value);

    Map<String, String> callAnotherApi(String text, String language);
}
