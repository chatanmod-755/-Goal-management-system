package login;

import javax.servlet.http.*;
import javax.servlet.ServletException;
import java.io.PrintWriter;
import java.io.IOException;

public class Postt extends HttpServlet{
    public void doPost(
        HttpServletRequest request,HttpServletResponse response
    )throws ServletException,IOException{
        	response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            request.setCharacterEncoding("UTF-8");
    }
}
    