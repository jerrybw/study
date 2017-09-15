package com.jerry.study.servlet;


import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * Created by 向博文 on 2017/9/12.
 */
public class HelloServlet extends javax.servlet.http.HttpServlet {
    private Logger logger = Logger.getLogger(HelloServlet.class);

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
//        InputStream jdbcResource = Class.class.getClassLoader().getResourceAsStream("jdbc.properties");
//        Properties jdbcProperties = new Properties();
//        jdbcProperties.load(jdbcResource);
//        String className = jdbcProperties.getProperty("className");
//        String url = jdbcProperties.getProperty("url");
//        String userName = jdbcProperties.getProperty("userName");
//        String pwd = jdbcProperties.getProperty("pwd");
//        Connection connection = null;
//        try {
//            Class.forName(className);
//            connection = DriverManager.getConnection(url, userName, pwd);
//            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM t_forms");
//            preparedStatement.execute();
//            ResultSet resultSet = preparedStatement.getResultSet();
//            while (resultSet.next()){
//                String name = resultSet.getString("name");
//                System.out.println(name);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        request.getRequestDispatcher("/hello.jsp").forward(request,response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doPost(request,response);
    }
}
