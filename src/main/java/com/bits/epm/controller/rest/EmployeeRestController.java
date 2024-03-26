package com.bits.epm.controller.rest;

import com.bits.epm.data.entity.Employee;
import com.bits.epm.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/employee")
@RequiredArgsConstructor
@Slf4j
public class EmployeeRestController {

    private final EmployeeService service;

    @RequestMapping(value = "/data", method = RequestMethod.POST)
    public DataTablesOutput<Employee> getUsers(@Valid @RequestBody DataTablesInput input) {
        return service.findAllDatatable(input);
    }



}
