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
public class Login extends HttpServlet{
    public void doPost(
            HttpServletRequest request,HttpServletResponse response){
            try{
                request.setCharacterEncoding("UTF-8");
            }catch(UnsupportedEncodingException e){};
                HttpSession session = request.getSession(); //セッション情報を取得
                String user_id = request.getParameter("user_id"); //リクエストパラメータからユーザーidを取得。
                String user_password = request.getParameter("user_password"); //リクエストパラメータからパスワードを取得。
                UserDAO dao = new UserDAO(); //UserDaoのインスタンスを生成
                User user = dao.search(user_id,user_password); //UserDaoのsearchメソッドにユーザー名,パスワードを渡す。
                login(user,user_id,session,request,response);
            }
    public void login(User user,String user_id,HttpSession session,HttpServletRequest request,HttpServletResponse response){
            if(user!=null){//顧客情報がある場合、ログイン
                session.setAttribute("user",user); //session情報をmypage.jspに渡す
                session.setAttribute("user_id",user_id); //session情報をmypage.jspに渡す
                String url = "/UNION/login/mypage.jsp";
                try{
		            response.sendRedirect(url);//mypage.jspへリダイレクト。
                }catch(IOException e){
                    System.out.println("mypage.jspへの遷移に失敗しました。");
                };
                    
            }else{
                String url = "/UNION/login/login-error.jsp";
                try{
		            response.sendRedirect(url);//login-error.jspへリダイレクト。
                }catch(IOException e){
                    System.out.println("login-error.jspへの遷移に失敗しました。");
                };
            }
    }
}
