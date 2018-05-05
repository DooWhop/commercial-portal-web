package com.sharnow.servlet;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.sharnow.utils.FileUtils;
import com.sharnow.utils.JsoupUtils;

public class LoginServlet extends BaseServlet {
    /**
     * 请求处理方法的参数都与doGet()和doPost()相同，即request和response
     * 但请求处理方法有String类型的返回值，而doGet()和doPost()没有返回值。
     * 在请求本方法时需要给出method=regist参数！
     */
//访问本方法的URL为http://localhost:8080/day01/AServlet?method=regist](HttpServletRequest req, HttpServletResponse resp)
    public String login(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
    	
    	//File[] htmls = FileUtils.searchFiles(FileUtils.getRootPath(), "html");
    	File[] htmls = {new File("D:/workspace/sharnow/WebContent/index.html")};
    	for (File h : htmls) {
			Document doc = JsoupUtils.getDoc(h);
			Iterator<Element> it = JsoupUtils.getTags(doc, "p");
			JsoupUtils.addClass(it, "jsoup_modify");
			FileUtils.writeFiles(doc.html(), h);
    	}

        System.out.println("login Servlet login()...");
        return "/index.html?"+new Date().getTime();//转发到/index.jsp页面
    }
    
    /**
     * 在请求本方法时需要给出method=login参数！
     */
//访问本方法的URL为http://localhost:8080/day01/AServlet?method=login](HttpServletRequest req, HttpServletResponse resp)
    public String regist(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        System.out.println("AServlet login()...");
        return "r:/NewFile.html";//重定向到/index.jsp
    }
}