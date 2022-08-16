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
public class Goal_add_month_child extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException{
        HttpSession session = request.getSession();//セッション情報を取得。
        String goal_month_id = (String)session.getAttribute("goal_month_id");//セッション情報から取得
        String goal_month_parent_id = (String)session.getAttribute("goal_month_parent_id");//セッション情報から取得
        String user_id = (String)session.getAttribute("user_id");//セッション情報から取得
        goal_add(goal_month_id,goal_month_parent_id,user_id,request,response);

    }
    public void goal_add(String goal_month_id,String goal_month_parent_id,String user_id,HttpServletRequest request, HttpServletResponse response){//目標追加
        try{
            Goal_update_month_DAO add_dao = new Goal_update_month_DAO();
            Goal_select_month_DAO select_dao = new Goal_select_month_DAO();
            Goal_edit_month edit_month = new Goal_edit_month();
            System.out.println("goal_month_idきた-追加するよ");
            System.out.println(goal_month_id);
            Boolean add = add_dao.goal_month_child_add(goal_month_id,goal_month_parent_id,user_id);
            if(add){
                System.out.println("目標追加成功");
                edit_month.select_child(goal_month_parent_id,request,response);//子目標を表示
            }else{
                try{
                    System.out.println("月間子目標追加失敗");
                    String url = "/UNION/goal/Goal_add_error.jsp";
                    response.sendRedirect(url);
                }catch(Exception l){
                    System.out.println("例外処理発生");
                    System.out.println("月間子目標追加エラー画面リダイレクトに失敗しました。");
                    l.printStackTrace();
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            try{
                System.out.println("月間子目標追加月間子目標追加失敗");
                String url = "/UNION/goal/Goal_add_error.jsp";
                response.sendRedirect(url);
            }catch(Exception k){
                System.out.println("例外処理発生");
                System.out.println("月間子目標追加エラー画面リダイレクトに失敗しました。");
                k.printStackTrace();
            }
        }
    }
}
