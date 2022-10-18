package goal;

import bean.Goal;
import dao.Goal_listDAO;
import dao.Goal_update_month_DAO;
import dao.Goal_select_month_DAO;

import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns={"/goal/Goal_add_month_child"})
public class Goal_add_month_child extends HttpServlet {//子目標追加
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException{
        HttpSession session = request.getSession();//セッション情報を取得。
        request.setCharacterEncoding("UTF-8");
        String goal_name   = request.getParameter("goal_rename");//目標名取得
        String goal_month_id = (String)session.getAttribute("goal_month_id");//月間目標id取得
        String goal_month_parent_id = (String)session.getAttribute("goal_month_parent_id");//月間親目標id取得
        String user_id = (String)session.getAttribute("user_id");//ユーザーid取得
        goal_add(goal_month_id,goal_month_parent_id,user_id,goal_name,request,response);
    }

    public void goal_add(String goal_month_id,String goal_month_parent_id,String user_id,String goal_name,HttpServletRequest request, HttpServletResponse response){//目標登録
        try{
            Goal_update_month_DAO add_dao = new Goal_update_month_DAO();
            Goal_select_month_DAO select_dao = new Goal_select_month_DAO();
            Goal_edit_month edit_month = new Goal_edit_month();
            Boolean add = add_dao.goal_month_child_add(goal_month_id,goal_month_parent_id,user_id,goal_name);
            
            if(add){
                edit_month.select_child(goal_month_parent_id,request,response);//子目標を表示
            }else{
                try{
                    String url = "/UNION/goal/Goal_add_error.jsp";
                    response.sendRedirect(url);//目標追加失敗ページへリダイレクト
                }catch(Exception l){
                    l.printStackTrace();
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            try{
                String url = "/UNION/goal/Goal_add_error.jsp";
                response.sendRedirect(url);//目標追加失敗ページへリダイレクト
            }catch(Exception k){
                k.printStackTrace();
            }
        }
    }
}
