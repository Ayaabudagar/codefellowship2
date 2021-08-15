package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
public class HomeController {
    @GetMapping("/home")
    @ResponseBody
    public String getHome(){
        return "Hello World";

}
    @GetMapping("/")
    @ResponseBody
    public String getHome(Principal p){
//        m.addAttribute("userName", p.getName());
        return "Hello World " + p.getName();
    }
}
