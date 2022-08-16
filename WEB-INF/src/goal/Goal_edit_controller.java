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
public class Goal_edit_controller extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException{

        Goaltype_checkDAO check= new Goaltype_checkDAO();

        HttpSession session = request.getSession();//セッション情報を取得。
        String goal_id = request.getParameter("goal_id");//リクエストパラメータから目標idを取得。
        System.out.println("Goal_edit_controllerきた。");
        System.out.println("Goal_id表示");
        System.out.println(goal_id);
        System.out.println("Goal_id表示終了");

        try{
            String goal_type  = check.check_type(goal_id);//目標の種類を取得。
            if(goal_type.equals("1")){//週間目標編集
                try{
                    System.out.println("週間目標編集へgoal_idを渡すよ");
                    System.out.println("Goal_id表示");
                    System.out.println(goal_id);
                    Goal_edit_week edit_week  = new Goal_edit_week();
                    edit_week.select(goal_id,request,response);
                }catch(Exception e){
                    System.out.println("週間目標編集へgoal_id渡せなかった");
                    e.printStackTrace();
                }
            }else if(goal_type.equals("2")){//月間目標編集
                try{
                    System.out.println("月間目標編集へgoal_idを渡すよ");
                    Goal_edit_month edit_month  = new Goal_edit_month();
                    edit_month.select(goal_id,request,response);
                }catch(Exception e){
                    System.out.println("月間目標編集へgoal_id渡せなかった");
                    e.printStackTrace();
                }
            }else if(goal_type.equals("3")){//年間目標編集
                try{
                    System.out.println("年間目標編集へgoal_idを渡すよ");
                    Goal_edit_year edit_year  = new Goal_edit_year();
                    edit_year.select(goal_id,request,response);
                }catch(Exception e){
                    System.out.println("年間目標編集へgoal_id渡せなかった");
                    e.printStackTrace();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("cechk_typeの取得に失敗");
        }

        System.out.println("goal_id");
        System.out.println(goal_id);
        
        String user_id = (String)session.getAttribute("user_id");//セッション情報からuser_idを取得
        Goal_listDAO dao = new Goal_listDAO();//Goal_listDAOのインスタンスを生成

        try{
            List<Goal> list = dao.search(user_id);//Goal_listDAOのsearchメソッドへuser_idを渡す
            session.setAttribute("list",list); //sessionへlistの情報をセット
            String url = "/UNION/goal/goal_edit.jsp";
            response.sendRedirect(url);//目標編集ページへリダイレクト。
        }catch(Exception e){
            System.out.println("listの情報を取得できませんでした。");
        }
    }
}



