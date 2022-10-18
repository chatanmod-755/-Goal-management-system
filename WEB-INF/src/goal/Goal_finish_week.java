package goal;

import dao.Goal_listDAO;
import dao.Goal_update_week_DAO;
import dao.Goal_select_week_DAO;
import bean.Goal;
import java.sql.Date;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import javax.servlet.ServletException;
import java.util.List;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns={"/goal/Goal_finish_week"})
public class Goal_finish_week extends HttpServlet {//週間目標進捗率アップデート
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException{
        HttpSession session = request.getSession();//セッション情報を取得。
        String goal_id = (String)session.getAttribute("goal_id");//目標id取得
        String goal_week_id = request.getParameter("goal_week_id");//週間目標idを取得
        String goal_count = request.getParameter("goal_condition");//達成回数を取得
        Goal_update_week_DAO dao = new Goal_update_week_DAO();
        Goal_select_week_DAO select_dao = new Goal_select_week_DAO();
        Goal_listDAO rate = new Goal_listDAO();

        try{
            Boolean goal_achieve_update = dao.goal_achieve_update(goal_count,goal_week_id);//週間目標進捗表のアップデート
            if(goal_achieve_update){
                List<Goal> Goal_achievement_list = select_dao.select_achievement_list(goal_id);
                List<Goal> Goal_not_achievement_list = select_dao.select_not_achievement_list(goal_id);
                List<Goal> Goal_achievement_rate = select_dao.select_achievement_rate(goal_id);
                long miliseconds = System.currentTimeMillis();
                Date date = new Date(miliseconds);
                Goal g = Goal_achievement_rate.get(0);
                String achievement_rate = g.get_achievement_rate();
                Boolean achievement_rate_update = dao.achievement_rate_update(goal_id,date,achievement_rate);//週間進捗表の進捗率アップデート
                List<Goal> goal_progress_date_jsp = rate.select_goal_progress_date(goal_id);
                session.setAttribute("goal_progress_date",goal_progress_date_jsp);//目標期間を代入
                session.setAttribute("Goal_achievement_rate",Goal_achievement_rate);//目標達成率を代入
                session.setAttribute("Goal_achievement_list",Goal_achievement_list);//達成した目標を代入
                session.setAttribute("Goal_not_achievement_list",Goal_not_achievement_list);//未達成の目標を代入
                String url = "/UNION/goal/goal_progress_week.jsp";
                response.sendRedirect(url);//週間目標進捗ページへリダイレクト
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
