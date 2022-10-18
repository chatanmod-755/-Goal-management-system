package goal;

import bean.Goal;
import dao.Goal_listDAO;
import dao.Goal_update_month_DAO;
import dao.Delete_month_DAO;
import dao.Goal_select_month_DAO;

import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns={"/goal/Goal_delete_month_child"})
public class Goal_delete_month_child extends HttpServlet {//子目標削除
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException{
        HttpSession session = request.getSession();//セッション情報を取得。
        String goal_month_child_id = request.getParameter("goal_month_child_id");//子目標id取得
        String goal_month_parent_id = (String)session.getAttribute("goal_month_parent_id");//親目標id取得
        Delete_month_DAO del_month  = new Delete_month_DAO();
        Goal_edit_month edit_month = new Goal_edit_month();

        if(goal_month_child_id == null){//目標が選択されているか判定
            String url = "/UNION/goal/Goal_check_error.jsp";
		    response.sendRedirect(url);
        }else{
            if(del_month.child_del(goal_month_child_id,goal_month_parent_id)){//子目標を削除
                edit_month.select_child(goal_month_parent_id,request,response);//子目標を表示
            }else{
                String url = "/UNION/goal/delete-goal-error.jsp";
		        response.sendRedirect(url);//目標削除失敗ページへリダイレクト。
            }
        }
    }
}