package goal;

import bean.Goal;
import dao.Goal_listDAO;
import dao.Goal_select_month_DAO;

import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns={"/goal/Goal_edit_month_child"})
public class Goal_edit_month_child extends HttpServlet {//子目標表示
   public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException{
        HttpSession session = request.getSession();//セッション情報を取得。
        Goal_select_month_DAO dao = new Goal_select_month_DAO();
        String goal_month_parent_id = request.getParameter("goal_month_parent_id");//親id取得
        
        try{
            if(goal_month_parent_id == null){//目標を選択されているか判定
                String url = "/UNION/goal/Goal_check_error.jsp";
                response.sendRedirect(url);
            }else{
                List<Goal> month_parent_list = dao.select_goal_parent(goal_month_parent_id);//親目標取得
                List<Goal> month_child_list = dao.select_goal_child(goal_month_parent_id);//子目標取得
                session.setAttribute("month_parent_list",month_parent_list); //sessionへ親目標の情報を代入
                session.setAttribute("month_child_list",month_child_list); //sessionへ子目標の情報を代入
                session.setAttribute("goal_month_parent_id",goal_month_parent_id); //sessionへ親目標のidを代入
                String url = "/UNION/goal/goal_edit_month_child.jsp";
                response.sendRedirect(url);//子目標編集ページへリダイレクト
            }
        }catch(Exception e){
            e.printStackTrace();
            String url = "/UNION/goal/goal_select_error.jsp";
            response.sendRedirect(url);//目標取得失敗ページへリダイレクト。
        }
    }
}
