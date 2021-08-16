package com.example.demo.controller;

import com.example.demo.models.ApplicationUserRepository;
import com.example.demo.models.Post;
import com.example.demo.models.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class PostController {
    @Autowired
    PostRepository postRepository ;
    @Autowired
    ApplicationUserRepository applicationUserRepository;
    @PostMapping("/add-post/{id}")
    public RedirectView addPost(@RequestParam(value = "body") String body, @ModelAttribute Post post , @PathVariable(value="id") Integer id){

        Post newPost = new Post( body ,  applicationUserRepository.findById(id).get());

        postRepository.save(newPost);

        return new RedirectView("/profile");
    }


}
