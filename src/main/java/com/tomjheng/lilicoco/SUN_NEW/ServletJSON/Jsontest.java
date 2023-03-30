package com.tomjheng.lilicoco.SUN_NEW.ServletJSON;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

@RestController
@RequestMapping("/PrintWriter")
public class Jsontest extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @GetMapping(value = "writerJSON")
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        //接受前台發送的數據，通過流的形式接收數據
        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream(), "utf-8"));    //將數據使用流進行傳遞
        StringBuffer strb = new StringBuffer();
        String line;
        while ((line = reader.readLine()) != null) {    //遍曆數據
            strb = strb.append(line);            //數據暫存StringBuffer
        }
        Gson g = new Gson();
        User o = g.fromJson(String.valueOf(strb), User.class);
        System.out.println("接受數據為" + strb);
        //User o= JSON.parseObject(strb.toString(), User.class);

        //設置之後前端接收到的是一個對象
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=UTF-8");
        System.out.println(o);
        PrintWriter out = response.getWriter();

        out.write(g.toJson(o));//通過流的形式響應
    }

    @PostMapping(value = "writerJSON")

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
