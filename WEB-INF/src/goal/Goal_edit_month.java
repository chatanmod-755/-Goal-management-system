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
public class Goal_edit_month extends HttpServlet {
    public void select(String goal_id,HttpServletRequest request, HttpServletResponse response){//目標を表示する
        System.out.println("goal_idきた");
        System.out.println(goal_id);
        HttpSession session = request.getSession();//セッション情報を取得。
        Goal_select_month_DAO dao = new Goal_select_month_DAO();
        Goal_listDAO date = new Goal_listDAO();
        try{
            System.out.println("goal_idきた--");
            System.out.println(goal_id);
            List<Goal> month_parent_all_list = dao.select_goal_parent_all(goal_id);//目標期間取得
            List<Goal> month_date = date.search_date(goal_id);//目標期間取得
            session.setAttribute("month_parent_all_list",month_parent_all_list); //sessionへ親目標の情報をセット
            session.setAttribute("month_date",month_date); //sessionへ親目標の期間情報をセット
            session.setAttribute("goal_month_id",goal_id); //sessionへ目親標idの情報をセット
        }catch(Exception e){
            System.out.println("listの情報を取得できませんでした。");
            e.printStackTrace();
        }
        String url = "/UNION/goal/goal_edit_month.jsp";
        try{
		    response.sendRedirect(url);
            return;
        }catch(IOException e){
            System.out.println("goal_edit_month.jspへリダイレクトに失敗しました。");
        }
    }
    public void select_child(String goal_month_parent_id,HttpServletRequest request, HttpServletResponse response){//目標を表示する
        HttpSession session = request.getSession();//セッション情報を取得。
        Goal_select_month_DAO select_dao = new Goal_select_month_DAO();
        try{
            List<Goal> month_child_list = select_dao.select_goal_child(goal_month_parent_id);//子目標取得
            session.setAttribute("month_child_list",month_child_list); //sessionへ子目標の情報をセット
            String url = "/UNION/goal/goal_edit_month_child.jsp";
            response.sendRedirect(url);
        }catch(Exception e){
            System.out.println("listの情報を取得できませんでした。");
            e.printStackTrace();
        }
    }
}
