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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    public String adminVideoDetail(@PathVariable("id") Long videoId, RedirectAttributes redirectAttributes, Model model) {
        Optional<VideoEntity> video = videoService.findById(videoId);
        if (video.isPresent()) {
            model.addAttribute("v", video.get());
            model.addAttribute("page","video/detail");
            return "admin/index";
        }
        redirectAttributes.addFlashAttribute("error","Pas de vidéo avec cet id");
        return "error_404.html";

    }
    @GetMapping("add")
    public String adminVideoAdd(VideoEntity videoEntity, Model model) {
        //model.addAttribute("civilite", Gender.values());
        model.addAttribute("cover-error","");
        model.addAttribute("cats",categoryService.findAll());
        model.addAttribute("page", "video/add.html");
        model.addAttribute("title", " - Video Add");
        return "admin/index.html";

    }
    @PostMapping("add")
    public String adminVideoAddPost(@Valid VideoEntity videoEntity, BindingResult bindingResult,
                                    @RequestParam("video-bin") MultipartFile videoBin,
                                    @RequestParam("cover-bin")MultipartFile coverBin,
                                    Model model) {   // mettre la dependance : "spring-boot-starter-validation" pour utiliser @Valid et BindingResult


       // model.addAttribute("videos", videoService.findAll());
       // model.addAttribute("cats",categoryService.findAll());

        if (bindingResult.hasErrors()) {
            return adminVideoAdd(videoEntity, model);
        }
        Path pathV = Paths.get("src/main/resources/static/files/" + videoBin.getOriginalFilename()).toAbsolutePath();
        try{
            videoBin.transferTo(new File(pathV.toFile().toURI()));
        } catch (IOException i) {
            model.addAttribute("video-error","Une erreur c'est produite lors du transfert");
            return adminVideoAdd(videoEntity, model);
        }


        Path pathC = Paths.get("src/main/resources/static/files/" + coverBin.getOriginalFilename()).toAbsolutePath();
        try{                                     //Users\Hervé\IdeaProjects\webflix\src\main\resources\static\files
            coverBin.transferTo(new File(pathC.toFile().toURI()));
        } catch (IOException i) {
            model.addAttribute("cover-error","Une erreur c'est produite lors du transfert");
            return adminVideoAdd(videoEntity, model);
        }
        //videoEntity.setCategory(new CategoryEntity(1L));
        videoEntity.setDatePublished(Calendar.getInstance());
//        if (bindingResult.hasErrors()) {
//            model.addAttribute("page", "video/index.html");
//            model.addAttribute("title", " - Video Add");
//            return "admin/index.html";
//        }
        videoEntity.setUrlVideo(videoBin.getOriginalFilename());
        videoEntity.setCover(coverBin.getOriginalFilename());
        videoService.save(videoEntity);
        return "redirect:/admin/videos";
    }
}
