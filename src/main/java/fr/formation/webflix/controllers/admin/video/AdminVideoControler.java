package fr.formation.webflix.controllers.admin.video;

import fr.formation.webflix.entities.CategoryEntity;
import fr.formation.webflix.entities.UserEntity;
import fr.formation.webflix.entities.VideoEntity;
import fr.formation.webflix.enums.Gender;
import fr.formation.webflix.services.CategoryService;
import fr.formation.webflix.services.VideoService;
import org.hibernate.annotations.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.Optional;

//@Filter() //permet de donner/filtrer l'acces au controler, peu servir pour la securité d'acces
@Controller
@RequestMapping("/admin/videos")
public class AdminVideoControler {

    @Autowired // instanciation de dependance
    private VideoService videoService;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("")
    public String adminVideo(Model model) {
        model.addAttribute("videos", videoService.findAll());
        model.addAttribute("page", "video/index.html");
        model.addAttribute("title", " - Video List");
        return "admin/index.html";
    }

    @RequestMapping("{id}")
    public String adminVideoDetail(@PathVariable("id") Long videoId, Model model) {
        Optional<VideoEntity> video = videoService.findById(videoId);
        if (video.isPresent()) {
            model.addAttribute("v", video.get());
            return "admin/video/detail";
        }
        return "error_404.html";

    }
    @GetMapping("add")
    public String adminVideoAdd(VideoEntity videoEntity, Model model) {
        //model.addAttribute("civilite", Gender.values());
        model.addAttribute("cats",categoryService.findAll());
        model.addAttribute("page", "video/add.html");
        model.addAttribute("title", " - Video Add");
        return "admin/index.html";

    }
    @PostMapping("add")
    public String adminVideoAddPost(@Valid VideoEntity videoEntity, BindingResult bindingResult,
                                    Model model) {   // mettre la dependance : "spring-boot-starter-validation" pour utiliser @Valid et BindingResult
        model.addAttribute("videos", videoService.findAll());
        model.addAttribute("cats",categoryService.findAll());
        //videoEntity.setCategory(new CategoryEntity(1L));
        videoEntity.setDatePublished(Calendar.getInstance());
        if (bindingResult.hasErrors()) {
            model.addAttribute("page", "video/index.html");
            model.addAttribute("title", " - Video Add");
            return "admin/index.html";
        }
        videoService.save(videoEntity);
        return "redirect:/admin/videos";
    }
}
