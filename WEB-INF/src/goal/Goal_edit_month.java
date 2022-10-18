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

@WebServlet(urlPatterns={"/goal/Goal_edit_month"})
public class Goal_edit_month extends HttpServlet {//親・子目標取得
    public void select(String goal_id,HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();//セッション情報を取得。
        Goal_select_month_DAO dao = new Goal_select_month_DAO();
        Goal_listDAO date = new Goal_listDAO();

        try{
            List<Goal> month_parent_all_list = dao.select_goal_parent_all(goal_id);
            List<Goal> month_date = date.search_date(goal_id);
            session.setAttribute("month_parent_all_list",month_parent_all_list); //sessionへ全ての親目標の情報を代入
            session.setAttribute("month_date",month_date); //sessionへ期間情報を代入
            session.setAttribute("goal_month_id",goal_id); //sessionへ目親標idを代入
        }catch(Exception e){
            try{
                String url = "/UNION/goal/goal_select_error.jsp";
                response.sendRedirect(url);//目標取得失敗ページへリダイレクト
                e.printStackTrace();
             }catch(IOException s){
                s.printStackTrace();
             }   
        }

        try{
            String url = "/UNION/goal/goal_edit_month.jsp";
		    response.sendRedirect(url);//親目標編集ページへリダイレクト
            return;
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void select_child(String goal_month_parent_id,HttpServletRequest request, HttpServletResponse response){//子目標取得
        HttpSession session = request.getSession();//セッション情報を取得。
        Goal_select_month_DAO select_dao = new Goal_select_month_DAO();

        try{
            List<Goal> month_child_list = select_dao.select_goal_child(goal_month_parent_id);
            session.setAttribute("month_child_list",month_child_list); //sessionへ子目標の情報を代入
            String url = "/UNION/goal/goal_edit_month_child.jsp";
            response.sendRedirect(url);//子目標編集ページへリダイレクト
        }catch(Exception e){
            try{
                String url = "/UNION/goal/goal_select_error.jsp";
                response.sendRedirect(url);//目標取得失敗ページへリダイレクト            
            }catch(IOException s){
                s.printStackTrace();
            }
        }
    }
}
