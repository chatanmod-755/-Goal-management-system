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
public class Goal_edit_year extends HttpServlet {
    public void select(String goal_id,HttpServletRequest request, HttpServletResponse response){//目標を表示する
        System.out.println("goal_idきた");
        System.out.println(goal_id);
        HttpSession session = request.getSession();//セッション情報を取得。
        Goal_select_year_DAO dao = new Goal_select_year_DAO();
        Goal_listDAO date = new Goal_listDAO();
        try{
            System.out.println("goal_idきた--");
            System.out.println(goal_id);
            List<Goal> year_parent_all_list = dao.select_goal_parent_all(goal_id);//目標期間取得
            List<Goal> year_date = date.search_date(goal_id);//目標期間取得
            session.setAttribute("year_parent_all_list",year_parent_all_list); //sessionへ親目標の情報をセット
            session.setAttribute("year_date",year_date); //sessionへ親目標の期間情報をセット
            session.setAttribute("goal_year_id",goal_id); //sessionへ目親標idの情報をセット
        }catch(Exception e){
            System.out.println("listの情報を取得できませんでした。");
            e.printStackTrace();
        }
        String url = "/UNION/goal/goal_edit_year.jsp";
        try{
		    response.sendRedirect(url);
            return;
        }catch(IOException e){
            System.out.println("goal_edit_year.jspへリダイレクトに失敗しました。");
        }
    }
    public void select_child(String goal_year_parent_id,HttpServletRequest request, HttpServletResponse response){//目標を表示する
        HttpSession session = request.getSession();//セッション情報を取得。
        Goal_select_year_DAO select_dao = new Goal_select_year_DAO();
        try{
            List<Goal> year_child_list = select_dao.select_goal_child(goal_year_parent_id);//子目標取得
            session.setAttribute("year_child_list",year_child_list); //sessionへ子目標の情報をセット
            String url = "/UNION/goal/goal_edit_year_child.jsp";
            response.sendRedirect(url);
        }catch(Exception e){
            System.out.println("listの情報を取得できませんでした。");
            e.printStackTrace();
        }
    }
}
