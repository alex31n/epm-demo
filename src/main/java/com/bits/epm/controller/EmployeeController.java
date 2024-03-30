package com.bits.epm.controller;

import com.bits.epm.data.dto.EmployeeDTO;
import com.bits.epm.data.entity.Employee;
import com.bits.epm.service.impl.EmployeeServiceImpl;
import com.bits.epm.utils.Constants;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/employee")
@RequiredArgsConstructor
@Slf4j
public class EmployeeController {

    private final EmployeeServiceImpl service;


    @GetMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public String addEmployee(Model model) {

        model.addAttribute("genders", Employee.Gender.values());
        model.addAttribute("employee", new EmployeeDTO());
        return "addEmployee";
    }


    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public String addEmployee(@Valid @ModelAttribute(value = "employee") EmployeeDTO request,
                              @RequestParam("imageFile") MultipartFile imageFile,
                              Errors errors, RedirectAttributes redirectAttributes) {

        if (errors.hasErrors()) {
            return "addEmployee";
        }

        try {

            service.create(request, imageFile);
            redirectAttributes.addFlashAttribute(Constants.Message.SUCCESS, "Employee save successfully!");

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute(Constants.Message.ERROR, "Employee save failed: " + e.getLocalizedMessage());
        }

        return "redirect:/dashboard";
    }


    @GetMapping("/{id}/edit")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public String editEmployee(@PathVariable Long id, Model model) {

        var employee = service.findById(id);
        model.addAttribute("employee", employee);
        model.addAttribute("genders", Employee.Gender.values());

        return "addEmployee";
    }


    @PostMapping("/{id}/edit")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public String editEmployee(@PathVariable Long id,
                               @Valid @ModelAttribute(value = "employee") EmployeeDTO request,
                               @RequestParam("imageFile") MultipartFile imageFile,
                               Errors errors,
                               RedirectAttributes redirectAttributes,
                               Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("genders", Employee.Gender.values());
            return "addEmployee";
        }

        try {

            service.update(id, request, imageFile);
            redirectAttributes.addFlashAttribute(Constants.Message.SUCCESS, "Employee updated successfully!");

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute(Constants.Message.ERROR, "Employee updated failed: " + e.getLocalizedMessage());
        }

        return "redirect:/dashboard";
    }

    @GetMapping("/{id}/delete")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public String deleteEmployee(@PathVariable Long id, RedirectAttributes redirectAttributes) {

        try {
            service.deleteById(id);
            redirectAttributes.addFlashAttribute(Constants.Message.SUCCESS, "Employee deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute(Constants.Message.ERROR, "Employee deleted failed: " + e.getLocalizedMessage());
        }

        return "redirect:/dashboard";
    }

}
