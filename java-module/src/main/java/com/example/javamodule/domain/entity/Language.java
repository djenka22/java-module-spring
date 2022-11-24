package com.example.javamodule.domain.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String language;
    private String value;

    public static class Builder {
        private String language;
        private String value;

        public Builder() {
        }

        public Builder createLanguage(String language) {
            this.language = language;
            return this;
        }
        public Builder createValue(String value) {
            this.value = value;
            return this;
        }

        public Language build() {
            return new Language(
                    null,
                    language,
                    value
            );
        }
    }
}
