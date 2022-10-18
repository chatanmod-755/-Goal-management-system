package goal;

import bean.Goal;
import dao.Goal_listDAO;
import dao.Goal_select_year_DAO;

import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns={"/goal/Goal_edit_year_child"})
public class Goal_edit_year_child extends HttpServlet {//子目標編集情報取得
   public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException{
        HttpSession session = request.getSession();//セッション情報を取得。
        Goal_select_year_DAO dao = new Goal_select_year_DAO();
        String goal_year_parent_id = request.getParameter("goal_year_parent_id");//親id取得

        try{
            if(goal_year_parent_id == null){//目標を選択されているか判定
                String url = "/UNION/goal/Goal_check_error.jsp";
                response.sendRedirect(url);
            }else{
                List<Goal> year_parent_list = dao.select_goal_parent(goal_year_parent_id);
                List<Goal> year_child_list = dao.select_goal_child(goal_year_parent_id);
                session.setAttribute("year_parent_list",year_parent_list); //親目標の情報を代入
                session.setAttribute("year_child_list",year_child_list); //子目標の情報を代入
                session.setAttribute("goal_year_parent_id",goal_year_parent_id); //親idを代入
                String url = "/UNION/goal/goal_edit_year_child.jsp";//子目標編集ページへリダイレクト
                response.sendRedirect(url);
            }
        }catch(Exception e){
            e.printStackTrace();
            String url = "/UNION/goal/goal_select_error.jsp";
            response.sendRedirect(url);
        }
    }
}
