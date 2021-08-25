package com.example.sprintrestanimemvc.daos;

import com.example.sprintrestanimemvc.models.Anime;
import com.example.sprintrestanimemvc.models.User;
import com.example.sprintrestanimemvc.models.UserRegistration;
import com.example.sprintrestanimemvc.requests.*;
import com.example.sprintrestanimemvc.responses.CreateAnimeResponse;
import com.example.sprintrestanimemvc.responses.LoginResponse;
import com.example.sprintrestanimemvc.responses.UpdateAnimeResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
public class ApiDAO {

    private final RestTemplate restTemplate;

    private final HttpSession session;

    @Value("${url.api}")
    private String url;

    public ApiDAO(RestTemplate restTemplate, HttpSession session) {
        this.restTemplate = restTemplate;
        this.session = session;
    }

    public List<Anime> getAnimeAll(String search) {
        HttpHeaders headers = new HttpHeaders();

        HttpEntity<?> entity = new HttpEntity<>(headers);
        String url = this.url + "/anime";

      if (search != null) {
            url += "?search=" + search;
        }

        log.debug("GET {}", url);

        return Arrays.asList(restTemplate.exchange(url, HttpMethod.GET, entity, Anime[].class).getBody());
    }

    public Anime getAnime(Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth((String) session.getAttribute("accessToken"));

        HttpEntity<?> entity = new HttpEntity<>(headers);

        log.debug("GET {}/anime/{}", url, id);

        return restTemplate.exchange(url + "/anime/{id}", HttpMethod.GET, entity, Anime.class, id).getBody();
    }
    public Anime createAnime(Anime anime) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth((String) session.getAttribute("accessToken"));

        CreateAnimeRequest createAnimeRequest = new CreateAnimeRequest(anime);
        HttpEntity<?> entity = new HttpEntity<>(createAnimeRequest, headers);

        log.debug("POST {}/anime, body = {}", url, createAnimeRequest);

        return new Anime(restTemplate.exchange(url + "/anime", HttpMethod.POST, entity, CreateAnimeResponse.class).getBody());
    }

    public Anime editAnime(Long id, Anime anime) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth((String) session.getAttribute("accessToken"));

        UpdateAnimeRequest updateAnimeRequest = new UpdateAnimeRequest(anime);
        HttpEntity<?> entity = new HttpEntity<>(updateAnimeRequest, headers);

        log.debug("PUT {}/anime/{}, body = {}", url, id, updateAnimeRequest);

        return new Anime(restTemplate.exchange(url + "/anime/{id}", HttpMethod.PUT, entity, UpdateAnimeResponse.class, id).getBody());
    }

    public void deleteAnime(Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth((String) session.getAttribute("accessToken"));

        HttpEntity<?> entity = new HttpEntity<>(headers);

        log.debug("DELETE {}/anime/{}", url, id);

        restTemplate.exchange(url + "/anime/{id}", HttpMethod.DELETE, entity, Void.class, id);
    }
    public User register(UserRegistration userRegistration) {
        RegisterRequest registerRequest = new RegisterRequest(userRegistration);

        log.debug("POST {}/signup, body = {}", url, registerRequest);

        return restTemplate.postForObject(url + "/signup", registerRequest, User.class);
    }
    public LoginResponse login(String username, String password) {
        LoginRequest loginRequest = new LoginRequest(username, password);

        log.debug("POST {}/login, body = {}", url, loginRequest);

        return restTemplate.postForObject(url + "/login", loginRequest, LoginResponse.class);
    }
}
