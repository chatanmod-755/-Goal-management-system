package goal;

import bean.Goal;
import dao.Goal_listDAO;
import dao.Goal_update_year_DAO;

import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns={"/goal/Goal_rename_year_parent"})
public class Goal_rename_year_parent extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException{
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();//セッション情報を取得。
        String goal_year_id = (String)session.getAttribute("goal_year_id");//セッション情報から取得
        String goal_year_parent_id = request.getParameter("goal_year_parent_id");//パラメーターから取得
        String goal_rename = request.getParameter("goal_rename");//パラメーターから取得
        System.out.println("goal_year_parent_id");
        System.out.println(goal_year_parent_id);
        System.out.println("goal_year_id");
        System.out.println(goal_year_id);
        System.out.println("goal_rename");
        System.out.println(goal_rename);

        if(goal_year_parent_id == null){
            String url = "/UNION/goal/goal_update_id_check_error.jsp";
		    response.sendRedirect(url);
        }else if(goal_rename(goal_year_parent_id,goal_rename)){
            Goal_edit_year edit_year  = new Goal_edit_year();
            edit_year.select(goal_year_id,request,response);
        }else{
            String url = "/UNION/goal/update-name-error.jsp";
		    response.sendRedirect(url);
        }

    }
    public Boolean goal_rename(String goal_year_parent_id,String goal_rename){
        Goal_update_year_DAO dao = new Goal_update_year_DAO();

        try{
            Boolean update = dao.goal_year_parent_rename(goal_year_parent_id,goal_rename);//目標期間取得
            if(update){
                System.out.println("目標名報更新成功");
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
