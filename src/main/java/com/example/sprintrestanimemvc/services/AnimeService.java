package com.example.sprintrestanimemvc.services;


import com.example.sprintrestanimemvc.daos.ApiDAO;
import com.example.sprintrestanimemvc.models.Anime;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimeService {

    private final ApiDAO apiDAO;

    public AnimeService(ApiDAO apiDAO) {
        this.apiDAO = apiDAO;
    }

    public List<Anime> getAnimeAll(String search) {
        return apiDAO.getAnimeAll(search);
    }

    public Anime getAnime(Long id) {
        return apiDAO.getAnime(id);
    }

    public Anime createAnime(Anime anime) {
        return apiDAO.createAnime(anime);
    }

    public Anime editAnime(Long id, Anime anime) {
        return apiDAO.editAnime(id, anime);
    }

    public void deleteAnime(Long id) {
        apiDAO.deleteAnime(id);
    }
}
