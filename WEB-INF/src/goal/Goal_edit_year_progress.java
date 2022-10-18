package goal;

import bean.Goal;
import dao.Goal_listDAO;
import dao.Goal_select_year_DAO;
import dao.Goal_update_year_DAO;
import goal.JFreeChartFunctions;
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

@WebServlet(urlPatterns={"/goal/Goal_edit_year_progress"})
public class Goal_edit_year_progress extends HttpServlet {
    public void select_year_judge(String goal_id,HttpServletRequest request, HttpServletResponse response){//目標を表示する
        HttpSession session = request.getSession();//セッション情報を取得。
        Goal_select_year_DAO dao = new Goal_select_year_DAO();
        Goal_listDAO date = new Goal_listDAO();
        Goal_update_year_DAO update_dao = new Goal_update_year_DAO();

        try{
            List<Goal> Goal_achievement_parent_list = dao.select_achievement_parent_list(goal_id);//達成した親目標取得
            List<Goal> Goal_not_achievement_parent_list = dao.select_not_achievement_parent_list(goal_id);//達成してない親目標取得
            List<Goal> Goal_achievement_child_list = dao.select_achievement_child_list(goal_id);//達成した子目標取得
            List<Goal> Goal_not_achievement_child_list = dao.select_not_achievement_child_list(goal_id);//未達成の子目標取得
            List<Goal> Goal_achievement_rate = dao.select_achievement_rate(goal_id);//達成率表示
            List<Goal> year_date = date.search_date(goal_id);
            session.setAttribute("Goal_achievement_parent_list",Goal_achievement_parent_list);
            session.setAttribute("Goal_not_achievement_parent_list",Goal_not_achievement_parent_list);
            session.setAttribute("Goal_achievement_child_list",Goal_achievement_child_list);
            session.setAttribute("Goal_not_achievement_child_list",Goal_not_achievement_child_list);
            session.setAttribute("Goal_achievement_rate",Goal_achievement_rate);//目標達成率設定
            session.setAttribute("year_date",year_date); //目親標期間の情報をセット
            session.setAttribute("goal_id",goal_id); //目親標idの情報をセット
            long miliseconds = System.currentTimeMillis();
            Date day = new Date(miliseconds);
            Goal g = Goal_achievement_rate.get(0);
            String achievement_rate = g.get_achievement_rate();
            Boolean achievement_rate_update = update_dao.achievement_rate_update(goal_id,day,achievement_rate);//年間進捗表の進捗率をアップデート
            List<Goal> goal_progress_date_jsp = date.select_goal_progress_year_date(goal_id);
            session.setAttribute("goal_progress_date",goal_progress_date_jsp);//進捗表の日付と進捗率を設定。
            String url = "/UNION/goal/goal_progress_year.jsp";
            response.sendRedirect(url);//月間目標進捗率ページへリダイレクト
        }catch(Exception e){
            try{
                String url = "/UNION/goal/goal_select_error.jsp";
                response.sendRedirect(url);//目標取得失敗ページへリダイレクト
            }catch(Exception s){
                s.printStackTrace();
            }
        }
    }
}
