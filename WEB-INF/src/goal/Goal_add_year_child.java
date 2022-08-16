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
public class Goal_add_year_child extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException{
        HttpSession session = request.getSession();//セッション情報を取得。
        String goal_year_id = (String)session.getAttribute("goal_year_id");//セッション情報から取得
        String goal_year_parent_id = (String)session.getAttribute("goal_year_parent_id");//セッション情報から取得
        String user_id = (String)session.getAttribute("user_id");//セッション情報から取得
        goal_add(goal_year_id,goal_year_parent_id,user_id,request,response);

    }
    public void goal_add(String goal_year_id,String goal_year_parent_id,String user_id,HttpServletRequest request, HttpServletResponse response){//目標追加
        try{
            Goal_update_year_DAO add_dao = new Goal_update_year_DAO();
            Goal_select_year_DAO select_dao = new Goal_select_year_DAO();
            Goal_edit_year edit_year = new Goal_edit_year();
            System.out.println("goal_year_idきた-追加するよ");
            System.out.println(goal_year_id);
            Boolean add = add_dao.goal_year_child_add(goal_year_id,goal_year_parent_id,user_id);
            if(add){
                System.out.println("目標追加成功");
                edit_year.select_child(goal_year_parent_id,request,response);//子目標を表示
            }else{
                try{
                    System.out.println("年間子目標追加失敗");
                    String url = "/UNION/goal/Goal_add_error.jsp";
                    response.sendRedirect(url);
                }catch(Exception l){
                    System.out.println("例外処理発生");
                    l.printStackTrace();
                }
            }
        }catch(Exception e){
            try{
                System.out.println("年間子目標追加失敗");
                String url = "/UNION/goal/Goal_add_error.jsp";
                response.sendRedirect(url);
                e.printStackTrace();
            }catch(Exception k){
                System.out.println("例外処理発生");
                System.out.println("年間子目標追加エラー画面リダイレクトに失敗しました。");
                k.printStackTrace();
            }
        }
    }
}
