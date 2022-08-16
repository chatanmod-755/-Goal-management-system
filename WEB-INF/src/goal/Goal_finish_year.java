package goal;

import dao.Goal_listDAO;
import dao.Goal_update_year_DAO;
import dao.Goal_select_year_DAO;
import bean.Goal;
import java.sql.Date;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import javax.servlet.ServletException;
import java.util.List;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns={"/goal/Goal_finish_year"})
public class Goal_finish_year extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException{
        HttpSession session = request.getSession();//セッション情報を取得。
        String goal_id = (String)session.getAttribute("goal_id");//セッション情報から取得
        String goal_year_child_id = request.getParameter("goal_year_child_id");//月間目標の子目標idを取得
        System.out.println("Goal_finish_yearきたよー");
        System.out.println("--Goal_id--");
        System.out.println(goal_id);
        System.out.println("--Goal_year_child_id--");
        System.out.println(goal_year_child_id);

        Goal_update_year_DAO update_dao = new Goal_update_year_DAO();
        Goal_select_year_DAO select_dao = new Goal_select_year_DAO();
        Goal_listDAO list_dao = new Goal_listDAO();

        try{
            Boolean goal_achieve_update = update_dao.goal_achieve_update(goal_year_child_id);//月間子目標のアップデート
            if(goal_achieve_update){
                System.out.println("年間子目標進捗更新");
                Boolean goal_achieve_parent_update = update_dao.goal_achieve_parent_update(goal_year_child_id);//月間親目標更新判定           
                List<Goal> Goal_achievement_parent_list = select_dao.select_achievement_parent_list(goal_id);//達成した親目標取得
                List<Goal> Goal_not_achievement_parent_list = select_dao.select_not_achievement_parent_list(goal_id);//達成してない親目標取得
                List<Goal> Goal_achievement_child_list = select_dao.select_achievement_child_list(goal_id);//達成した子目標取得
                List<Goal> Goal_not_achievement_child_list = select_dao.select_not_achievement_child_list(goal_id);//未達成の子目標取得
                List<Goal> Goal_achievement_rate = select_dao.select_achievement_rate(goal_id);//達成率表示
                List<Goal> year_date = list_dao.search_date(goal_id);
                Goal g = Goal_achievement_rate.get(0);
                String achievement_rate = g.get_achievement_rate();
                System.out.println("進捗率表示");
                System.out.println(achievement_rate);
                long miliseconds = System.currentTimeMillis();
                Date date = new Date(miliseconds);
                System.out.println(date);
                Boolean achievement_rate_update = update_dao.achievement_rate_update(goal_id,date,achievement_rate);//月間進捗表の進捗率をアップデート                
                List<Goal> goal_progress_date_jsp = list_dao.select_goal_progress_year_date(goal_id);
                session.setAttribute("goal_progress_date",goal_progress_date_jsp);//進捗表の日付と進捗率を設定
                session.setAttribute("Goal_achievement_parent_list",Goal_achievement_parent_list);
                session.setAttribute("Goal_not_achievement_parent_list",Goal_not_achievement_parent_list);
                session.setAttribute("Goal_achievement_child_list",Goal_achievement_child_list);
                session.setAttribute("Goal_not_achievement_child_list",Goal_not_achievement_child_list);
                session.setAttribute("Goal_achievement_rate",Goal_achievement_rate);//目標達成率設定
                String url = "/UNION/goal/goal_progress_year.jsp";
                response.sendRedirect(url);
            }else{
                System.out.println("年間目標進捗更新失敗");
                String url = "/UNION/goal/finish-progress-error.jsp";
                response.sendRedirect(url);
            }
        }catch(Exception e){
            System.out.println("例外処理発生");
            String url = "/UNION/goal/finish-progress-error.jsp";
            response.sendRedirect(url);
        }

        /*String url = "/UNION/goal/goal_progress_year.jsp";
        try{
		    response.sendRedirect(url);//check_edit.jspへリダイレクト。
        }catch(IOException e){
            System.out.println("check_edit.jspへリダイレクトに失敗しました。");
        }*/
    }
}
