package com.bits.epm.data.dto;


import com.bits.epm.data.entity.Employee;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeDTO implements Serializable {

    private Long id;

    @Size(max = 55)
    @NotBlank(message = "Name cannot be empty.")
    private String name;

    @NotNull(message = "Date of Birth cannot be empty.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @NotNull(message = "Gender cannot be empty.")
    private Employee.Gender gender;

//    @Size(max = 255)
    private String image;

    @Size(max = 255)
    private String note;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

}
