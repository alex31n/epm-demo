package com.bits.epm.controller;

import com.bits.epm.data.entity.Employee;
import com.bits.epm.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/employee")
@RequiredArgsConstructor
@Slf4j
public class EmployeeController {

    private final EmployeeService service;


    @GetMapping("/add")
    public ModelAndView addEmployee() {
        var modelView = new ModelAndView();
        modelView.setViewName("addEmployee");

        modelView.addObject("employee", new Employee());

        return modelView;
    }


}
