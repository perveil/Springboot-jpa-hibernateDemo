package com.example.demo.service.Impl;

import com.example.demo.entity.Student;
import com.example.demo.mappper.StudentMapper;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("studentService")
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;
    @Override
    public List<Student> selectAll() {
        return studentMapper.selectAll();
    }
}
