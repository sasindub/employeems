package com.example.employeems.service;

import com.example.employeems.VarList.VarList;
import com.example.employeems.dto.EmpDTO;
import com.example.employeems.entity.Employee;
import com.example.employeems.repository.EmpRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class EmpService {

    @Autowired
    private EmpRepo empRepo;

    @Autowired
    private ModelMapper modelMapper;

    private String status;

    //intsert
    public String saveEmployee(EmpDTO empDto){

        if(empRepo.existsById(empDto.getEmpId())){
            status = VarList.RSP_DUPLICATED;
        }else{
            empRepo.save(modelMapper.map(empDto, Employee.class));
            status = VarList.RSP_SUCCESS;
        }
            return status;
    }

    //update
    public String updateEmployee(EmpDTO empDto){

        if(empRepo.existsById(empDto.getEmpId())){
            empRepo.save(modelMapper.map(empDto, Employee.class));
            status = VarList.RSP_SUCCESS;

        }else{
            status = VarList.RSP_NO_DATA_FOUND;
        }

            return status;
    }

    //getAll
    public List<EmpDTO> getAllEmployee(){

        List<Employee> empList = empRepo.findAll();
        return modelMapper.map(empList, new TypeToken<List<EmpDTO>>(){}.getType());

    }

    //get employee by ID
    public EmpDTO getEmpById(int id){
        if(empRepo.existsById(id)){
            Employee emp = empRepo.findById(id).orElse(null);
            return modelMapper.map(emp, EmpDTO.class);
        }else{
            return null;
        }
    }

    //delete employee
    public String deleteEmployee(int id){
        if(empRepo.existsById(id)){
            empRepo.deleteById(id);
            return VarList.RSP_SUCCESS;
        }else{
            return VarList.RSP_NO_DATA_FOUND;
        }
    }

}
