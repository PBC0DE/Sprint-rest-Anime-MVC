package com.example.sprintrestanimemvc.requests;

import com.example.sprintrestanimemvc.models.Anime;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class UpdateAnimeRequest {

    private Long id;

    private String name;

    private String image;

    private String type;

    private Double score;

    private Integer chapters;

    private String releasedate;

    private String description;

    public UpdateAnimeRequest(Anime anime) {
        this.id = anime.getId();
        this.name = anime.getName();
        this.image = anime.getImage();
        this.type = anime.getType();
        this.score = anime.getScore();
        this.chapters = anime.getChapters();
        this.releasedate = anime.getReleasedate();
        this.description = anime.getDescription();
    }
}
