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
public class Registration extends HttpServlet{
    public void doPost(
            HttpServletRequest request,HttpServletResponse response){
                int i = 0;
                String user_name = request.getParameter("user_name"); //リクエストパラメータからユーザー名を取得。
                String user_id = request.getParameter("user_id"); //リクエストパラメータからユーザーidを取得。
                String user_password = request.getParameter("user_password"); //リクエストパラメータからパスワードを取得。
                int check = check(user_id,user_name,user_password,request,response);//user_nameとuser_passwordが空白,nullを判定

                if (check == i){//iが0の場合は、ユーザー名・パスワードが空白じゃない為会員登録の処理を行う。
                    RegistrationDAO dao = new RegistrationDAO(); //RegistrationDAOのインスタンスを生成
                    Boolean registration = dao.insert(user_id,user_name,user_password); //UserDaoのsearchメソッドにユーザー名,パスワードを渡す。
                    registration(registration,request,response);
                }else{
                    try{
                        String url = "/UNION/registration/registration-error.jsp";
                        response.sendRedirect(url);
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                }

    }
    public void registration(Boolean registration,HttpServletRequest request, HttpServletResponse response){
            if(registration){//DBに登録成功した場合は、ログイン画面へ遷移
                //session.setAttribute("user",user); //session情報をmypage.jspに渡す
                /*RequestDispatcher dispatch = request.getRequestDispatcher("/login/login.jsp");
                try{
		            dispatch.forward(request, response);
                }catch(IOException | IOException e){};
                    System.out.println("login.jspへの遷移時に例外処理発生。");*/
                try{
                    String url = "/UNION/login/login.jsp";
                    response.sendRedirect(url);
                }catch(IOException e){
                        e.printStackTrace();
                    }
            }else{
                /*RequestDispatcher dispatch = request.getRequestDispatcher("registration.jsp");
                try{
		            dispatch.forward(request, response);
                }catch(IOException | IOException e){};
                    System.out.println("Registration.jspへの遷移に失敗しました。");*/
                try{
                    String url = "/UNION/registration/registration-user-id-check-error.jsp";
                    response.sendRedirect(url);
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
    }
    public int check(String user_name,String user_id,String user_password,HttpServletRequest request, HttpServletResponse response){
            int i = 0;
            if(user_name == null || user_name.isEmpty()){//ユーザー名が、空白もしくはnullか確認。
                i =1;//ユーザー名が空白で会員登録できないので、iに1を代入する。
                /*RequestDispatcher dispatch = request.getRequestDispatcher("registration-user-error.jsp");
                try{
                    dispatch.forward(request, response);
                    System.out.println("ユーザーが空白の為、会員登録に失敗しました。");
                }catch(IOException | IOException e){};*/
                try{
                    System.out.println("ユーザーが空白の為、会員登録に失敗しました。");
                    String url = "/UNION/registration/registration-user-error.jsp";
                    response.sendRedirect(url);
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
            if(user_id == null || user_id.isEmpty()){//ユーザーidが、空白もしくはnullか確認。
                i =1;//ユーザーidが空白で会員登録できないので、iに1を代入する。
                /*RequestDispatcher dispatch = request.getRequestDispatcher("registration-user_id-error.jsp");
                try{
                    dispatch.forward(request, response);
                    System.out.println("ユーザーidが空白の為、会員登録に失敗しました。");
                }catch(IOException | IOException e){};*/
                try{
                    System.out.println("ユーザーidが空白の為、会員登録に失敗しました。");
                    String url = "/UNION/registration/registration-user_id-error.jsp";
                    response.sendRedirect(url);
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
            if(user_password == null || user_password.isEmpty()){//パスワードが、空白もしくはnullか確認。
                i =1;//パスワードが空白で会員登録できないので、iに1を代入する。
                /*RequestDispatcher dispatch = request.getRequestDispatcher("registration-password-error.jsp");
                try{
                    dispatch.forward(request, response);
                    System.out.println("パスワードが空白の為、会員登録に失敗しました。");
                }catch(IOException | IOException e){};*/
                try{
                    System.out.println("パスワードが空白の為、会員登録に失敗しました。");
                    String url = "/UNION/registration/registration-password-error.jsp";
                    response.sendRedirect(url);
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        return i;
    }
    
}
