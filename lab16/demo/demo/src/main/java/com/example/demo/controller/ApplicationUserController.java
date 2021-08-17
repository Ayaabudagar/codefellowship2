package com.example.demo.controller;

import com.example.demo.models.ApplicationUser;
import com.example.demo.models.ApplicationUserRepository;
import com.example.demo.models.DBUser;
import com.example.demo.models.DBUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.List;

@Controller
public class ApplicationUserController {

    @Autowired
    DBUserRepository dbUserRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/signup")
    public String getSignUpPage(){
        return "signup.html";
    }
    @GetMapping("/login")
    public String getSignInPage(){
        return "signin.html";
    }
    @PostMapping("/signup")
    public RedirectView signUp(@ModelAttribute DBUser dbUser ){
        DBUser newUser = new DBUser(dbUser.getUsername(),bCryptPasswordEncoder.encode(dbUser.getPassword()) , dbUser.getFirstName(), dbUser.getLastName(), dbUser.getDateOfBirth(), dbUser.getBio());
        dbUserRepository.save(dbUser);
        return new RedirectView("/login");
    }

    @GetMapping("/user/{id}")
    public String getUserById(@PathVariable(value="id") Integer  id , Model newModel){

            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof UserDetails) {
                String username = ((UserDetails)principal).getUsername();
                newModel.addAttribute("username" , username);
                newModel.addAttribute("user",  dbUserRepository.findById(id).get());
            } else {
                String username = principal.toString();
            }
            return "user.html";


//        newModel.addAttribute("user", dbUserRepository.findById(id).get());
//        return("user.html");
    }
    @GetMapping("/profile")
    public String profile(Model newModel){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails)principal).getUsername();
            newModel.addAttribute("username" , username);
            newModel.addAttribute("user" , dbUserRepository.findByUsername(username));
        } else {
            String username = principal.toString();
        }
        return "userProfile.html";
    }
    @GetMapping("/feed")
    public String feed(Principal p, Model newModel){
        DBUser user = dbUserRepository.findByUsername(p.getName());
        newModel.addAttribute("user",user);
        return "feed.html";
    }

}

