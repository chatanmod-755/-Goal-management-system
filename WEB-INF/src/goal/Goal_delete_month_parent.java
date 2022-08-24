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

@WebServlet(urlPatterns={"/goal/Goal_delete_month_parent"})
public class Goal_delete_month_parent extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException{
        HttpSession session = request.getSession();//セッション情報を取得。
        String goal_month_id = (String)session.getAttribute("goal_month_id");//セッション情報から取得
        String user_id = (String)session.getAttribute("user_id");//セッション情報から取得
        String goal_month_parent_id = request.getParameter("goal_month_parent_id");//セッション情報から取得

        Delete_month_DAO del_month  = new Delete_month_DAO();
        Goal_select_month_DAO dao = new Goal_select_month_DAO();
        Goal_edit_month edit_month  = new Goal_edit_month();

        if(goal_month_parent_id == null){
            String url = "/UNION/goal/Goal_check_error.jsp";
		    response.sendRedirect(url);
        }else{
            if(dao.check_child(goal_month_parent_id)){//子目標にデータがあるか判定
                System.out.println("子目標にデータあるので削除しにいきます");
                if(del_month.childs_del(goal_month_parent_id) && del_month.parent_del(goal_month_parent_id)){
                    edit_month.select(goal_month_id,request,response);
                }else{
                    String url = "/UNION/goal/delete-goal-error.jsp";
		            response.sendRedirect(url);//目標削除失敗ページへリダイレクト。
                }
            }else{//親目標を削除
                System.out.println("子目標にデータがないので親目標だけ削除します");
                if(del_month.parent_del(goal_month_parent_id)){
                    edit_month.select(goal_month_id,request,response);
                }else{
                    String url = "/UNION/goal/delete-goal-error.jsp";
		            response.sendRedirect(url);//目標削除失敗ページへリダイレクト。
                }
            }
        }
    }
}
