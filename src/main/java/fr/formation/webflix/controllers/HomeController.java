package fr.formation.webflix.controllers;

import fr.formation.webflix.entities.UserEntity;
import fr.formation.webflix.enums.Gender;
import fr.formation.webflix.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.Optional;

@Controller
public class HomeController {


    @RequestMapping("/")
    public  String index(){
        //ModelAndView
        System.out.println("<je suis a l'index de mon site ");
        return "index.html";//.html n'est pas obligatoire
    }
    @RequestMapping("admin")
    public String amin(){
        return "admin/index.html";
    }
}
