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
public class Goal_delete extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException{

        Goaltype_checkDAO check= new Goaltype_checkDAO();

        /*Map<String,String[]> parameterMap = request.getParameterMap();
        System.out.println(parameterMap);
         for(String key : parameterMap.keySet()) {
            System.out.println(key + ":");
            String[] vals = parameterMap.get(key);
            for(String s : vals) {
                System.out.println(s + " ");
            }
        }*/
        HttpSession session = request.getSession();//セッション情報を取得。
        String goal_id = request.getParameter("goal_id");//リクエストパラメータからユーザーidを取得。
        String user_id = (String)session.getAttribute("user_id");//セッション情報からuser_idを取得
        Goal_listDAO dao = new Goal_listDAO();//Goal_listDAOのインスタンスを生成

        try{
            String goal_type  = check.check_type(goal_id);//目標の種類を取得。
            if(goal_type.equals("1")){//週間目標削除
                try{
                    System.out.println("週間削除処理始まるよ");
                    Goal_deleteDAO_week del_week  = new Goal_deleteDAO_week();
                    if(del_week.delete(goal_id)){
                        List<Goal> list = dao.search(user_id);//Goal_listDAOのsearchメソッドへuser_idを渡す
                        session.setAttribute("list",list); //sessionへlistの情報をセット
                        String url = "/UNION/goal/goal_edit.jsp";
                        response.sendRedirect(url);//目標編集ページへリダイレクト。
                    }else{
                        String url = "/UNION/goal/delete-goal-error.jsp";
                        response.sendRedirect(url);//目標削除失敗ページへリダイレクト。
                    }
                }catch(Exception e){
                    System.out.println("週間削除に失敗しました");
                    e.printStackTrace();
                    String url = "/UNION/goal/delete-goal-error.jsp";
                    response.sendRedirect(url);//目標削除失敗ページへリダイレクト。
                }
            }else if(goal_type.equals("2")){//月間目標削除
                try{
                    System.out.println("月間削除処理始まるよ");
                    Goal_delete_month del_month  = new Goal_delete_month(); 
                    if(del_month.delete(goal_id)){
                        List<Goal> list = dao.search(user_id);//Goal_listDAOのsearchメソッドへuser_idを渡す
                        session.setAttribute("list",list); //sessionへlistの情報をセット
                        String url = "/UNION/goal/goal_edit.jsp";
                        response.sendRedirect(url);//目標編集ページへリダイレクト。
                    }else{
                        String url = "/UNION/goal/delete-goal-error.jsp";
                        response.sendRedirect(url);//目標削除失敗ページへリダイレクト。
                    }
                }catch(Exception e){
                    System.out.println("月間目標削除に失敗しました");
                    String url = "/UNION/goal/delete-goal-error.jsp";
                    response.sendRedirect(url);//目標削除失敗ページへリダイレクト。                    
                    e.printStackTrace();
                }
            }else if(goal_type.equals("3")){//年間目標削除
                try{
                    System.out.println("年間削除処理始まるよ");
                    Goal_delete_year del_year  = new Goal_delete_year();
                    if(del_year.delete(goal_id)){
                        List<Goal> list = dao.search(user_id);//Goal_listDAOのsearchメソッドへuser_idを渡す
                        session.setAttribute("list",list); //sessionへlistの情報をセット
                        String url = "/UNION/goal/goal_edit.jsp";
                        response.sendRedirect(url);//目標編集ページへリダイレクト。
                    }else{
                        String url = "/UNION/goal/delete-goal-error.jsp";
                        response.sendRedirect(url);//目標削除失敗ページへリダイレクト。
                    }
                }catch(Exception e){
                    System.out.println("年間削除に失敗しました");
                    e.printStackTrace();
                    String url = "/UNION/goal/goal_edit.jsp";
                    response.sendRedirect(url);//目標編集ページへリダイレクト。                    
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("cechk_typeの取得に失敗");
            String url = "/UNION/goal/delete-goal-error.jsp";
            response.sendRedirect(url);//目標削除失敗ページへリダイレクト。
        }

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



