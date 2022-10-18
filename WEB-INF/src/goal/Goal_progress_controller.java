package goal;

import dao.Goal_listDAO;
//import goal.Goal_edit_week;
import goal.Goal_edit_month;
import goal.Goal_edit_year;
import goal.Goal_edit_progress;
import goal.Goal_edit_month_progress;
import goal.Goal_edit_year_progress;
import dao.Goaltype_checkDAO;
import bean.Goal;

import java.io.PrintWriter;
import java.util.Map;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import javax.servlet.ServletException;
import java.util.List;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns={"/goal/Goal_progress_contlloer"})
public class Goal_progress_controller extends HttpServlet {//進捗表情報取得
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException{
        Goaltype_checkDAO check= new Goaltype_checkDAO();
        HttpSession session = request.getSession();//セッション情報を取得
        String goal_id = request.getParameter("goal_id");//目標id取得

        try{
            String goal_type  = check.check_type(goal_id);//目標の種類を取得
            if(goal_type.equals("1")){//週間目標判定
                try{
                    Goal_edit_progress edit_week  = new Goal_edit_progress();
                    edit_week.select_week_judge(goal_id,request,response);//週間進捗表に必要な情報取得
                }catch(Exception e){
                    e.printStackTrace();
                }
            }else if(goal_type.equals("2")){//月間目標判定
                try{
                    Goal_edit_month_progress edit_month  = new Goal_edit_month_progress();
                    edit_month.select_month_judge(goal_id,request,response);//月間進捗表に必要な情報取得
                }catch(Exception e){
                    e.printStackTrace();
                }
            }else if(goal_type.equals("3")){//年間目標判定
                try{
                    Goal_edit_year_progress edit_year  = new Goal_edit_year_progress();
                    edit_year.select_year_judge(goal_id,request,response);//年間進捗表に必要な情報取得
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        String user_id = (String)session.getAttribute("user_id");//ユーザーid取得
        Goal_listDAO dao = new Goal_listDAO();

        if(goal_id == null){//目標選択の有無確認
            String url = "/UNION/goal/goal_progress_error.jsp";
            response.sendRedirect(url);//目標選択失敗ページへリダイレクト
        }
    }
}



