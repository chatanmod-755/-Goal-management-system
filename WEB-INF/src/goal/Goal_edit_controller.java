package goal;

import dao.Goal_listDAO;
import goal.Goal_edit_week;
import goal.Goal_edit_month;
import goal.Goal_edit_year;
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

@WebServlet(urlPatterns={"/goal/Goal_edit_contlloer"})
public class Goal_edit_controller extends HttpServlet {//目標表示
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException{
        Goaltype_checkDAO check= new Goaltype_checkDAO();
        HttpSession session = request.getSession();//セッション情報を取得。
        String goal_id = request.getParameter("goal_id");//目標idを取得。

        try{
            String goal_type  = check.check_type(goal_id);//目標の種類を取得。
            if(goal_type.equals("1")){//週間目標判定
                try{
                    Goal_edit_week edit_week  = new Goal_edit_week();
                    edit_week.select(goal_id,request,response);//週間目標取得
                }catch(Exception e){
                    String url = "/UNION/goal/goal_select_error.jsp";
                    response.sendRedirect(url);//目標取得失敗ページへリダイレクト。
                    e.printStackTrace();
                }
            }else if(goal_type.equals("2")){//月間目標判定
                try{
                    Goal_edit_month edit_month  = new Goal_edit_month();
                    edit_month.select(goal_id,request,response);//月間目標取得
                }catch(Exception e){
                    String url = "/UNION/goal/goal_select_error.jsp";
                    response.sendRedirect(url);//目標取得失敗ページへリダイレクト。
                    e.printStackTrace();
                }
            }else if(goal_type.equals("3")){//年間目標判定
                try{
                    Goal_edit_year edit_year  = new Goal_edit_year();
                    edit_year.select(goal_id,request,response);//年間目標取得
                }catch(Exception e){
                    String url = "/UNION/goal/goal_select_error.jsp";
                    response.sendRedirect(url);//目標取得失敗ページへリダイレクト。
                    e.printStackTrace();
                }
            }
        }catch (Exception e){
            String url = "/UNION/goal/goal_select_error.jsp";
            response.sendRedirect(url);//目標取得失敗ページへリダイレクト。
            e.printStackTrace();
        }
        String user_id = (String)session.getAttribute("user_id");//ユーザーid取得
        Goal_listDAO dao = new Goal_listDAO();

        try{
            List<Goal> list = dao.search(user_id);
            session.setAttribute("list",list); //sessionへ最新の目標情報を代入
            String url = "/UNION/goal/goal_edit.jsp";
            response.sendRedirect(url);//目標編集ページへリダイレクト
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}



