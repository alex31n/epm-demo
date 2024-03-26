package com.bits.epm.service;

import com.bits.epm.data.dto.EmployeeDTO;
import com.bits.epm.data.entity.Employee;
import com.bits.epm.repository.EmployeeRepository;
import com.bits.epm.utils.ExceptionUtils;
import com.bits.epm.utils.mapper.EmployeeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Service
public class EmployeeService{

    private final EmployeeRepository repository;

    private final EmployeeMapper employeeMapper = EmployeeMapper.INSTANCE;

    public List<Employee> findAll() {
        return repository.findAll();
    }

    /*public Page<EmployeeDTO> findAll(Specification<Employee> spec, Pageable pageable) {
        return null;
    }*/


    public EmployeeDTO findById(long id) {
        return repository.findById(id)
                .map(employeeMapper::mapToDto)
                .orElseThrow(ExceptionUtils::notFoundException);
    }


    public EmployeeDTO create(EmployeeDTO obj) {
        return Stream.of(obj)
                .map(employeeMapper::mapToEntity)
                .map(repository::save)
                .map(employeeMapper::mapToDto)
                .findFirst()
                .orElseThrow(ExceptionUtils::badRequestException);
    }


    public EmployeeDTO update(long id, EmployeeDTO obj) {
        return null;
    }


    public void deleteById(long id) {

    }
}
