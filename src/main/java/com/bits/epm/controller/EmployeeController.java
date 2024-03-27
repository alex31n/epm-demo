package com.bits.epm.controller;

import com.bits.epm.data.dto.EmployeeDTO;
import com.bits.epm.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/employee")
@RequiredArgsConstructor
@Slf4j
public class EmployeeController {

    private final EmployeeService service;


    @GetMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public String addEmployee(Model model) {

        model.addAttribute("employee", new EmployeeDTO());

        return "addEmployee";
    }



    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public String SaveEmployee(@Valid @ModelAttribute(value="employee") EmployeeDTO request, Errors errors, Model model) {
        if (errors.hasErrors()) {
            return "addEmployee";
        }

        service.create(request);
        return "redirect:/dashboard";
    }


}
