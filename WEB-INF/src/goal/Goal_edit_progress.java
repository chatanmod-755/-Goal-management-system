package goal;

import bean.Goal;
import dao.Goal_listDAO;
import dao.Goal_select_week_DAO;
import goal.JFreeChartFunctions;
import dao.Goal_update_week_DAO;

import java.sql.Date;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.io.UnsupportedEncodingException;
import java.io.PrintWriter;

@WebServlet(urlPatterns={"/goal/Goal_edit_progress"})
public class Goal_edit_progress extends HttpServlet {//目標進捗情報取得
    public void select_week_judge(String goal_id,HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();//セッション情報を取得。
        Goal_select_week_DAO dao = new Goal_select_week_DAO();
        Goal_listDAO date = new Goal_listDAO();
        Goal_update_week_DAO update_dao = new Goal_update_week_DAO();
        long miliseconds = System.currentTimeMillis();
        Date day = new Date(miliseconds);

        try{
            List<Goal> Goal_achievement_list = dao.select_achievement_list(goal_id);
            List<Goal> Goal_not_achievement_list = dao.select_not_achievement_list(goal_id);
            List<Goal> Goal_achievement_rate = dao.select_achievement_rate(goal_id);
            List<Goal> week_date = date.search_date(goal_id);
            session.setAttribute("Goal_achievement_list",Goal_achievement_list);//目標達成リストを代入
            session.setAttribute("Goal_not_achievement_list",Goal_not_achievement_list);//目標未達成リストを代入
            session.setAttribute("Goal_achievement_rate",Goal_achievement_rate);//目標達成率を代入
            session.setAttribute("week_date",week_date); //目親標期間の情報を代入
            session.setAttribute("goal_id",goal_id); //目標idを代入
            
            Goal g = Goal_achievement_rate.get(0);
            String achievement_rate = g.get_achievement_rate();
            Boolean achievement_rate_update = update_dao.achievement_rate_update(goal_id,day,achievement_rate);//週間進捗表の進捗率をアップデート
            List<Goal> goal_progress_date_jsp = date.select_goal_progress_date(goal_id);
            String url = "/UNION/goal/goal_progress_week.jsp";//目標進捗ページへリダイレクト
            response.sendRedirect(url);
        }catch(Exception e){
            try{
                String url = "/UNION/goal/goal_select_error.jsp";
                response.sendRedirect(url);//目標取得失敗ページへリダイレクト。
            }catch(IOException s){
                s.printStackTrace();
            }
        }
    }
}
