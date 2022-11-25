package com.example.javamodule.domain.service;

import com.example.javamodule.domain.entity.Language;
import com.example.javamodule.domain.models.ApiRequest;
import com.example.javamodule.domain.models.ApiResponse;
import com.example.javamodule.domain.repository.LanguageRepository;
import com.example.javamodule.infrastructure.exceptions.custom.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.http.HttpHeaders;
import java.util.Map;

@Service
@Slf4j
public class LanguageServiceImpl implements LanguageService {
    public final String URI = "https://api-translate.systran.net/translation/text/translate";
    public final String ACCESS_TOKEN = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6InFaZjVyd0hzVklmLWx4UDBGd3A2RHQxenV6d2dJRG43cktSbE5DSDUxUGcifQ.eyJqdGkiOiJ1X2NVNXpQQjNwMEg0Y2pEUlNodlkiLCJpYXQiOjE2NjkzODY4NjgsImV4cCI6MTY2OTM4NzQ2OCwiaXNzIjoiaHR0cHM6Ly90cmFuc2xhdGUuc3lzdHJhbi5uZXQiLCJhdWQiOiJSUlcyQ0VWaGhKTGV1RHo1cUV5NHMifQ.IRa51pC-AkFsdDFRlGhrQ4WzFNI1bKIKo_Lzh6cKumLAHrWNBZzbrdtPX9qxo32p65NAd0Ma_qRaAm-4JBQByFCI35sUPN0ntdtNhLQGV9Cmh9cFkqtIWAIkwGWu-hbD_wn1WaR7s9iXxcgYSs2AkQUfdKE_bGQV8A904Xl7Stf7isSCSAi55SW_tzDcC_Q6j0whqsycfov9GTFm5cwClsG8TKid5I7eyr8fkxqx5LFYwFuWn0_geox_7kxLVMU4UvIjKIDhO7afD3XAod9xblIV6QM2nrFM_N6ewa7gVRwq5rceTfbWmU6OE93iYuzRZYUUv4IjyHosE9koplN4iA";
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

    @Override
    public Map<String, String> callAnotherApi(String text, String language) {
        ApiRequest apiRequest = new ApiRequest.Builder().createInput(text).createTarget(language).build();
        log.info(apiRequest.toString());
        org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer "+ ACCESS_TOKEN);
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<ApiRequest> request = new HttpEntity<>(apiRequest, headers);
        ApiResponse response = restTemplate.postForObject(URI, request, ApiResponse.class);
        return Map.of(response.getLang(), response.getText());
    }
}
