package com.bits.epm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @GetMapping
    public String home(Model model){
        return "login";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    /*@RequestMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }*/

}
