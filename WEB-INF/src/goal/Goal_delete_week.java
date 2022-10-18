package goal;

import bean.Goal;
import dao.Goal_listDAO;
import dao.Goal_update_week_DAO;
import dao.Goal_select_week_DAO;

import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns={"/goal/Goal_delete_week"})
public class Goal_delete_week extends HttpServlet {//週間目標削除
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException{
        HttpSession session = request.getSession();//セッション情報を取得。
        String goal_id = (String)session.getAttribute("goal_week_id");//目標id取得
        String goal_week_id = request.getParameter("goal_week_id");//週間目標id取得
        Goal_update_week_DAO del_week  = new Goal_update_week_DAO();
        Goal_edit_week edit_week = new Goal_edit_week();

        if(del_week.goal_delete(goal_week_id)){ //週間目標の目標を削除
            edit_week.select(goal_id,request,response);//目標を表示
        }else{
            String url = "/UNION/goal/delete-goal-error.jsp";
		    response.sendRedirect(url);//目標削除失敗ページへリダイレクト。
        }
    }
}