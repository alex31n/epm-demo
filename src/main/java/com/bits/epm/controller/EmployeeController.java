package com.bits.epm.controller;

import com.bits.epm.data.dto.EmployeeDTO;
import com.bits.epm.data.entity.Employee;
import com.bits.epm.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.function.EntityResponse;

@RestController
@RequestMapping(value = "/employee")
@RequiredArgsConstructor
@Slf4j
public class EmployeeController {

    private final EmployeeService service;

    @GetMapping("/add")
    public ModelAndView addEmployee(){
        var modelView = new ModelAndView();
        modelView.setViewName("addEmployee");

        modelView.addObject("employee", new Employee());

        return modelView;
    }


}
