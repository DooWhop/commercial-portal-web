package com.sharnow.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * BaseServlet逕ｨ譚･菴應ｸｺ蜈ｶ螳ゴervlet逧�宛邀ｻ
 * 
 * @author qdmmy6
 * 
 *         荳�ｸｪ邀ｻ螟壻ｸｪ隸ｷ豎ょ､�炊譁ｹ豕包ｼ梧ｯ丈ｸｪ隸ｷ豎ょ､�炊譁ｹ豕慕噪蜴溷梛荳市ervice逶ｸ蜷鯉ｼ�蜴溷梛 = 霑泌屓蛟ｼ邀ｻ蝙�+ 譁ｹ豕募錐遘ｰ + 蜿よ焚蛻苓｡ｨ
 */
@SuppressWarnings("serial")
public class BaseServlet extends HttpServlet {
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");//螟�炊蜩榊ｺ皮ｼ也�
        
        /**
         * 1. 闔ｷ蜿卜ethod蜿よ焚�悟ｮ�弍逕ｨ謌ｷ諠ｳ隹�畑逧�婿豕�2. 謚頑婿豕募錐遘ｰ蜿俶�Method邀ｻ逧�ｮ樔ｾ句ｯｹ雎｡ 3. 騾夊ｿ㌍nvoke()譚･隹�畑霑吩ｸｪ譁ｹ豕�
         */
        String methodName = request.getParameter("method");
        if(methodName==null){
        	methodName="login";
        }
        Method method = null;
        /**
         * 2. 騾夊ｿ�婿豕募錐遘ｰ闔ｷ蜿邦ethod蟇ｹ雎｡
         */
        try {
            method = this.getClass().getMethod(methodName,
                    HttpServletRequest.class, HttpServletResponse.class);
        } catch (Exception e) {
            throw new RuntimeException("您要调用的方法：" + methodName + "它不存在!", e);
        }
        
        /**
         * 3. 騾夊ｿ㍊ethod蟇ｹ雎｡譚･隹�畑螳�
         */
        try {
            String result = (String)method.invoke(this, request, response);
            if(result != null && !result.trim().isEmpty()) {//螯よ棡隸ｷ豎ょ､�炊譁ｹ豕戊ｿ泌屓荳堺ｸｺ遨ｺ
                int index = result.indexOf(":");//闔ｷ蜿也ｬｬ荳�ｸｪ蜀貞捷逧�ｽ咲ｽｮ
                if(index == -1) {//螯よ棡豐｡譛牙�蜿ｷ�御ｽｿ逕ｨ霓ｬ蜿�
                    request.getRequestDispatcher(result).forward(request, response);
                } else {//螯よ棡蟄伜惠蜀貞捷
                    String start = result.substring(0, index);//蛻�牡蜃ｺ蜑咲ｼ�
                    String path = result.substring(index + 1);//蛻�牡蜃ｺ霍ｯ蠕�
                    if(start.equals("f")) {//蜑咲ｼ�ｸｺf陦ｨ遉ｺ霓ｬ蜿�                  	
                        request.getRequestDispatcher(path).forward(request, response);
                    } else if(start.equals("r")) {//蜑咲ｼ�ｸｺr陦ｨ遉ｺ驥榊ｮ壼髄
                        response.sendRedirect(request.getContextPath() + path);
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}