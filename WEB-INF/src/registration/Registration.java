package registration;

import bean.User;
import dao.RegistrationDAO;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import java.io.UnsupportedEncodingException;

@WebServlet(urlPatterns={"/registration/Registration"})
public class Registration extends HttpServlet{//ユーザー名登録
    public void doPost(
            HttpServletRequest request,HttpServletResponse response){
                int i = 0;
                String user_name = request.getParameter("user_name"); //ユーザー名取得
                String user_id = request.getParameter("user_id"); //ユーザーid取得。
                String user_password = request.getParameter("user_password"); //パスワード取得。
                int check = check(user_id,user_name,user_password,request,response);//ユーザー名とパスワードが空白 or nullか判定

                if (check == i){//iが0の場合は、ユーザー名・パスワードが空白じゃない為会員登録の処理を行う。
                    RegistrationDAO dao = new RegistrationDAO();
                    Boolean registration = dao.insert(user_id,user_name,user_password); //ユーザー登録
                    registration(registration,request,response);
                }else{
                    try{
                        String url = "/UNION/registration/registration-error.jsp";
                        response.sendRedirect(url);//ユーザー登録失敗処理ページへリダイレクト
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                }

    }

    public void registration(Boolean registration,HttpServletRequest request, HttpServletResponse response){
            if(registration){//DBに登録成功した場合は、ログイン画面へ遷移
                try{
                    String url = "/UNION/login/login.jsp";
                    response.sendRedirect(url);//ログインページへリダイレクト
                }catch(IOException e){
                        e.printStackTrace();
                    }
            }else{
                try{
                    String url = "/UNION/registration/registration-user-id-check-error.jsp";
                    response.sendRedirect(url);//ユーザーid、ユーザー名が重複失敗ページリダイレクト
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
    }

    public int check(String user_name,String user_id,String user_password,HttpServletRequest request, HttpServletResponse response){
            int i = 0;
            if(user_name == null || user_name.isEmpty()){//ユーザー名が、空白もしくはnullか確認。
                i =1;//ユーザー名が空白で会員登録できないので、iに1を代入する。
                try{
                    String url = "/UNION/registration/registration-user-error.jsp";
                    response.sendRedirect(url);//ユーザー名が空白。失敗ページへリダイレクト
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
            if(user_id == null || user_id.isEmpty()){//ユーザーidが、空白もしくはnullか確認。
                i =1;//ユーザーidが空白で会員登録できないので、iに1を代入する。
                try{
                    String url = "/UNION/registration/registration-user_id-error.jsp";
                    response.sendRedirect(url);//ユーザーidが空白。失敗ページへリダイレクト
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
            if(user_password == null || user_password.isEmpty()){//パスワードが、空白もしくはnullか確認。
                i =1;//パスワードが空白で会員登録できないので、iに1を代入する。
                try{
                    String url = "/UNION/registration/registration-password-error.jsp";
                    response.sendRedirect(url);//パスワードが空白。失敗ページへリダイレクト
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        return i;
    }
    
}
