package goal;

import bean.Goal;
import dao.Goal_listDAO;
import dao.Goal_update_year_DAO;
import dao.Goal_select_year_DAO;

import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns={"/goal/Goal_rename_year_child"})
public class Goal_rename_year_child extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException{
        request.setCharacterEncoding("UTF-8");
        Goal_edit_year edit_year = new Goal_edit_year();
        HttpSession session = request.getSession();//セッション情報を取得。
        String goal_year_child_id = request.getParameter("goal_year_child_id");//パラメーターから取得
        String goal_rename = request.getParameter("goal_rename");//パラメーターから取得
        String goal_year_parent_id = (String)session.getAttribute("goal_year_parent_id");//セッション情報から取得
        System.out.println("goal_year_parent_id");
        System.out.println(goal_year_parent_id);
        System.out.println("goal_rename");
        System.out.println(goal_rename);

        if(goal_year_child_id == null){
            String url = "/UNION/goal/goal_update_id_check_error.jsp";
		    response.sendRedirect(url);
        }else if(goal_rename(goal_year_child_id,goal_year_parent_id,goal_rename)){
            edit_year.select_child(goal_year_parent_id,request,response);//子目標を表示
        }else{
            String url = "/UNION/goal/update-name-error.jsp";
		    response.sendRedirect(url);
        }
    }
    //public void goal_rename(String goal_year_child_id,String goal_year_parent_id,String goal_rename,HttpServletRequest request, HttpServletResponse response){//目標追加
    public Boolean goal_rename(String goal_year_child_id,String goal_year_parent_id,String goal_rename){//目標追加
        Goal_update_year_DAO dao = new Goal_update_year_DAO();
        
        try{
            Boolean update = dao.goal_year_child_rename(goal_year_child_id,goal_rename,goal_year_parent_id);//目標期間取得
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
}
