package com.example.employeems.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpDTO {
    private int empId;
    private String empName;
    private String empAddress;
    private String empMobile;
}
