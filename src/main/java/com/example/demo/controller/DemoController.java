package com.example.demo.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
//@RequestMapping("/DemoController")
public class DemoController {

    @RequestMapping("/helloworld")
    public  String  index(){
        System.out.println("进入Controller");
        return  "/HelloWorld";
    }
}
