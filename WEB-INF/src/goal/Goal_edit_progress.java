package goal;

import bean.Goal;
import dao.Goal_listDAO;
import dao.Goal_select_week_DAO;
import goal.JFreeChartFunctions;
import dao.Goal_update_week_DAO;


import java.sql.Date;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.io.UnsupportedEncodingException;
import java.io.PrintWriter;

/*import org.jfree.chart.ChartColor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYSeriesCollection;*/

@WebServlet(urlPatterns={"/goal/Goal_edit_progress"})
public class Goal_edit_progress extends HttpServlet {
    public void select_week_judge(String goal_id,HttpServletRequest request, HttpServletResponse response){//目標を表示する
        System.out.println("goal_idきた");
        System.out.println(goal_id);
        HttpSession session = request.getSession();//セッション情報を取得。
        Goal_select_week_DAO dao = new Goal_select_week_DAO();
        Goal_listDAO date = new Goal_listDAO();
        Goal_update_week_DAO update_dao = new Goal_update_week_DAO();
        long miliseconds = System.currentTimeMillis();
        Date day = new Date(miliseconds);
        try{
            System.out.println("goal_idきた--");
            System.out.println(goal_id);
            List<Goal> Goal_achievement_list = dao.select_achievement_list(goal_id);
            List<Goal> Goal_not_achievement_list = dao.select_not_achievement_list(goal_id);
            List<Goal> Goal_achievement_rate = dao.select_achievement_rate(goal_id);
            List<Goal> week_date = date.search_date(goal_id);
            session.setAttribute("Goal_achievement_list",Goal_achievement_list);//目標達成リスト設定
            session.setAttribute("Goal_not_achievement_list",Goal_not_achievement_list);//目標未達成リスト設定
            session.setAttribute("Goal_achievement_rate",Goal_achievement_rate);//目標達成率設定
            session.setAttribute("week_date",week_date); //目親標期間の情報をセット
            session.setAttribute("goal_id",goal_id); //目親標idの情報をセット
            
            Goal g = Goal_achievement_rate.get(0);
            String achievement_rate = g.get_achievement_rate();
            Boolean achievement_rate_update = update_dao.achievement_rate_update(goal_id,day,achievement_rate);//週間進捗表の進捗率をアップデート
            List<Goal> goal_progress_date_jsp = date.select_goal_progress_date(goal_id);
            System.out.println("進捗情報取得");
            session.setAttribute("goal_progress_date",goal_progress_date_jsp);//進捗表の日付と進捗率を設定。
            System.out.println(goal_progress_date_jsp);
            System.out.println("進捗情報表示終了");
            //displayProduct(Goal_achievement_rate,request,response);
            String url = "/UNION/goal/goal_progress_week.jsp";
            response.sendRedirect(url);
        }catch(Exception e){
            System.out.println("listの情報を取得できませんでした。");
            e.printStackTrace();
        }
    }

 /*   @SuppressWarnings("unchecked")
    public static void displayProduct(List<Goal> week_date,HttpServletRequest request, HttpServletResponse response) {
        //response.setContentType("image/png");
        try{
            request.setCharacterEncoding("UTF-8");
        }catch(UnsupportedEncodingException e){
            System.out.println("UTF-8文字変換へ失敗しました");
            e.printStackTrace();
        }
        //response.setCharacterEncoding("UTF-8");
        //response.setContentType("image/png; charset=UTF-8");

        JFreeChartFunctions ChartFunctions  = new JFreeChartFunctions();
        for(Goal date : week_date) {
        System.out.println("進捗率"+date.get_achievement_rate());
        }
        String [][] input = {
                {"120","","3月"},
                {"130","","4月"},
                {"140","","6月"},
                {"150","","7月"},
                {"160","","8月"},
        };

        ArrayList<ArrayList<String>> ar1 = new ArrayList<ArrayList<String>>();
        ArrayList<String> tmp = new ArrayList<String>();
        for(int i=0; i<input.length; i++) {
                tmp = new ArrayList<String>();
                tmp.add(input[i][0]);
                tmp.add(input[i][1]);
                tmp.add(input[i][2]);
                ar1.add(tmp);
        }
        
        ArrayList<ArrayList<String>> ar = ar1;
        ArrayList ar12 = new ArrayList();
        ArrayList ar2 = new ArrayList();
        ArrayList ar3 = new ArrayList();
        for(int i=0; i<ar.size(); i++) {
            ar12.add(Integer.parseInt(ar.get(i).get(0)));
            ar2.add(ar.get(i).get(1));
            ar3.add(ar.get(i).get(2));
            //System.out.println(ar12);
        }
        DefaultCategoryDataset ds_cat = ChartFunctions.createDS_LineChart(ar12,ar2,ar3);
        //JFreeChart chart=ChartFactory.createLineChart("お菓子の売上数", "月", "売れた数", ds_cat, PlotOrientation.VERTICAL, true, false, false);
        JFreeChart chart=ChartFactory.createLineChart("Goal_progress","sunday","", ds_cat, PlotOrientation.VERTICAL, true, false, false);
        //### ⑦PNG画像生成 ###
        File file = new File("test.jpeg");
        try{
            ServletOutputStream objSos=response.getOutputStream();
            ChartUtilities.writeChartAsJPEG(objSos, chart, 600, 400);
            //ChartUtilities.saveChartAsJPEG(file, chart, 400, 300);
        }catch(IOException e){
            System.out.println("データ作成失敗");
        }
    }
            /*public void select_child(String goal_year_parent_id,HttpServletRequest request, HttpServletResponse response){//目標を表示する
        HttpSession session = request.getSession();//セッション情報を取得。
        Goal_select_year_DAO select_dao = new Goal_select_year_DAO();
        try{
            List<Goal> year_child_list = select_dao.select_goal_child(goal_year_parent_id);//子目標取得
            session.setAttribute("year_child_list",year_child_list); //sessionへ子目標の情報をセット
            String url = "/UNION/goal/goal_edit_year_child.jsp";
            response.sendRedirect(url);
        }catch(Exception e){
            System.out.println("listの情報を取得できませんでした。");
            e.printStackTrace();
        }
    }*/
}
