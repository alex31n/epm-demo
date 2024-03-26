package com.bits.epm;

import com.bits.epm.data.entity.Employee;
import com.bits.epm.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;

@DataJpaTest
public class EmployeeDataTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    private Employee employee1, employee2;

    @BeforeEach
    public void setupTestData(){
        // Given : Setup object or precondition
        employee1 =  Employee.builder()
                .name("User 1")
                .dateOfBirth(new Date())
                .gender(Employee.Gender.MALE)
                .build();

        employee2 = Employee.builder()
                .name("User 2")
                .dateOfBirth(new Date())
                .gender(Employee.Gender.FEMALE)
                .build();
    }

    @Test
    @DisplayName("test for save employees")
    public void addEmployeeTest() {

        Employee saveEmployee1 = employeeRepository.save(employee1);
        Employee saveEmployee2 = employeeRepository.save(employee2);

        // Then : Verify the output

        Assertions.assertThat(saveEmployee1).isNotNull();
        Assertions.assertThat(saveEmployee2).isNotNull();

    }
}
