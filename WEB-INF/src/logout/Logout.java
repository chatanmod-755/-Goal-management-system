package logout;

import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import java.io.IOException;

@WebServlet(urlPatterns={"/logout/Logout"})
public class Logout extends HttpServlet{//ログアウト
    public void doPost(
        HttpServletRequest request,HttpServletResponse response){
        HttpSession session=request.getSession();//セッション情報を取得

        if(session.getAttribute("user")!=null){//セッション情報にユーザー情報がある事を確認
            session.removeAttribute("user");//セッション情報のユーザーを削除する。
            String url = "/UNION/login/login.jsp";
            try{
		        response.sendRedirect(url);//ログインページへリダイレクト。
            }catch(IOException e){
                e.printStackTrace();
            };
        }
    }
}