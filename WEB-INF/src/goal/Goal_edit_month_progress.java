package goal;

import bean.Goal;
import dao.Goal_listDAO;
import dao.Goal_select_month_DAO;
import goal.JFreeChartFunctions;
import java.sql.Date;
import dao.Goal_update_month_DAO;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.io.UnsupportedEncodingException;

@WebServlet(urlPatterns={"/goal/Goal_edit_month_progress"})
public class Goal_edit_month_progress extends HttpServlet {//目標の達成状況を表示
    public void select_month_judge(String goal_id,HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();//セッション情報を取得。
        Goal_select_month_DAO dao = new Goal_select_month_DAO();
        Goal_listDAO date = new Goal_listDAO();
        Goal_update_month_DAO update_dao = new Goal_update_month_DAO();

        try{
            List<Goal> Goal_achievement_parent_list = dao.select_achievement_parent_list(goal_id);
            List<Goal> Goal_not_achievement_parent_list = dao.select_not_achievement_parent_list(goal_id);
            List<Goal> Goal_achievement_child_list = dao.select_achievement_child_list(goal_id);
            List<Goal> Goal_not_achievement_child_list = dao.select_not_achievement_child_list(goal_id);
            List<Goal> Goal_achievement_rate = dao.select_achievement_rate(goal_id);
            List<Goal> month_date = date.search_date(goal_id);
            session.setAttribute("Goal_achievement_parent_list",Goal_achievement_parent_list);//達成した親目標を代入
            session.setAttribute("Goal_not_achievement_parent_list",Goal_not_achievement_parent_list);//達成してない親目標を代入
            session.setAttribute("Goal_achievement_child_list",Goal_achievement_child_list);//達成した子目標を代入
            session.setAttribute("Goal_not_achievement_child_list",Goal_not_achievement_child_list);//未達成の子目標を代入
            session.setAttribute("Goal_achievement_rate",Goal_achievement_rate);//目標達成率設定
            session.setAttribute("month_date",month_date); //目標期間の情報を代入
            session.setAttribute("goal_id",goal_id); //目標idの情報を代入            
            Goal g = Goal_achievement_rate.get(0);
            String achievement_rate = g.get_achievement_rate();
            long miliseconds = System.currentTimeMillis();
            Date day = new Date(miliseconds);
            Boolean achievement_rate_update = update_dao.achievement_rate_update(goal_id,day,achievement_rate);//進捗表の進捗率をアップデート                            
            List<Goal> goal_progress_date_jsp = date.select_goal_progress_month_date(goal_id);
            session.setAttribute("goal_progress_date",goal_progress_date_jsp);//進捗表の日付と進捗率を代入
            String url = "/UNION/goal/goal_progress_month.jsp";
            response.sendRedirect(url);//月間目標進捗率ページへリダイレクト
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
