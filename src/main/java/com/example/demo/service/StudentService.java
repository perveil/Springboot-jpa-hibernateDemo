package com.example.demo.service;

import com.example.demo.entity.Student;
//import com.example.demo.mappper.StudentMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public interface StudentService {
    //最简单的连接数据库的方式
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//    public List<Student> getList(){
//        String sql = "SELECT student_name,student_id,student_age  FROM student";
//        return (List<Student>)jdbcTemplate.query(sql, new RowMapper<Student>() {
//
//            @Override
//            public Student mapRow(ResultSet resultSet, int i) throws SQLException {
//                Student student=new Student();
//                student.setStudentId(resultSet.getString("student_id"));
//                student.setStudentName(resultSet.getString("student_name"));
//                return student;
//            }
//        });
//    }
   List<Student> selectAll();

}
