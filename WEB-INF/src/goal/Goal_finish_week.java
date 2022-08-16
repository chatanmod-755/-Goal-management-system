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
public class Goal_finish_week extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException{
        HttpSession session = request.getSession();//セッション情報を取得。
        String goal_id = (String)session.getAttribute("goal_id");//セッション情報から取得
        System.out.println("Goal_finish_weekきたよー");
        System.out.println("--Goal_id--");
        System.out.println(goal_id);
        String goal_week_id = request.getParameter("goal_week_id");//週間目標のgoal_week_idを取得
        String goal_count = request.getParameter("goal_condition");//達成回数を取得

        Goal_update_week_DAO dao = new Goal_update_week_DAO();
        Goal_select_week_DAO select_dao = new Goal_select_week_DAO();
        Goal_listDAO rate = new Goal_listDAO();

        try{
            Boolean goal_achieve_update = dao.goal_achieve_update(goal_count,goal_week_id);//週間進捗表のアップデート
            if(goal_achieve_update){
                System.out.println("週間目標進捗更新");
                List<Goal> Goal_achievement_list = select_dao.select_achievement_list(goal_id);
                List<Goal> Goal_not_achievement_list = select_dao.select_not_achievement_list(goal_id);
                List<Goal> Goal_achievement_rate = select_dao.select_achievement_rate(goal_id);
                long miliseconds = System.currentTimeMillis();
                Date date = new Date(miliseconds);
                System.out.println(date);
                Goal g = Goal_achievement_rate.get(0);
                String achievement_rate = g.get_achievement_rate();
                System.out.println("進捗率表示");
                System.out.println(achievement_rate);
                Boolean achievement_rate_update = dao.achievement_rate_update(goal_id,date,achievement_rate);//週間進捗表の進捗率をアップデート
                List<Goal> goal_progress_date_jsp = rate.select_goal_progress_date(goal_id);
                session.setAttribute("goal_progress_date",goal_progress_date_jsp);//進捗表の日付と進捗率を設定。
                session.setAttribute("Goal_achievement_rate",Goal_achievement_rate);//目標達成率設定
                session.setAttribute("Goal_achievement_list",Goal_achievement_list);//目標達成リスト設定
                session.setAttribute("Goal_not_achievement_list",Goal_not_achievement_list);//目標未達成リスト設定
                String url = "/UNION/goal/goal_progress_week.jsp";
                response.sendRedirect(url);
            }else{
                System.out.println("週間目標進捗更新失敗");
                String url = "/UNION/goal/finish-progress-error.jsp";
                response.sendRedirect(url);
            }
        }catch(Exception e){
            System.out.println("例外処理発生");
            String url = "/UNION/goal/finish-progress-error.jsp";
            response.sendRedirect(url);
        }

        /*String url = "/UNION/goal/goal_progress_week.jsp";
        try{
		    response.sendRedirect(url);//check_edit.jspへリダイレクト。
        }catch(IOException e){
            System.out.println("check_edit.jspへリダイレクトに失敗しました。");
        }*/
    }
}
