package com.example.sprintrestanimemvc.models;

import com.example.sprintrestanimemvc.responses.CreateAnimeResponse;
import com.example.sprintrestanimemvc.responses.UpdateAnimeResponse;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
@NoArgsConstructor
public class Anime {

    private Long id;

    @NotBlank(message = "{anime.name} {error.notblank}")
    private String name;

    @NotBlank(message = "{anime.image} {error.notblank}")
    private String image;

    @NotBlank(message = "{anime.type} {error.notblank}")
    private String type;

    private Double score;

    private Integer chapters;

    @NotNull(message = "{releasedate} {error.notnull}")
    private String releasedate;

    @NotBlank(message = "{anime.description} {error.notblank}")
    private String description;

    private String created;

    private String updated;

    public Anime(CreateAnimeResponse createAnimeResponse) {
        this.id = createAnimeResponse.getId();
        this.name = createAnimeResponse.getName();
        this.image = createAnimeResponse.getImage();
        this.type = createAnimeResponse.getImage();
        this.score = createAnimeResponse.getScore();
        this.chapters = createAnimeResponse.getChapters();
        this.releasedate = createAnimeResponse.getReleasedate();
        this.description = createAnimeResponse.getDescription();
        this.created = createAnimeResponse.getCreated();
        this.updated = createAnimeResponse.getUpdated();
    }

    public Anime(UpdateAnimeResponse updateAnimeResponse) {
        this.id = updateAnimeResponse.getId();
        this.name = updateAnimeResponse.getName();
        this.image = updateAnimeResponse.getImage();
        this.type = updateAnimeResponse.getImage();
        this.score = updateAnimeResponse.getScore();
        this.chapters = updateAnimeResponse.getChapters();
        this.releasedate = updateAnimeResponse.getReleasedate();
        this.description = updateAnimeResponse.getDescription();
        this.created = updateAnimeResponse.getCreated();
        this.updated = updateAnimeResponse.getUpdated();
    }
}
