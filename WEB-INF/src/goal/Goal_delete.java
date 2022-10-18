package goal;

import dao.Goal_listDAO;
import dao.Goal_deleteDAO_week;
import goal.Goal_delete_month;
import goal.Goal_delete_year;
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

@WebServlet(urlPatterns={"/goal/Goal_delete"})
public class Goal_delete extends HttpServlet {//目標削除
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException{
        Goaltype_checkDAO check= new Goaltype_checkDAO();
        HttpSession session = request.getSession();//セッション情報を取得。
        String goal_id = request.getParameter("goal_id");//目標idを取得。
        String user_id = (String)session.getAttribute("user_id");//ユーザーidを取得
        Goal_listDAO dao = new Goal_listDAO();//Goal_listDAOのインスタンスを生成

        try{
            String goal_type  = check.check_type(goal_id);//目標の種類を取得。
            if(goal_type.equals("1")){//週間目標判定
                try{
                    Goal_deleteDAO_week del_week  = new Goal_deleteDAO_week();
                    if(del_week.delete(goal_id)){//週間目標削除
                        List<Goal> list = dao.search(user_id);
                        session.setAttribute("list",list); //sessionへ最新の目標情報を代入
                        String url = "/UNION/goal/goal_edit.jsp";
                        response.sendRedirect(url);//目標編集ページへリダイレクト。
                    }else{
                        String url = "/UNION/goal/delete-goal-error.jsp";
                        response.sendRedirect(url);//目標削除失敗ページへリダイレクト。
                    }
                }catch(Exception e){
                    e.printStackTrace();
                    String url = "/UNION/goal/delete-goal-error.jsp";
                    response.sendRedirect(url);//目標削除失敗ページへリダイレクト。
                }
            }else if(goal_type.equals("2")){//月間目標判定
                try{
                    Goal_delete_month del_month  = new Goal_delete_month(); 
                    if(del_month.delete(goal_id)){//月間目標削除
                        List<Goal> list = dao.search(user_id);
                        session.setAttribute("list",list); //sessionへ最新の目標情報を代入
                        String url = "/UNION/goal/goal_edit.jsp";
                        response.sendRedirect(url);//目標編集ページへリダイレクト。
                    }else{
                        String url = "/UNION/goal/delete-goal-error.jsp";
                        response.sendRedirect(url);//目標削除失敗ページへリダイレクト。
                    }
                }catch(Exception e){
                    String url = "/UNION/goal/delete-goal-error.jsp";
                    response.sendRedirect(url);//目標削除失敗ページへリダイレクト。                    
                    e.printStackTrace();
                }
            }else if(goal_type.equals("3")){//年間目標判定
                try{
                    Goal_delete_year del_year  = new Goal_delete_year();
                    if(del_year.delete(goal_id)){//年間目標削除
                        List<Goal> list = dao.search(user_id);
                        session.setAttribute("list",list); //sessionへ最新の目標情報を代入
                        String url = "/UNION/goal/goal_edit.jsp";
                        response.sendRedirect(url);//目標編集ページへリダイレクト。
                    }else{
                        String url = "/UNION/goal/delete-goal-error.jsp";
                        response.sendRedirect(url);//目標削除失敗ページへリダイレクト。
                    }
                }catch(Exception e){
                    e.printStackTrace();
                    String url = "/UNION/goal/goal_edit.jsp";
                    response.sendRedirect(url);//目標編集ページへリダイレクト。                    
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            String url = "/UNION/goal/delete-goal-error.jsp";
            response.sendRedirect(url);//目標削除失敗ページへリダイレクト。
        }
    }
}



