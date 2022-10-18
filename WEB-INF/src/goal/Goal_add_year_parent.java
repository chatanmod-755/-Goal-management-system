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

@WebServlet(urlPatterns={"/goal/Goal_add_year_parent"})
public class Goal_add_year_parent extends HttpServlet {//親目標追加
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException{
        HttpSession session = request.getSession();//セッション情報を取得。
        request.setCharacterEncoding("UTF-8");
        String goal_name   = request.getParameter("goal_rename");//目標名取得
        String goal_year_id = (String)session.getAttribute("goal_year_id");//年間目標id取得
        String user_id = (String)session.getAttribute("user_id");//ユーザーid取得
        goal_add(goal_year_id,user_id,goal_name,response);
        Goal_edit_year edit_year  = new Goal_edit_year();
        edit_year.select(goal_year_id,request,response);//年間目標情報取得
    }

    public void goal_add(String goal_year_id,String user_id,String goal_name,HttpServletResponse response){//親目標登録
        Goal_update_year_DAO dao = new Goal_update_year_DAO();
        try{
            Boolean add = dao.goal_year_parent_add(goal_year_id,user_id,goal_name);
            
            if(add){
                System.out.println("親目標追加成功");
            }else{
                try{
                    String url = "/UNION/goal/Goal_add_error.jsp";
                    response.sendRedirect(url);//目標追加失敗ページへリダイレクト
                }catch(Exception k){
                    k.printStackTrace();
                }
            }
        }catch(Exception e){
            try{
                String url = "/UNION/goal/Goal_add_error.jsp";
                response.sendRedirect(url);//目標追加失敗ページへリダイレクト
                e.printStackTrace();
            }catch(Exception k){
                k.printStackTrace();
        }
        }
    }
}
