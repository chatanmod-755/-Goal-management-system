package goal;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns={"/goal/Hello"})
public class Hello extends HttpServlet{
        public void doGet(
                HttpServletRequest request,HttpServletResponse response
        ) throws ServletException,IOException{
		response.setContentType("text/plain; charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.println("Hello!");
                out.println("こんにちは!");	
                out.println(new java.util.Date());
        }
}