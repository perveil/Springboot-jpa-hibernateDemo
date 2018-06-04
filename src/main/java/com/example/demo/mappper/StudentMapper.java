package com.example.demo.mappper;

import com.example.demo.entity.Student;
import org.springframework.stereotype.Service;

import java.util.List;


public interface StudentMapper {
    List<Student> selectAll();
}
