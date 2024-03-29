package com.bits.epm.controller.rest;

import com.bits.epm.data.entity.Employee;
import com.bits.epm.service.impl.EmployeeServiceImpl;
import com.bits.epm.utils.PageUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/employee")
@RequiredArgsConstructor
@Slf4j
public class EmployeeRestController {

    private final EmployeeServiceImpl service;

    @RequestMapping(value = "/data", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public DataTablesOutput<Employee> getUsers(@Valid @RequestBody DataTablesInput input) {
        return service.findAllDatatable(input);
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<?> findAll(Pageable pageable) {
        return ResponseEntity.ok(
                PageUtils.build(service.findAll(pageable))
        );
    }



}
