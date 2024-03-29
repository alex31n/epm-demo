package com.bits.epm.repository;

import com.bits.epm.data.entity.Employee;
import com.bits.epm.data.report.IEmployeeReport;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long>, DataTablesRepository<Employee, Long> {


    @Query(value = "select gender, count (Employee.id) as count from Employee GROUP BY gender", nativeQuery = true)
    public List<IEmployeeReport> employeeReports();
}
