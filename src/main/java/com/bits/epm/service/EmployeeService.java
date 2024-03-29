package com.bits.epm.service;

import com.bits.epm.data.dto.EmployeeDTO;
import com.bits.epm.data.entity.Employee;
import com.bits.epm.data.report.IEmployeeReport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EmployeeService {

    Page<Employee> findAll(Pageable pageable);

    EmployeeDTO findById(long id);

    EmployeeDTO create(EmployeeDTO employeeDTO, MultipartFile imageFile);

    EmployeeDTO update(Long empId, EmployeeDTO obj, MultipartFile imageFile);

    void deleteById(Long id);

    DataTablesOutput<Employee> findAllDatatable(DataTablesInput input);

    List<IEmployeeReport> getEmployeeReport();
}
