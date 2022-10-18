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

@WebServlet(urlPatterns={"/goal/Goal_add_year_child"})
public class Goal_add_year_child extends HttpServlet {//子目標追加
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException{
        HttpSession session = request.getSession();//セッション情報を取得。
        request.setCharacterEncoding("UTF-8");
        String goal_name   = request.getParameter("goal_rename");//目標名取得
        String goal_year_id = (String)session.getAttribute("goal_year_id");//年間目標id取得
        String goal_year_parent_id = (String)session.getAttribute("goal_year_parent_id");//年間目標親id取得
        String user_id = (String)session.getAttribute("user_id");//user_id取得
        goal_add(goal_year_id,goal_year_parent_id,user_id,goal_name,request,response);
    }

    public void goal_add(String goal_year_id,String goal_year_parent_id,String user_id,String goal_name,HttpServletRequest request, HttpServletResponse response){//子目標登録
        try{
            Goal_update_year_DAO add_dao = new Goal_update_year_DAO();
            Goal_select_year_DAO select_dao = new Goal_select_year_DAO();
            Goal_edit_year edit_year = new Goal_edit_year();
            Boolean add = add_dao.goal_year_child_add(goal_year_id,goal_year_parent_id,user_id,goal_name);
            
            if(add){
                edit_year.select_child(goal_year_parent_id,request,response);//子目標を表示
            }else{
                try{
                    String url = "/UNION/goal/Goal_add_error.jsp";
                    response.sendRedirect(url);
                }catch(Exception l){
                    l.printStackTrace();
                }
            }
        }catch(Exception e){
            try{
                String url = "/UNION/goal/Goal_add_error.jsp";
                response.sendRedirect(url);
                e.printStackTrace();
            }catch(Exception k){
                k.printStackTrace();
            }
        }
    }
}
