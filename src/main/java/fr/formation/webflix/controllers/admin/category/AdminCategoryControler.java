package fr.formation.webflix.controllers.admin.category;

import fr.formation.webflix.entities.CategoryEntity;
import fr.formation.webflix.entities.UserEntity;
import fr.formation.webflix.enums.Gender;
import fr.formation.webflix.services.CategoryService;
import fr.formation.webflix.services.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.Collection;
import java.util.Optional;

@Controller
@RequestMapping("admin/categorys")
public class AdminCategoryControler {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private VideoService videoService;

    @GetMapping("")
    public String adminUserAdd(CategoryEntity categoryEntity, Model model) {
        model.addAttribute("categorys", categoryService.findAll());
        //model.addAttribute("civilite", Gender.values());
        model.addAttribute("page", "category/index.html");
        model.addAttribute("title", " - Category Add");
        return "admin/index.html";
    }

    @PostMapping("")
    public String adminCategoryAddPost(@Valid CategoryEntity categoryEntity, BindingResult bindingResult,
                                       Model model) {   // mettre la dependance : "spring-boot-starter-validation" pour utiliser @Valid et BindingResult
        model.addAttribute("categorys", categoryService.findAll());
        if (bindingResult.hasErrors()) {
            model.addAttribute("page", "category/index.html");
            model.addAttribute("title", " - Category Add");
            return "admin/index.html";
        }

        categoryService.save(categoryEntity);
        return "redirect:/admin/categorys";
    }

    @RequestMapping("{id}")
    public String deleteByID(@PathVariable("id") Long id, RedirectAttributes redirAttrs, Model model) {
        Optional<CategoryEntity> videoCat = categoryService.findById(id);
        if (videoCat.get().getVideos().isEmpty()) {
            categoryService.deleteById(id);
        } else {
            redirAttrs.addFlashAttribute("error", "Cette categorie ne peux pas être supprimer ,car elle contient des vidéos," +
                    " voulez vous supprimmer toutes les vidéos de cette categorie ?");
        }

        model.addAttribute("categorys", categoryService.findAll());
        return "redirect:/admin/categorys";
    }

}
