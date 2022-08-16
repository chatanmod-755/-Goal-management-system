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
public class Goal_edit_year_child extends HttpServlet {
   public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException{
        HttpSession session = request.getSession();//セッション情報を取得。
        Goal_select_year_DAO dao = new Goal_select_year_DAO();
        String goal_year_parent_id = request.getParameter("goal_year_parent_id");//親idを取得
        try{
            if(goal_year_parent_id == null){
                String url = "/UNION/goal/Goal_check_error.jsp";
                response.sendRedirect(url);
            }else{
                List<Goal> year_parent_list = dao.select_goal_parent(goal_year_parent_id);//親目標期間取得
                List<Goal> year_child_list = dao.select_goal_child(goal_year_parent_id);//子目標取得
                session.setAttribute("year_parent_list",year_parent_list); //sessionへ親目標の情報をセット
                session.setAttribute("year_child_list",year_child_list); //sessionへ子目標の情報をセット
                session.setAttribute("goal_year_parent_id",goal_year_parent_id); //sessionへ親目標のid情報をセット
                String url = "/UNION/goal/goal_edit_year_child.jsp";
                response.sendRedirect(url);
            }
        }catch(Exception e){
            System.out.println("listの情報を取得できませんでした。");
            e.printStackTrace();
            String url = "/UNION/goal/goal_select_error.jsp";
            response.sendRedirect(url);
        }
    }
}
