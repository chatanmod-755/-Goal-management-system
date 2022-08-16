package goal;

import bean.Goal;
import dao.Goal_listDAO;
import dao.Goal_update_month_DAO;

import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns={"/goal/Goal_rename_month_parent"})
public class Goal_rename_month_parent extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException{
        request.setCharacterEncoding("UTF-8");
        Goal_edit_month edit_month  = new Goal_edit_month();
        HttpSession session = request.getSession();//セッション情報を取得。
        String goal_month_id = (String)session.getAttribute("goal_month_id");//セッション情報から取得
        String goal_month_parent_id = request.getParameter("goal_month_parent_id");//パラメーターから取得
        String goal_rename = request.getParameter("goal_rename");//パラメーターから取得
        System.out.println("goal_month_parent_id");
        System.out.println(goal_month_parent_id);
        System.out.println("goal_month_id");
        System.out.println(goal_month_id);
        System.out.println("goal_rename");
        System.out.println(goal_rename);
        if(goal_rename(goal_month_parent_id,goal_rename)){
            edit_month.select(goal_month_id,request,response);
        }else{
            String url = "/UNION/goal/update-name-error.jsp";
		    response.sendRedirect(url);
        }
    }
    public Boolean goal_rename(String goal_month_parent_id,String goal_rename){//目標追加
        Goal_update_month_DAO dao = new Goal_update_month_DAO();

        try{
            Boolean update = dao.goal_month_parent_rename(goal_month_parent_id,goal_rename);//目標期間取得
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
