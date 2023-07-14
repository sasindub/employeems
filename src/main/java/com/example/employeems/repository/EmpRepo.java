package com.example.employeems.repository;

import com.example.employeems.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmpRepo extends JpaRepository<Employee,Integer> {
}
