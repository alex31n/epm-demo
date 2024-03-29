package com.bits.epm.service;

import com.bits.epm.data.dto.EmployeeDTO;
import com.bits.epm.data.entity.Employee;
import com.bits.epm.repository.EmployeeRepository;
import com.bits.epm.utils.ExceptionUtils;
import com.bits.epm.utils.mapper.EmployeeMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

@Slf4j
@RequiredArgsConstructor
@Service
public class EmployeeService {

    private final EmployeeRepository repository;
    private final FileService fileService;

    private final EmployeeMapper employeeMapper = EmployeeMapper.INSTANCE;


    public DataTablesOutput<Employee> findAllDatatable(DataTablesInput input) {
//        log.debug("Request to get all Users");
        return repository.findAll(input);
    }

    public Page<Employee> findAll(Pageable pageable) {
        var list =  repository.findAll(pageable);

        return list;
    }


    public EmployeeDTO findById(long id) {
        return repository.findById(id)
                .map(employeeMapper::mapToDto)
                .orElseThrow(ExceptionUtils::notFoundException);
    }

    public EmployeeDTO create(EmployeeDTO employeeDTO, MultipartFile imageFile) {

        return Stream.of(employeeDTO)
                .peek(emp->emp.setId(null))
                .map(employeeMapper::mapToEntity)
                .peek(employee -> employee.setImage(saveFile(imageFile)))
                .map(repository::save)
                .map(employeeMapper::mapToDto)
                .findFirst()
                .orElse(employeeDTO);
    }

    public EmployeeDTO update(Long empId, EmployeeDTO obj, MultipartFile imageFile) {

        return Stream.ofNullable(empId)
                .peek(id -> {
                    if (id == null || id <= 0) throw ExceptionUtils.badRequestException("Employee not found");
                })
                .map(repository::findById)
                .map(emp -> emp.orElseThrow(() -> ExceptionUtils.badRequestException("Employee not found")))
                .peek(employee -> {
                    employee.setName(obj.getName());
                    employee.setDateOfBirth(obj.getDateOfBirth());
                    employee.setGender(obj.getGender());
                    employee.setNote(obj.getNote());
                    employee.setImage(updateFile(imageFile, obj.getImage()));
                })
                .map(repository::save)
                .map(employeeMapper::mapToDto)
                .findFirst()
                .orElse(obj);
    }


    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    private String saveFile(MultipartFile imageFile) {

        if (imageFile == null || imageFile.isEmpty()) {
            return null;
        }

        try {
            String fileName = StringUtils.cleanPath(imageFile.getOriginalFilename());
            String savedFilePath = fileService.saveFile(fileName, imageFile);

            if (savedFilePath != null) {
                return fileName;
            }

        } catch (IOException e) {

        }

        return null;
    }

    private String updateFile(MultipartFile imageFile, String oldFilename) {

        if (imageFile == null || imageFile.isEmpty()) {
            return oldFilename;
        }

        // delete old file
        if (StringUtils.hasText(oldFilename)) {
            fileService.deleteFile(oldFilename);
        }

        try {
            String fileName = StringUtils.cleanPath(imageFile.getOriginalFilename());
            String savedFilePath = fileService.saveFile(fileName, imageFile);

            if (savedFilePath != null) {
                return fileName;
            }

        } catch (IOException e) {

        }

        return null;
    }
}
