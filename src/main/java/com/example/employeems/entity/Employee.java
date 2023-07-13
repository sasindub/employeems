package com.example.employeems.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Employee")
public class Employee {
    @Id
    private int empId;
    private String empName;
    private String empAddress;
    private String empMobile;
}
