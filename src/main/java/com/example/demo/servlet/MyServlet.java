package com.example.demo.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/xs/*",description = "myServlet")
public class MyServlet extends HttpServlet {
    private static final long serialVersionUID = -8685285401859800066L;
     @Override
     protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         System.out.println(">>>>>>>>>>doGet()<<<<<<<<<<<");
         doPost(req,resp);
     }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(">>>>>>>>>>doPost()<<<<<<<<<<<");
        //resp.sendRedirect("/HelloWorld.html");
    }

}
