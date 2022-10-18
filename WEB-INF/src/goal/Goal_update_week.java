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
public class Goal_update_week extends HttpServlet {//週間目標情報更新
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException{
        request.setCharacterEncoding("UTF-8");
        Goal_update_week_DAO dao = new Goal_update_week_DAO();
        Goal_listDAO rate = new Goal_listDAO();
        Goal_select_week_DAO select_dao = new Goal_select_week_DAO();
        Goal_edit_week edit_week = new Goal_edit_week();
        HttpSession session = request.getSession();//セッション情報を取得。
        String goal_week_id = request.getParameter("goal_week_id");//週間目標i取得
        String goal_rename = request.getParameter("goal_rename");//目標名取得
        String goal_condition = request.getParameter("goal_condition");//目標名回数取得
        String goal_id = (String)session.getAttribute("goal_week_id");//週間目標id取得
        long miliseconds = System.currentTimeMillis();
        Date date = new Date(miliseconds);

        if(goal_week_id == null){//目標選択判定
            String url = "/UNION/goal/goal_update_id_check_error.jsp";
            response.sendRedirect(url);//目標選択失敗ページへリダイレクト
        }else if(goal_rename.isEmpty() && goal_condition.isEmpty()){//目標回数・目標情報の記載判定
            String url = "/UNION/goal/goal_update_week_error.jsp";
            response.sendRedirect(url);//目標回数・目標情報の記載失敗ページへリダイレクト
        }

        if(goal_week_id != null && !goal_rename.isEmpty() && goal_condition.isEmpty()){
            if(goal_rename(goal_week_id,goal_id,goal_rename,request,response)){//目標名更新
                edit_week.select(goal_id,request,response);//目標を表示
            }else{
                String url = "/UNION/goal/update-name-error.jsp";
		        response.sendRedirect(url);//目標更新失敗ページへリダイレクト
            }
            
        }

        if(goal_week_id != null && goal_rename.isEmpty() && !goal_condition.isEmpty()){
            if(goal_condition_update(goal_week_id,goal_id,goal_condition,request,response)){//目標達成条件回数を更新する
                edit_week.select(goal_id,request,response);//目標を表示
            }else{
                String url = "/UNION/goal/update-condition-error.jsp";
		        response.sendRedirect(url);//目標更新失敗ページへリダイレクト
            }
        }

        if(goal_week_id != null && !goal_rename.isEmpty() && !goal_condition.isEmpty()){
            if(goal_update(goal_week_id,goal_id,goal_condition,goal_rename,request,response)){//目標名と目標達成条件回数を更新する
                edit_week.select(goal_id,request,response);//目標を表示
            }else{
                String url = "/UNION/goal/update-condition-name-error.jsp";
		        response.sendRedirect(url);//目標更新失敗ページへリダイレクト
            }
        }
    }
    
    public Boolean goal_rename(String goal_week_id,String goal_id,String goal_rename,HttpServletRequest request, HttpServletResponse response){
        Goal_update_week_DAO dao = new Goal_update_week_DAO();
        try{
            Boolean update = dao.goal_rename(goal_week_id,goal_rename);//目標名更新
            if(update){
                return true;
            }else{
                return false;
            }
        }catch(Exception e){
            try{
                String url = "/UNION/goal/update-name-error.jsp";
                response.sendRedirect(url);//目標名更新失敗ページへリダイレクト
            }catch(IOException s){
                s.printStackTrace();
            }
            return false;
        }
    }

    public Boolean goal_condition_update(String goal_week_id,String goal_id,String goal_condition,HttpServletRequest request, HttpServletResponse response){
        Goal_update_week_DAO dao = new Goal_update_week_DAO();
        try{
            Boolean update = dao.condition_update(goal_week_id,goal_condition);//目標達成条件回数更新
            if(update){
                return true;
            }else{
                return false;
            }
        }catch(Exception e){
            try{
                String url = "/UNION/goal/update-name-error.jsp";
                response.sendRedirect(url);//目標名更新失敗ページへリダイレクト
            }catch(IOException s){
                e.printStackTrace();
            }
            return false;
        }
    }

    public Boolean goal_update(String goal_week_id,String goal_id,String goal_condition,String goal_rename,HttpServletRequest request, HttpServletResponse response){
        Goal_update_week_DAO dao = new Goal_update_week_DAO();
        try{
            Boolean condition_update = dao.condition_update(goal_week_id,goal_condition);//目標達成条件回数更新
            Boolean rename_update = dao.goal_rename(goal_week_id,goal_rename);//目標名更新
            if(condition_update && rename_update){
                return true;
            }else{
                return false;
            }
        }catch(Exception e){
            try{
                String url = "/UNION/goal/update-name-error.jsp";
                response.sendRedirect(url);//目標名更新失敗ページへリダイレクト
            }catch(IOException s){
                e.printStackTrace();
            }
            return false;
        }
    }
}
