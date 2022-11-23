package com.example.javamodule.domain.repository;

import com.example.javamodule.domain.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Long> {
    String findByLanguage(String language);
}
