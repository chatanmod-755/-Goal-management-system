package goal;

import dao.Goal_listDAO;
import bean.Goal;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import javax.servlet.ServletException;
import java.util.List;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns={"/goal/Goal_edit"})
public class Goal_edit extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException{
        HttpSession session = request.getSession();//セッション情報を取得。

        String user_id = (String)session.getAttribute("user_id");//セッション情報からuser_idを取得

        Goal_listDAO dao = new Goal_listDAO();//Goal_listDAOのインスタンスを生成

        try{
            List<Goal> list = dao.search(user_id);//Goal_listDAOのsearchメソッドへuser_idを渡す
            session.setAttribute("list",list); //sessionへlistの情報をセット
            System.out.println("list表示");
            System.out.println(list.goal_type);
            System.out.println(list.goal_start_date);
            System.out.println(list.goal_end_date);
        }catch(Exception e){
            System.out.println("listの情報を取得できませんでした。");
        }
        String url = "/UNION/goal/goal_edit.jsp";
        try{
		    response.sendRedirect(url);//check_edit.jspへリダイレクト。
        }catch(IOException e){
            System.out.println("check_edit.jspへリダイレクトに失敗しました。");
        }
    }
}
