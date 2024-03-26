package com.bits.epm.repository;

import com.bits.epm.data.entity.Employee;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long>, DataTablesRepository<Employee, Long> {
}
