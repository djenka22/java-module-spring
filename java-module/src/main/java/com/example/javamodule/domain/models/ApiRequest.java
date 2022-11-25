package com.example.javamodule.domain.models;

import com.example.javamodule.domain.entity.Language;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiRequest {
    String profileId;
    String autoDetectionMode;
    String domain;
    String format;
    List<String> inputs;
    String owner;
    String size;
    String target;
    Boolean withAnnotations;
    Boolean withInfo;

    public static class Builder {
        List<String> inputs = new ArrayList<>();
        String target;


        public Builder() {
        }

        public ApiRequest.Builder createInput(String input) {
            inputs.add(input);
            return this;
        }
        public ApiRequest.Builder createTarget(String target) {
            this.target = target;
            return this;
        }

        public ApiRequest build() {
            return new ApiRequest(
                    null,
                    "single",
                    "Generic",
                    "text/plain",
                    inputs,
                    "Systran",
                    "M",
                    target,
                    true,
                    true
            );
        }
    }
}
