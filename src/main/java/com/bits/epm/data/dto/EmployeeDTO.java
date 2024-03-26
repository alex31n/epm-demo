package com.bits.epm.data.dto;


import com.bits.epm.data.entity.Employee;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeDTO {

    @NotBlank
    private Long id;

    @NotBlank
    @Size(max = 55)
    private String name;

    @NotBlank
    private LocalDate dateOfBirth;

    @NotBlank
    private Employee.Gender gender;

    @NotBlank
    @Size(max = 11)
    private String phone;

    @Size(max = 55)
    private String email;

    @Size(max = 255)
    private String image;

    @Size(max = 255)
    private String note;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

}
