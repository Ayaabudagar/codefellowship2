package com.example.demo.controller;

import com.example.demo.models.ApplicationUserRepository;
import com.example.demo.models.DBUserRepository;
import com.example.demo.models.Post;
import com.example.demo.models.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class PostController {
    @Autowired
    PostRepository postRepository ;
    @Autowired
    DBUserRepository dbUserRepository;
    @PostMapping("/add-post/{id}")
    public RedirectView addPost(@RequestParam(value = "body") String body,  @PathVariable(value="id") int id){
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());


        Post newPost = new Post(body , formatter.format(date), dbUserRepository.findById(id).get());

        postRepository.save(newPost);

        return new RedirectView("/profile");
    }


}
