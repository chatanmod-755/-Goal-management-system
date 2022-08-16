package login;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns={"/login/Test"})
public class Test extends HttpServlet{
        public void doPost(
                HttpServletRequest request,HttpServletResponse response
        ) throws ServletException,IOException{
	    response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();

            request.setCharacterEncoding("UTF-8");
            String user_name=request.getParameter("user_name");
            String user_password=request.getParameter("user_password");

            out.println("<p>こんにちは、"+user_name+"さん</p>");
            out.println("<p>パスワードは、"+user_password+"さん</p>");
        }
}