package goal;

import bean.Goal;
import dao.Goal_listDAO;
import dao.Goal_update_week_DAO;
import dao.Goal_select_week_DAO;
import java.sql.Date;

import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns={"/goal/Goal_update_week"})
public class Goal_update_week extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException{
        request.setCharacterEncoding("UTF-8");
        //response.setContentType("text/html; charset=UTF-8");
        Goal_update_week_DAO dao = new Goal_update_week_DAO();
        Goal_listDAO rate = new Goal_listDAO();
        Goal_select_week_DAO select_dao = new Goal_select_week_DAO();
        Goal_edit_week edit_week = new Goal_edit_week();
        HttpSession session = request.getSession();//セッション情報を取得。
        String goal_week_id = request.getParameter("goal_week_id");//パラメーターから取得
        String goal_rename = request.getParameter("goal_rename");//パラメーターから取得
        String goal_condition = request.getParameter("goal_condition");//パラメーターから取得
        String goal_id = (String)session.getAttribute("goal_week_id");//セッション情報から取得
        long miliseconds = System.currentTimeMillis();
        Date date = new Date(miliseconds);
        System.out.println("goal_id");
        System.out.println(goal_id);
        System.out.println("goal_week__id");
        System.out.println(goal_week_id);
        System.out.println("goal_rename");
        System.out.println(goal_rename);
        System.out.println("goal_condition");
        System.out.println(goal_condition);

        if(goal_week_id == null){
            System.out.println("目標idがない");
            String url = "/UNION/goal/goal_update_id_check_error.jsp";
            response.sendRedirect(url);
        }else if(goal_rename.isEmpty() && goal_condition.isEmpty()){
            System.out.println("目標情報がない");
            String url = "/UNION/goal/goal_update_week_error.jsp";
            response.sendRedirect(url);
        }

        if(goal_week_id != null && !goal_rename.isEmpty() && goal_condition.isEmpty()){//目標名を更新する
            if(goal_rename(goal_week_id,goal_id,goal_rename,request,response)){
                edit_week.select(goal_id,request,response);//子目標を表示
            }else{
                String url = "/UNION/goal/update-name-error.jsp";
		        response.sendRedirect(url);
            }
            
        }

        if(goal_week_id != null && goal_rename.isEmpty() && !goal_condition.isEmpty()){//目標回数を更新する
            if(goal_condition_update(goal_week_id,goal_id,goal_condition,request,response)){
                edit_week.select(goal_id,request,response);//子目標を表示
            }else{
                String url = "/UNION/goal/update-condition-error.jsp";
		        response.sendRedirect(url);
            }
            /*try{
                Boolean goal_achieve_update = dao.goal_achieve_update(goal_condition,goal_week_id);//週間進捗表のアップデート
                List<Goal> Goal_achievement_rate = select_dao.select_achievement_rate(goal_id);
                Goal g = Goal_achievement_rate.get(0);
                String achievement_rate = g.get_achievement_rate();
                List<Goal> goal_progress_date_jsp = rate.select_goal_progress_date(goal_id);
                Boolean achievement_rate_update = dao.achievement_rate_update(goal_id,date,achievement_rate);//週間進捗表の進捗率をアップデート
                System.out.println("goal_progress_dateをセッションに入れるよー");
                session.setAttribute("goal_progress_date",goal_progress_date_jsp);//進捗表の日付と進捗率を設定。
            }catch (Exception e){
                e.printStackTrace();
            }*/

        }

        if(goal_week_id != null && !goal_rename.isEmpty() && !goal_condition.isEmpty()){//目標名と目標回数を更新する
            if(goal_update(goal_week_id,goal_id,goal_condition,goal_rename,request,response)){
                edit_week.select(goal_id,request,response);//目標を表示
            }else{
                String url = "/UNION/goal/update-condition-name-error.jsp";
		        response.sendRedirect(url);
            }
            /*try{
                Boolean goal_achieve_update = dao.goal_achieve_update(goal_condition,goal_week_id);//週間進捗表のアップデート
                List<Goal> Goal_achievement_rate = select_dao.select_achievement_rate(goal_id);
                Goal g = Goal_achievement_rate.get(0);
                String achievement_rate = g.get_achievement_rate();
                List<Goal> goal_progress_date_jsp = rate.select_goal_progress_date(goal_id);
                Boolean achievement_rate_update = dao.achievement_rate_update(goal_id,date,achievement_rate);//週間進捗表の進捗率をアップデート
                System.out.println("goal_progress_dateをセッションに入れるよー");
                session.setAttribute("goal_progress_date",goal_progress_date_jsp);//進捗表の日付と進捗率を設定。
            }catch (Exception e){
                e.printStackTrace();
            }*/
        }

        //goal_update(goal_week__id,goal_rename,goal_condition,request,response);
    }
    
    public Boolean goal_rename(String goal_week_id,String goal_id,String goal_rename,HttpServletRequest request, HttpServletResponse response){//目標更新
        Goal_update_week_DAO dao = new Goal_update_week_DAO();
        try{
            Boolean update = dao.goal_rename(goal_week_id,goal_rename);//目標期間取得
            if(update){
                System.out.println("目標名更新成功");
                return true;
            }else{
                System.out.println("目標名更新失敗");
                return false;
            }
        }catch(Exception e){
            System.out.println("例外処理発生");
            e.printStackTrace();
            return false;
        }
    }

    public Boolean goal_condition_update(String goal_week_id,String goal_id,String goal_condition,HttpServletRequest request, HttpServletResponse response){//目標更新
        Goal_update_week_DAO dao = new Goal_update_week_DAO();
        try{
            Boolean update = dao.condition_update(goal_week_id,goal_condition);//目標期間取得
            if(update){
                System.out.println("目標回数更新成功");
                return true;
            }else{
                System.out.println("目標回数更新失敗");
                return false;
            }
        }catch(Exception e){
            System.out.println("例外処理発生");
            e.printStackTrace();
            return false;
        }
    }

    public Boolean goal_update(String goal_week_id,String goal_id,String goal_condition,String goal_rename,HttpServletRequest request, HttpServletResponse response){//目標更新
        Goal_update_week_DAO dao = new Goal_update_week_DAO();
        try{
            Boolean condition_update = dao.condition_update(goal_week_id,goal_condition);//目標期間取得
            Boolean rename_update = dao.goal_rename(goal_week_id,goal_rename);//目標期間取得
            if(condition_update && rename_update){
                System.out.println("目標回数・目標名報更新成功");
                return true;
            }else{
                System.out.println("目標回数・目標名報更新失敗");
                return false;
            }
        }catch(Exception e){
            System.out.println("例外処理発生");
            e.printStackTrace();
            return false;
        }
    }
}
