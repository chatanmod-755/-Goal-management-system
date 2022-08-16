package goal;

import bean.Goal;
import dao.Goal_listDAO;
import dao.Goal_select_week_DAO;

import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns={"/goal/Goal_edit_week"})
public class Goal_edit_week extends HttpServlet {
    public void select(String goal_id,HttpServletRequest request, HttpServletResponse response){//目標を表示する
        System.out.println("goal_idきた");
        System.out.println(goal_id);
        HttpSession session = request.getSession();//セッション情報を取得。
        Goal_select_week_DAO dao = new Goal_select_week_DAO();
        Goal_listDAO date = new Goal_listDAO();
        try{
            System.out.println("goal_idきた--");
            System.out.println("goal_week改修版きた");
            System.out.println(goal_id);
            List<Goal> week_list = dao.select_goal_list(goal_id);//目標情報取得
            List<Goal> week_date = date.search_date(goal_id);//目標期間取得
            session.setAttribute("week_list",week_list);
            session.setAttribute("week_date",week_date); //sessionへ親目標の期間情報をセット
            session.setAttribute("goal_week_id",goal_id); //sessionへ目親標idの情報をセット
        }catch(Exception e){
            System.out.println("listの情報を取得できませんでした。");
            e.printStackTrace();
        }
        String url = "/UNION/goal/goal_edit_week.jsp";
        try{
		    response.sendRedirect(url);
            return;
        }catch(IOException e){
            System.out.println("goal_edit_week.jspへリダイレクトに失敗しました。");
        }
    }
}
