package com.bits.epm.service;

import com.bits.epm.data.report.IEmployeeReport;
import com.bits.epm.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class EmployeeReportService {

    private final EmployeeRepository repository;


    public List<IEmployeeReport> getEmployeeReport(){
        var reports = repository.employeeReports();

        reports.forEach(IEmployeeReport::print);
        return reports;
    }

}
