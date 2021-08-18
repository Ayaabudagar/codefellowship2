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


    @GetMapping("/")
    public String start(Principal principl ,Model newModel){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails)principal).getUsername();
            newModel.addAttribute("username" , username);
        } else {
            String username = principal.toString();
        }
        return principl != null ? "home.html" : "start.html";
    }

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
    public String getUserById(@PathVariable(value="id") int  id , Model newModel){

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails)principal).getUsername();
            newModel.addAttribute("username" , username);
            DBUser user= dbUserRepository.findByUsername(username);
            if (!(user.getFollowing().contains(dbUserRepository.findById(id).get())) && dbUserRepository.findById(id).get() != user){
                newModel.addAttribute("status" , true);
            }else{
                newModel.addAttribute("status" , false);
            }
        } else {
            String username = principal.toString();
        }

        newModel.addAttribute("user" , dbUserRepository.findById(id).get());


        return "user.html";

    }
    @GetMapping("/profile")
    public String profile(Model newModel){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails)principal).getUsername();
            DBUser user = dbUserRepository.findByUsername(username);
            newModel.addAttribute("username" , username);


            newModel.addAttribute("user" , user);
        } else {
            String username = principal.toString();
        }
        return "userProfile.html";
    }
    @PostMapping("/follow/{id}")
    public RedirectView follow(Principal p , @PathVariable("id") int id){
        DBUser me = dbUserRepository.findByUsername(p.getName());
        DBUser followed = dbUserRepository.findById(id).get();

        me.getFollowing().add(followed);
        followed.getFollowers().add(me);
        dbUserRepository.save(me);
        dbUserRepository.save(followed);
        return new RedirectView("/user/{id}");
    }

    @GetMapping("/feed")
    public String feed(Principal p, Model model){
        DBUser me = dbUserRepository.findByUsername(p.getName());
        List<DBUser> following = me.getFollowing();
        model.addAttribute("following",following);
        model.addAttribute("username",me.getUsername());
        return "feed.html";
    }

    @GetMapping("/findfriends")
    public String findFriends(Model model , Principal p){
        List<DBUser> allUsers = (List<DBUser>)dbUserRepository.findAll();
        model.addAttribute("username",p.getName());
        model.addAttribute("users" , allUsers);

        return "findfriends.html";
    }

}

