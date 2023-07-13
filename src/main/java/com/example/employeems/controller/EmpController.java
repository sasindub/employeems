package com.example.employeems.controller;

import com.example.employeems.VarList.VarList;
import com.example.employeems.dto.EmpDTO;
import com.example.employeems.dto.ResponseDTO;
import com.example.employeems.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

}
