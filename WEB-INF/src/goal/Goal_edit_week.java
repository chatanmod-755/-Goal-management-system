package goal;

import bean.Goal;
import dao.Goal_listDAO;
import dao.Goal_select_week_DAO;

import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns={"/goal/Goal_edit_week"})
public class Goal_edit_week extends HttpServlet {//目標編集情報取得
    public void select(String goal_id,HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();//セッション情報を取得。
        Goal_select_week_DAO dao = new Goal_select_week_DAO();
        Goal_listDAO date = new Goal_listDAO();

        try{
            List<Goal> week_list = dao.select_goal_list(goal_id);
            List<Goal> week_date = date.search_date(goal_id);
            session.setAttribute("week_list",week_list);//目標情報を代入
            session.setAttribute("week_date",week_date); //目標の期間情報を代入
            session.setAttribute("goal_week_id",goal_id); //週間目標idを代入
        }catch(Exception e){
            e.printStackTrace();
        }

        try{
            String url = "/UNION/goal/goal_edit_week.jsp";
		    response.sendRedirect(url);//目標編集ページへリダイレクト
            return;
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
