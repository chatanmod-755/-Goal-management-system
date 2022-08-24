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

@WebServlet(urlPatterns={"/goal/Goal_delete_year_parent"})
public class Goal_delete_year_parent extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException{
        HttpSession session = request.getSession();//セッション情報を取得。
        String goal_year_id = (String)session.getAttribute("goal_year_id");//セッション情報から取得
        String user_id = (String)session.getAttribute("user_id");//セッション情報から取得
        String goal_year_parent_id = request.getParameter("goal_year_parent_id");//セッション情報から取得

        Delete_year_DAO del_year  = new Delete_year_DAO();
        Goal_select_year_DAO dao = new Goal_select_year_DAO();
        Goal_edit_year edit_year  = new Goal_edit_year();

        if(goal_year_parent_id == null){
            String url = "/UNION/goal/Goal_check_error.jsp";
		    response.sendRedirect(url);
        }else{
            if(dao.check_child(goal_year_parent_id)){//子目標にデータがあるか判定
                System.out.println("子目標にデータあるので削除しにいきます");
                if(del_year.childs_del(goal_year_parent_id) && del_year.parent_del(goal_year_parent_id)){
                    edit_year.select(goal_year_id,request,response);
                }else{
                    String url = "/UNION/goal/delete-goal-error.jsp";
		            response.sendRedirect(url);//目標削除失敗ページへリダイレクト。
                }
                System.out.println("月間目標削除成功");
            }else{//親目標を削除
                System.out.println("親目標削除成功");
                if(del_year.parent_del(goal_year_parent_id)){//月間目表から親目標idを削除
                    edit_year.select(goal_year_id,request,response);
                }else{
                    String url = "/UNION/goal/delete-goal-error.jsp";
		            response.sendRedirect(url);//目標削除失敗ページへリダイレクト。
                }
            }
        }
    }
}
