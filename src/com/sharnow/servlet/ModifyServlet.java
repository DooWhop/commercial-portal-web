package com.sharnow.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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

public class ModifyServlet extends BaseServlet {
    /**
     * 请求处理方法的参数都与doGet()和doPost()相同，即request和response
     * 但请求处理方法有String类型的返回值，而doGet()和doPost()没有返回值。
     * 在请求本方法时需要给出method=regist参数！
     */
//访问本方法的URL为http://localhost:8080/day01/AServlet?method=regist](HttpServletRequest req, HttpServletResponse resp)
    public String modify(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
    	File[] htmls = {new File("D:/workspace/sharnow/WebContent/index.html")};  	
 
    	String oldhtml = request.getParameter("oldhtml");
    	String newhtml = request.getParameter("newhtml");
    	
		for (File h : htmls) {
			Document doc = JsoupUtils.getDoc(h);
			Elements els = doc.getElementsContainingOwnText(oldhtml);
			
			Iterator<Element> it = els.iterator();
			while(it.hasNext()){
				Element el = it.next();
				if(el.text().equals(oldhtml)){
					System.out.println(el);
					el.html(newhtml);
					break;
				}			
				
			}
			
			Iterator<Element> it1 = JsoupUtils.getTags(doc, "p");
			JsoupUtils.removeClass(it1, "jsoup_modify");
			
			
			FileUtils.writeFiles(doc.html(), h);
			
			 response.setContentType("text/html;charset=UTF-8");
	         response.setHeader("Pragma", "No-cache");
	         response.setHeader("Cache-Control", "no-cache");
	         response.setDateHeader("Expires", 0);
	         PrintWriter writer = response.getWriter();
	         writer.write(doc.html());
	         writer.flush();
	         writer.close();
	         return null;
    	}
		
		
	
    //	JsoupUtils.
        System.out.println("login Servlet login()...");
        String content = "hello world";
       return null;
       // return "/index.html?"+new Date().getTime();//转发到/index.jsp页面
    }
    
}