package login;

import bean.User;
import dao.UserDAO;

import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import java.io.UnsupportedEncodingException;

@WebServlet(urlPatterns={"/login/Login"})
public class Login extends HttpServlet{//ログイン
    public void doPost(
            HttpServletRequest request,HttpServletResponse response){
            try{
                request.setCharacterEncoding("UTF-8");
            }catch(UnsupportedEncodingException e){};
                HttpSession session = request.getSession(); //セッション情報を取得
                String user_id = request.getParameter("user_id"); //ユーザーid取得。
                String user_password = request.getParameter("user_password"); //パスワード取得。
                UserDAO dao = new UserDAO();
                User user = dao.search(user_id,user_password);//ユーザーid、パスワードが一致しているか確認
                login(user,user_id,session,request,response);
            }

    public void login(User user,String user_id,HttpSession session,HttpServletRequest request,HttpServletResponse response){
            if(user!=null){//ユーザー情報がある場合ログイン
                session.setAttribute("user",user); //sessionにユーザー情報を代入
                session.setAttribute("user_id",user_id); //sessionにユーザーidを代入
                String url = "/UNION/login/mypage.jsp";

                try{
		            response.sendRedirect(url);//Myページへリダイレクト。
                }catch(IOException e){
                    e.printStackTrace();
                };
            }else{
                String url = "/UNION/login/login-error.jsp";
                try{
		            response.sendRedirect(url);//login失敗ページへリダイレクト。
                }catch(IOException e){
                    e.printStackTrace();
                };
            }
    }
}
