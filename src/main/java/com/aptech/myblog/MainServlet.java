package com.aptech.myblog;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
//// Config by @Annotation
//@WebServlet(urlPatterns = {"/home", "*.do"},
//        initParams = {@WebInitParam(name="productName", value = "My blog")})

@WebServlet(urlPatterns = {"/home", "*.do"},name = "Main")
public class MainServlet extends HttpServlet {
    String productName = "";

    String connectionString = "";

    @Override
    public void init() throws ServletException {
        productName = getInitParameter("productName");
        connectionString = getServletContext().getInitParameter("connectionStr");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name").trim();
        resp.setContentType("text/xml");
        resp.getWriter().write(String.format("<application>" +
                "<msg>Hello world %s</msg>" +
                "<product>%s</product>"+
                "<connectionString>%s</connectionString>"+
                "</application>", name!=null&&!name.isEmpty()?", "+name:"", productName, connectionString));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name").trim();
        if(name != null && !name.isEmpty()){
            resp.getWriter().write(String.format("Hello: %s", name));
        }else {
            resp.sendRedirect("index.html");
        }
    }
}
