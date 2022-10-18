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
public class Goal_edit extends HttpServlet {//目標情報取得
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException{
        HttpSession session = request.getSession();//セッション情報を取得
        String user_id = (String)session.getAttribute("user_id");//ユーザーid取得
        Goal_listDAO dao = new Goal_listDAO();

        try{
            List<Goal> list = dao.search(user_id);
            session.setAttribute("list",list); //目標情報を代入
        }catch(Exception e){
            String url = "/UNION/goal/goal_select_error.jsp";
            response.sendRedirect(url);//目標取得失敗ページへリダイレクト
            e.printStackTrace();
        }

        try{
            String url = "/UNION/goal/goal_edit.jsp";
		    response.sendRedirect(url);//check_edit.jspへリダイレクト。
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
