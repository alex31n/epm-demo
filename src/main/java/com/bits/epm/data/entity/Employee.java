package com.bits.epm.data.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.WithBy;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.Date;

@WithBy
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "employee")
public class Employee {

    public enum Gender{MALE, FEMALE, OTHER}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 55)
    private String name;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "gender", nullable = false, length = 55)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "image")
    private String image;

    @Column(name = "note")
    private String note;

    @CreationTimestamp
    @Column(name = "created_date")
    private Date createdDate;

    @UpdateTimestamp
    @Column(name = "updated_date")
    private Date updatedDate;

}
