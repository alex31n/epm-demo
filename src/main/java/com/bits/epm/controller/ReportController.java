package com.bits.epm.controller;

import com.bits.epm.service.CoindeskService;
import com.bits.epm.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/report")
@RequiredArgsConstructor
@Slf4j
public class ReportController {

    private final EmployeeService employeeService;

    private final CoindeskService coindeskService;

    @GetMapping
    public String addEmployee(Model model) {

        model.addAttribute("reports", employeeService.getEmployeeReport());
        model.addAttribute("coindesk", coindeskService.getData());

        return "report";
    }




}
