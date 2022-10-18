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
public class Goal_rename_month_parent extends HttpServlet {//親目標名更新
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException{
        request.setCharacterEncoding("UTF-8");
        Goal_edit_month edit_month  = new Goal_edit_month();
        HttpSession session = request.getSession();//セッション情報を取得。
        String goal_month_id = (String)session.getAttribute("goal_month_id");//月間目標id取得
        String goal_month_parent_id = request.getParameter("goal_month_parent_id");//親目標id取得
        String goal_rename = request.getParameter("goal_rename");//目標名取得

        if(goal_month_parent_id == null){//目標選択判定
            String url = "/UNION/goal/goal_update_id_check_error.jsp";
		    response.sendRedirect(url);//目標選択失敗ページへリダイレクト
        }else if(goal_rename(goal_month_parent_id,goal_rename)){//子目標名変更
            edit_month.select(goal_month_id,request,response);//子目標を取得
        }
    }
    
    public Boolean goal_rename(String goal_month_parent_id,String goal_rename){
        Goal_update_month_DAO dao = new Goal_update_month_DAO();
        try{
            Boolean update = dao.goal_month_parent_rename(goal_month_parent_id,goal_rename);//親目標名更新
            if(update){
                return true;
            }else{
                return false;
            }
        }catch(Exception e){
            String url = "/UNION/goal/update-name-error.jsp";
            response.sendRedirect(url);//目標名更新失敗ページへリダイレクト
            e.printStackTrace();
            return false;
        }
    }
}
