package com.bits.epm.controller;

import com.bits.epm.data.dto.EmployeeDTO;
import com.bits.epm.service.EmployeeService;
import com.bits.epm.utils.Constants;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String SaveEmployee(@Valid @ModelAttribute(value="employee") EmployeeDTO request, Errors errors, RedirectAttributes redirectAttributes) {
        if (errors.hasErrors()) {
            return "addEmployee";
        }

        try {
            service.create(request);
            redirectAttributes.addFlashAttribute(Constants.Message.SUCCESS,"Employee save successfully!");
        }catch (Exception e){
            redirectAttributes.addFlashAttribute(Constants.Message.ERROR,"Employee save failed: "+e.getLocalizedMessage());
        }

        return "redirect:/dashboard";
    }


}
