package com.example.sprintrestanimemvc.controllers;

import com.example.sprintrestanimemvc.models.Anime;
import com.example.sprintrestanimemvc.models.User;
import com.example.sprintrestanimemvc.services.AnimeService;
import com.example.sprintrestanimemvc.services.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
@Controller
@RequestMapping("/anime")
public class AnimeController {

    private final AnimeService animeService;

    private final UserService userService;


    public AnimeController(AnimeService animeService, UserService userService) {
        this.animeService = animeService;
        this.userService = userService;
    }

    @GetMapping
    public String getAnime(@RequestParam(required = false) String search, Model model) {
        model.addAttribute("anime", animeService.getAnimeAll(search));
        return "index";
    }

    @GetMapping("/create")
    public String createAnime(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("anime", new Anime());

        return "postAnime";
    }

    @GetMapping("/{id}/view")
    public String getAnimeid(@PathVariable("id") Long id, Model model, @AuthenticationPrincipal User user) {
        Anime anime = animeService.getAnime(id);

        model.addAttribute("anime", anime);

        return "animeView";
    }

    @GetMapping("/{id}/edit")
    public String editAnime(@PathVariable("id") Long id, Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("anime", animeService.getAnime(id));

        return "editAnime";
    }

    @GetMapping("/{id}/delete")
    public String deleteAnime(@PathVariable("id") Long id, RedirectAttributes attributes) {
        animeService.deleteAnime(id);

        return "redirect:/";
    }

    @PostMapping("/create")
    public String createAnime(@Valid Anime anime, BindingResult result, Model model, RedirectAttributes attributes,
                              @AuthenticationPrincipal User user) {

        anime = animeService.createAnime(anime);

        return "redirect:/anime/" + anime.getId() + "/view";
    }

    @PostMapping("/{id}/edit")
    public String editAnime(
            @PathVariable("id") Long id,
            @Valid Anime anime, BindingResult result,
            Model model,
            @AuthenticationPrincipal User user) {

        return "redirect:/anime/" + animeService.editAnime(id, anime).getId() + "/view";
    }
}
