package com.example.demo.controller;


import com.example.demo.entity.Student;
import com.example.demo.mappper.StudentMapper;
import com.example.demo.service.Impl.StudentServiceImpl;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stu")
public class StudentController {
     @Autowired
     private StudentServiceImpl studentService;
     @RequestMapping("/list")
    public List<Student> getStudentList(){
        // System.out.println(studentService.getList().get(0).toString());
         return studentService.selectAll();
     }
}
