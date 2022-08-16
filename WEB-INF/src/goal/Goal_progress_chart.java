package goal;

import bean.Goal;
import dao.Goal_listDAO;
import dao.Goal_select_week_DAO;
//import IT0090_JFreeChartDrawServlet;
import goal.JFreeChartFunctions;


import java.io.File;
import java.util.List;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.io.UnsupportedEncodingException;

import org.jfree.chart.ChartColor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYSeriesCollection;

@WebServlet(urlPatterns={"/goal/Goal_progress_chart"})
public class Goal_progress_chart extends HttpServlet {
    public void progress_chart(HttpServletRequest request, HttpServletResponse response){
    try{
        HttpSession session = request.getSession();//セッション情報を取得。
        String Goal_achievement_rate = (String)session.getAttribute("Goal_achievement_rate");//セッション情報から取得
        displayProduct(Goal_achievement_rate,request,response);
    }catch(Exception e){
        System.out.println("listの情報を取得できませんでした。");
        e.printStackTrace();
    }
    }
    /*String url = "/UNION/goal/goal_progress_week.jsp";
    try{
	    response.sendRedirect(url);
        return;
    }catch(IOException e){
        System.out.println("goal_progress_week.jspへリダイレクトに失敗しました。");
    }*/

    @SuppressWarnings("unchecked")
    public static void displayProduct(String week_date,HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("image/png");
        try{
            request.setCharacterEncoding("UTF-8");
        }catch(UnsupportedEncodingException e){
            System.out.println("UTF-8文字変換へ失敗しました");
            e.printStackTrace();
        }
        //response.setCharacterEncoding("UTF-8");
        //response.setContentType("image/png; charset=UTF-8");

        JFreeChartFunctions ChartFunctions  = new JFreeChartFunctions();
        //for(Goal date : week_date) {
        System.out.println("進捗率"+week_date);
        //}
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
