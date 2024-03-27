package com.bits.epm.controller;

import com.bits.epm.data.dto.EmployeeDTO;
import com.bits.epm.data.entity.Employee;
import com.bits.epm.service.EmployeeService;
import com.bits.epm.utils.Constants;
import com.bits.epm.service.FileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
@RequestMapping(value = "/employee")
@RequiredArgsConstructor
@Slf4j
public class EmployeeController {

    private final EmployeeService service;

    private final FileService fileService;


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


        // save images
        if (imageFile != null && !imageFile.isEmpty()) {
            try {
                String fileName = StringUtils.cleanPath(imageFile.getOriginalFilename());
                String saveFileName = fileService.saveFile(
                        fileName,
                        imageFile
                );

                if (saveFileName != null) {
                    request.setImage(fileName);
                }

            } catch (IOException e) {
                redirectAttributes.addFlashAttribute(Constants.Message.ERROR, "Image save failed: " + e.getLocalizedMessage());
            }
        }


        try {

            var saveEmployee = service.save(request);
            log.error("saveEmployee: " + saveEmployee);

            redirectAttributes.addFlashAttribute(Constants.Message.SUCCESS, "Employee save successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute(Constants.Message.ERROR, "Employee save failed: " + e.getLocalizedMessage());
        }

        return "redirect:/dashboard";
    }


    @GetMapping("/{id}/edit")
    @PreAuthorize("hasRole('ADMIN')")
    public String editEmployee(@PathVariable Long id, Model model) {

        var employee = service.findById(id);
        log.error("employee " + employee);
        model.addAttribute("employee", employee);
        model.addAttribute("genders", Employee.Gender.values());

        return "addEmployee";
    }


    @PostMapping("/{id}/edit")
    @PreAuthorize("hasRole('ADMIN')")
    public String editEmployee(@PathVariable Long id, @Valid @ModelAttribute(value = "employee") EmployeeDTO request, Errors errors, RedirectAttributes redirectAttributes, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("genders", Employee.Gender.values());
            return "addEmployee";
        }

        try {
            request.setId(id);
            service.save(request);
            redirectAttributes.addFlashAttribute(Constants.Message.SUCCESS, "Employee updated successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute(Constants.Message.ERROR, "Employee updated failed: " + e.getLocalizedMessage());
        }

        return "redirect:/dashboard";
    }

    @GetMapping("/{id}/delete")
    @PreAuthorize("hasRole('ADMIN')")
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
