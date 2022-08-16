package logout;

import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import java.io.IOException;

@WebServlet(urlPatterns={"/logout/Logout"})
public class Logout extends HttpServlet{
    public void doPost(
        HttpServletRequest request,HttpServletResponse response){
            HttpSession session=request.getSession();//セッション情報を取得

        if(session.getAttribute("user")!=null){//セッション情報からユーザー取得できれはtrue
            System.out.println("セッション削除成功");
            session.removeAttribute("user");//セッション情報のユーザーを削除する。
            //RequestDispatcher dispatch = request.getRequestDispatcher("/login/login.jsp");
            String url = "/UNION/login/login.jsp";
            try{
		        response.sendRedirect(url);//login.jspへリダイレクト。
            }catch(IOException e){
                System.out.println("login.jspへの遷移に失敗しました。");
            };
        }
    }
}