package goal;

import bean.Goal;
import dao.Goal_listDAO;
import dao.Goal_select_year_DAO;

import java.util.List;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns={"/goal/Goal_edit_year"})
public class Goal_edit_year extends HttpServlet {//年間目標編集情報取得
    public void select(String goal_id,HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();//セッション情報を取得
        Goal_select_year_DAO dao = new Goal_select_year_DAO();
        Goal_listDAO date = new Goal_listDAO();

        try{
            List<Goal> year_parent_all_list = dao.select_goal_parent_all(goal_id);
            List<Goal> year_date = date.search_date(goal_id);
            session.setAttribute("year_parent_all_list",year_parent_all_list); //親目標の情報を代入
            session.setAttribute("year_date",year_date); //期間情報を代入
            session.setAttribute("goal_year_id",goal_id); //親目標idの情報を代入
        }catch(Exception e){
            try{
                String url = "/UNION/goal/goal_select_error.jsp";
                response.sendRedirect(url);//目標取得失敗ページへリダイレクト
            }catch(Exception s){
                s.printStackTrace();
            }
        }

        try{
            String url = "/UNION/goal/goal_edit_year.jsp";
		    response.sendRedirect(url);//年間目標編集ページへリダイレクト
            return;
        }catch(IOException e){
            try{
                String url = "/UNION/goal/goal_select_error.jsp";
                response.sendRedirect(url);//目標取得失敗ページへリダイレクト
            }catch(IOException s){
                s.printStackTrace();
            }
        }
    }

    public void select_child(String goal_year_parent_id,HttpServletRequest request, HttpServletResponse response){//子目標情報を取得
        HttpSession session = request.getSession();//セッション情報を取得。
        Goal_select_year_DAO select_dao = new Goal_select_year_DAO();
        try{
            List<Goal> year_child_list = select_dao.select_goal_child(goal_year_parent_id);
            session.setAttribute("year_child_list",year_child_list); //子目標の情報を代入
            String url = "/UNION/goal/goal_edit_year_child.jsp";
            response.sendRedirect(url);//年間子目標編集ページへリダイレクト
        }catch(Exception e){
            try{
                String url = "/UNION/goal/goal_select_error.jsp";
                response.sendRedirect(url);//目標取得失敗ページへリダイレクト
            }catch(IOException s){
                e.printStackTrace();
            }
        }
    }
}
