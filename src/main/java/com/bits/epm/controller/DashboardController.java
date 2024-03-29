package com.bits.epm.controller;

import com.bits.epm.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    private final EmployeeService employeeService;

    @RequestMapping
    public String dashboard(Model model){

//        var list = employeeService.findAll();

//        model.addAttribute("employees", list);
        model.addAttribute("CONTENT_TITLE", "Dashboard");

        return "dashboard";
    }
}
