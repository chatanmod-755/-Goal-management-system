package goal;

import bean.Goal;
import dao.Goal_listDAO;
import dao.Goal_update_year_DAO;
import dao.Delete_year_DAO;
import dao.Goal_select_year_DAO;

import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns={"/goal/Goal_delete_year_child"})
public class Goal_delete_year_child extends HttpServlet {//子目標削除
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException{
        HttpSession session = request.getSession();//セッション情報を取得。
        String goal_year_child_id = request.getParameter("goal_year_child_id");//子目標id取得
        String goal_year_parent_id = (String)session.getAttribute("goal_year_parent_id");//親目標id取得
        Delete_year_DAO del_year  = new Delete_year_DAO();
        Goal_edit_year edit_year = new Goal_edit_year();

        if(goal_year_child_id == null){//目標が選択されているか判定
            String url = "/UNION/goal/Goal_check_error.jsp";
		    response.sendRedirect(url);
        }else{
            if(del_year.child_del(goal_year_child_id,goal_year_parent_id)){//子目標を削除
                edit_year.select_child(goal_year_parent_id,request,response);//子目標を表示
            }else{
                String url = "/UNION/goal/delete-goal-error.jsp";
		        response.sendRedirect(url);//目標削除失敗ページへリダイレクト。
            }
        }
    }
}