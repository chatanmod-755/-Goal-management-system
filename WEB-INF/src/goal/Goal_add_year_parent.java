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
public class Goal_add_year_parent extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException{
        HttpSession session = request.getSession();//セッション情報を取得。
        String goal_year_id = (String)session.getAttribute("goal_year_id");//セッション情報から取得
        String user_id = (String)session.getAttribute("user_id");//セッション情報から取得
        goal_add(goal_year_id,user_id,response);
        Goal_edit_year edit_year  = new Goal_edit_year();
        edit_year.select(goal_year_id,request,response);
    }
    public void goal_add(String goal_year_id,String user_id,HttpServletResponse response){//目標追加
        Goal_update_year_DAO dao = new Goal_update_year_DAO();
        try{
            System.out.println("goal_year_idきた-追加するよ");
            System.out.println(goal_year_id);
            Boolean add = dao.goal_year_parent_add(goal_year_id,user_id);
            if(add){
                System.out.println("目標追加成功");
            }else{
                try{
                    System.out.println("目標追加失敗");
                    String url = "/UNION/goal/Goal_add_error.jsp";
                    response.sendRedirect(url);
                }catch(Exception k){
                    System.out.println("例外処理発生");
                    System.out.println("年間親目標追加エラー画面リダイレクトに失敗しました。");
                    k.printStackTrace();
                }
            }
        }catch(Exception e){
            try{
                System.out.println("目標追加失敗");
                String url = "/UNION/goal/Goal_add_error.jsp";
                response.sendRedirect(url);
                e.printStackTrace();
            }catch(Exception k){
                System.out.println("例外処理発生");
                System.out.println("月間親目標追加エラー画面リダイレクトに失敗しました。");
                k.printStackTrace();
        }
        }
    }
}