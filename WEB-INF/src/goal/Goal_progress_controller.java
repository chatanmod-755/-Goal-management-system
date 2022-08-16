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
public class Goal_progress_controller extends HttpServlet {
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
                    System.out.println("週間目標進捗へgoal_idを渡すよ");
                    System.out.println("Goal_id表示");
                    System.out.println(goal_id);
                    Goal_edit_progress edit_week  = new Goal_edit_progress();
                    edit_week.select_week_judge(goal_id,request,response);
                }catch(Exception e){
                    System.out.println("週間目標進捗へgoal_id渡せなかった");
                    e.printStackTrace();
                }
            }else if(goal_type.equals("2")){//月間目標編集
                try{
                    System.out.println("月間目標進捗へgoal_idを渡すよ");
                    Goal_edit_month_progress edit_month  = new Goal_edit_month_progress();
                    edit_month.select_month_judge(goal_id,request,response);
                }catch(Exception e){
                    System.out.println("月間目標進捗へgoal_id渡せなかった");
                    e.printStackTrace();
                }
            }else if(goal_type.equals("3")){//年間目標編集
                try{
                    System.out.println("年間目標進捗へgoal_idを渡すよ");
                    Goal_edit_year_progress edit_year  = new Goal_edit_year_progress();
                    edit_year.select_year_judge(goal_id,request,response);
                }catch(Exception e){
                    System.out.println("年間目標進捗へgoal_id渡せなかった");
                    e.printStackTrace();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("cechk_typeの取得に失敗");
        }

        String user_id = (String)session.getAttribute("user_id");//セッション情報からuser_idを取得
        Goal_listDAO dao = new Goal_listDAO();//Goal_listDAOのインスタンスを生成

        if(goal_id != null){//目標選択の有無確認
            try{
                List<Goal> list = dao.search(user_id);//Goal_listDAOのsearchメソッドへuser_idを渡す
                session.setAttribute("list",list); //sessionへlistの情報をセット
                String url = "/UNION/goal/goal_edit.jsp";
                response.sendRedirect(url);
            }catch(Exception e){
                System.out.println("listの情報を取得できませんでした。");
            }
        }else{
            String url = "/UNION/goal/goal_progress_error.jsp";
            response.sendRedirect(url);
        }
    }
}



