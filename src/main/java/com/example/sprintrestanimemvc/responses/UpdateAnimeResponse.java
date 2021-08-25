package com.example.sprintrestanimemvc.responses;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class UpdateAnimeResponse {

    private Long id;

    private String name;

    private String image;

    private String type;

    private Double score;

    private Integer chapters;

    private String releasedate;

    private String description;

    private String created;

    private String updated;
}
