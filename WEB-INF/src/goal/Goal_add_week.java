package goal;

import bean.Goal;
import dao.Goal_listDAO;
import dao.Goal_update_week_DAO;

import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns={"/goal/Goal_add_week"})
public class Goal_add_week extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException{
        HttpSession session = request.getSession();//セッション情報を取得。
        String goal_week_id = (String)session.getAttribute("goal_week_id");//セッション情報から取得
        String user_id = (String)session.getAttribute("user_id");//セッション情報から取得
        Goal_update_week_DAO dao = new Goal_update_week_DAO();
        Goal_edit_week edit_week  = new Goal_edit_week();
        if(dao.goal_add(goal_week_id,user_id)){
            edit_week.select(goal_week_id,request,response);//最新の情報を取得
        }else{
            try{
                System.out.println("週間目標追加失敗");
                String url = "/UNION/goal/Goal_add_error.jsp";
                response.sendRedirect(url);
            }catch(Exception k){
                System.out.println("例外処理発生");
                System.out.println("週間目標追加エラー画面リダイレクトに失敗しました。");
                k.printStackTrace();
            }
        }
    }
}
