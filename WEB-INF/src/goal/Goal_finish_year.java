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
public class Goal_finish_year extends HttpServlet {//年間目標進捗率アップデート
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException{
        HttpSession session = request.getSession();//セッション情報を取得。
        String goal_id = (String)session.getAttribute("goal_id");//目標id取得
        String goal_year_child_id = request.getParameter("goal_year_child_id");//子目標id取得
        Goal_update_year_DAO update_dao = new Goal_update_year_DAO();
        Goal_select_year_DAO select_dao = new Goal_select_year_DAO();
        Goal_listDAO list_dao = new Goal_listDAO();

        try{
            Boolean goal_achieve_update = update_dao.goal_achieve_update(goal_year_child_id);//年間子目標進捗のアップデート
            if(goal_achieve_update){
                Boolean goal_achieve_parent_update = update_dao.goal_achieve_parent_update(goal_year_child_id);//月間親目標更新判定
                List<Goal> Goal_achievement_parent_list = select_dao.select_achievement_parent_list(goal_id);
                List<Goal> Goal_not_achievement_parent_list = select_dao.select_not_achievement_parent_list(goal_id);
                List<Goal> Goal_achievement_child_list = select_dao.select_achievement_child_list(goal_id);
                List<Goal> Goal_not_achievement_child_list = select_dao.select_not_achievement_child_list(goal_id);
                List<Goal> Goal_achievement_rate = select_dao.select_achievement_rate(goal_id);
                List<Goal> year_date = list_dao.search_date(goal_id);
                Goal g = Goal_achievement_rate.get(0);
                String achievement_rate = g.get_achievement_rate();
                long miliseconds = System.currentTimeMillis();
                Date date = new Date(miliseconds);
                Boolean achievement_rate_update = update_dao.achievement_rate_update(goal_id,date,achievement_rate);//月間進捗表の進捗率をアップデート                
                List<Goal> goal_progress_date_jsp = list_dao.select_goal_progress_year_date(goal_id);
                session.setAttribute("goal_progress_date",goal_progress_date_jsp);//目標期間を代入
                session.setAttribute("Goal_achievement_parent_list",Goal_achievement_parent_list);//達成した親目標情報を代入
                session.setAttribute("Goal_not_achievement_parent_list",Goal_not_achievement_parent_list);//達成してない親目標報を代入
                session.setAttribute("Goal_achievement_child_list",Goal_achievement_child_list);//達成した子目標標報を代入
                session.setAttribute("Goal_not_achievement_child_list",Goal_not_achievement_child_list);//未達成の子目標標報を代入
                session.setAttribute("Goal_achievement_rate",Goal_achievement_rate);//目標達成率設定
                String url = "/UNION/goal/goal_progress_year.jsp";
                response.sendRedirect(url);//年間目標進捗ページへリダイレクト
            }else{
                String url = "/UNION/goal/finish-progress-error.jsp";
                response.sendRedirect(url);//標進捗更新失敗ページへリダイレクト
            }
        }catch(Exception e){
            String url = "/UNION/goal/finish-progress-error.jsp";
            response.sendRedirect(url);//標進捗更新失敗ページへリダイレクト
        }
    }
}
