package com.example.employeems.controller;

import com.example.employeems.VarList.VarList;
import com.example.employeems.dto.EmpDTO;
import com.example.employeems.dto.ResponseDTO;
import com.example.employeems.entity.Employee;
import com.example.employeems.service.EmpService;
import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/emp")
@RestController
@CrossOrigin
public class EmpController {
    @Autowired
    private EmpService empService;

    @Autowired
    private ResponseDTO responseDto;

    String res, varList, message, code   = null;
    Object content                       = null;
    HttpStatus httpStatus                = null;


    @PostMapping("/saveEmployee")
    public ResponseEntity saveEmployee(@RequestBody EmpDTO empDto){
        try{
            res = empService.saveEmployee(empDto);
            content                       = empDto;


              if(res.equals("00")){
                  varList    = VarList.RSP_SUCCESS;
                  message    = "Success";
                  httpStatus = HttpStatus.ACCEPTED;

              }else if(res.equals("06")){
                  varList    = VarList.RSP_DUPLICATED;
                  message    = "Employee Registered";
                  httpStatus = HttpStatus.BAD_REQUEST;

              }else{
                   varList    = VarList.RSP_FAIL;
                   message    = "Error";
                   httpStatus = HttpStatus.BAD_REQUEST;
                   content    = null;
              }

                  responseDto.setCode(varList);
                  responseDto.setMessage(message);
                  responseDto.setContent(content);
                  return new ResponseEntity(responseDto, httpStatus);

        }catch(Exception e){
                  responseDto.setCode(VarList.RSP_ERROR);
                  responseDto.setMessage(e.getMessage());
                  responseDto.setContent(null);
                  return new ResponseEntity(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateEmployee")
    public ResponseEntity updateEmployee(@RequestBody EmpDTO empDto){
        try{
            res      = empService.updateEmployee(empDto);
            content  = empDto;

            if(res.equals("00")){
                code       = VarList.RSP_SUCCESS;
                message    = "Success";
                httpStatus = HttpStatus.ACCEPTED;
            }else if(res.equals("01")){
                code       = VarList.RSP_NO_DATA_FOUND;
                message    = "Not Data Found";
                httpStatus = HttpStatus.BAD_REQUEST;
            }else{
                code       = VarList.RSP_ERROR;
                message    = "Error";
                httpStatus = HttpStatus.BAD_REQUEST;
                content    = null;
            }

            responseDto.setCode(code);
            responseDto.setMessage(message);
            responseDto.setContent(content);
            return new ResponseEntity(responseDto, httpStatus);

        }catch(Exception e){
            responseDto.setCode(VarList.RSP_ERROR);
            responseDto.setMessage(e.getMessage());
            responseDto.setContent(null);
            return new ResponseEntity(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAllEmployee")
    public ResponseEntity getAllEmployee(){
        try{
            List<EmpDTO> empList = empService.getAllEmployee();
            responseDto.setCode(VarList.RSP_SUCCESS);
            responseDto.setContent(empList);
            responseDto.setMessage("Success");
            return new ResponseEntity(responseDto, HttpStatus.ACCEPTED);
        }catch(Exception e){
            responseDto.setCode(VarList.RSP_ERROR);
            responseDto.setMessage("Error");
            responseDto.setContent(null);
            return new ResponseEntity(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getEmpById/{id}")
    public ResponseEntity getEmpById(@PathVariable String id){
        try{

            EmpDTO emp = empService.getEmpById(Integer.parseInt(id));
            if(emp != null) {
                responseDto.setContent(emp);
                responseDto.setMessage("Success");
                responseDto.setCode(VarList.RSP_SUCCESS);
                return new ResponseEntity(responseDto, HttpStatus.ACCEPTED);
            }
            else{
                responseDto.setContent(null);
                responseDto.setMessage("No Data Available");
                responseDto.setCode(VarList.RSP_NO_DATA_FOUND);
                System.out.println("no data");
                return new ResponseEntity(responseDto, HttpStatus.BAD_REQUEST);
            }

        }catch(Exception e){
            responseDto.setCode(VarList.RSP_ERROR);
            responseDto.setMessage("Error");
            responseDto.setContent(null);
            return new ResponseEntity(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/empDelete/{id}")
    public ResponseEntity deleteEmployee(@PathVariable String id){
        try{

            res = empService.deleteEmployee(Integer.parseInt(id));
            if(res.equals("00")){
                message = "Success";
                content = res;
                code = VarList.RSP_SUCCESS;
                httpStatus = HttpStatus.ACCEPTED;
            }else if(res.equals("01")){
                message = "Data Not Found";
                content = res;
                code = VarList.RSP_NO_DATA_FOUND;
                httpStatus = HttpStatus.BAD_REQUEST;
            }else{
                message = "Error";
                code = VarList.RSP_ERROR;
                content = null;
                httpStatus = HttpStatus.BAD_REQUEST;
            }

            responseDto.setMessage(message);
            responseDto.setCode(code);
            responseDto.setContent(content);
            return new ResponseEntity(responseDto, httpStatus);

        }catch(Exception e){
            responseDto.setCode(VarList.RSP_ERROR);
            responseDto.setMessage(e.getMessage());
            responseDto.setContent(null);
            return new ResponseEntity(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
