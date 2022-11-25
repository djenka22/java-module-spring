package com.example.javamodule.domain.service;

import com.example.javamodule.domain.entity.Language;
import com.example.javamodule.domain.models.AccessTokenReponse;
import com.example.javamodule.domain.models.ApiRequest;
import com.example.javamodule.domain.models.ApiResponse;
import com.example.javamodule.domain.repository.LanguageRepository;
import com.example.javamodule.infrastructure.exceptions.custom.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.http.HttpHeaders;
import java.util.Map;

import static com.example.javamodule.constants.SistranTranslation.*;

@Service
@Slf4j
public class LanguageServiceImpl implements LanguageService {
    public final String ACCESS_TOKEN = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6InFaZjVyd0hzVklmLWx4UDBGd3A2RHQxenV6d2dJRG43cktSbE5DSDUxUGcifQ.eyJqdGkiOiIyN0FhTzJFT3d0RVhMdDE3QW5PNC0iLCJpYXQiOjE2NjkzOTEyMDksImV4cCI6MTY2OTM5MTgwOSwiaXNzIjoiaHR0cHM6Ly90cmFuc2xhdGUuc3lzdHJhbi5uZXQiLCJhdWQiOiJSUlcyQ0VWaGhKTGV1RHo1cUV5NHMifQ.lz6zh3TGC1Gl1nxBeUXhkpO5b5xjQLUalD8ABeSsMw9itOft7O47A9u4JfvGmjExXUcxVkz0aT-Xt9XVVTNn2aeyewN8MO2MlPY25Y9uHtaSKrNKWS_p9n1k3WcILt-AUZcwt7yt5CoTnnzCj-KxHRUmoY8J3GztuGz_67eMMjGO0XQb6VYYJ_rawPXMxpeygcVTg_kuJgTwjhwVGCW3MnhZbi_FaNZgA2h-Q9DUFFaSZSdwdb6lJ9zsrRzOQXb57LrJy1udrMzgFEjAvS-UO3klr3QQTOSrC8UcNfG92YuyFeK_A8S4HYUKcMpCLyv5zpzLsOv-V4BLFkw7dhhmIw";
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
    public Map<String, String> callAnotherApi(String value, String language) {
        ApiRequest apiRequest = new ApiRequest.Builder().createInput(value).createTarget(language).build();
        org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
        RestTemplate restTemplate = new RestTemplate();

        String accessToken = getAccessToken();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + accessToken);
        HttpEntity<ApiRequest> request = new HttpEntity<>(apiRequest, headers);
        ApiResponse response = restTemplate.postForObject(TRANSLATION_URI, request, ApiResponse.class);
        return Map.of(response.getLang(), response.getText());
    }

    private String getAccessToken() {
        org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
        RestTemplate restTemplate = new RestTemplate();

        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> valuesForAccessToken = new LinkedMultiValueMap<>();
        valuesForAccessToken.add("grant_type", GRANT_TYPE);
        valuesForAccessToken.add("client_id", CLIENT_ID);
        valuesForAccessToken.add("client_secret", CLIENT_SECRET);
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity(valuesForAccessToken, headers);
        AccessTokenReponse accessTokenResponse = restTemplate.postForObject(ACCESS_TOKEN_URI, entity, AccessTokenReponse.class);
        return accessTokenResponse.getAccessToken();
    }
}
