package goal;

import bean.Goal;
import dao.Goal_listDAO;
import dao.Goal_select_week_DAO;
import goal.JFreeChartFunctions;


import java.io.File;
import java.awt.Font;
import org.jfree.chart.title.LegendTitle;
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
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;


@WebServlet(urlPatterns={"/goal/Goal_progress_chart2"})
public class Goal_progress_chart2 extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        displayProduct(request,response);
        request.setCharacterEncoding("UTF-8");
    }

    @SuppressWarnings("unchecked")
    public static void displayProduct(HttpServletRequest request, HttpServletResponse response) {
        //response.setContentType("image/png");text/html
        
        response.setContentType("text/html; charset=Shift_JIS");
        //response.setCharacterEncoding("UTF-8");
        try{
            request.setCharacterEncoding("UTF-8");
        }catch(UnsupportedEncodingException e){
            System.out.println("UTF-8文字変換へ失敗しました");
            e.printStackTrace();
        }
        HttpSession session = request.getSession();
        //ArrayList<ArrayList<String>> ar = (ArrayList<ArrayList<String>>) request.getSession().getAttribute("chart1");
        List<Goal> progress_date = (List<Goal>)session.getAttribute("goal_progress_date");//セッション情報から取得
        System.out.println("progress_date表示");
        System.out.println(progress_date);
        System.out.println("progress_date表示終了");
        /*for (int i = 0; i < progress_date.size(); i++) {
            Goal date = progress_date.get(i);
            System.out.println("progress_date表示----------");
            System.out.println(date.getgoal_progress_date());
            System.out.println("progress_date表示終了--------");
        }*/
        ArrayList ar1 = new ArrayList();
        ArrayList ar2 = new ArrayList();
        ArrayList ar3 = new ArrayList();
        //for(int i=0; i<ar.size(); i++) {
        //for (int i = 0; i < progress_date.size(); i++) {
        for (int i = 0; i < progress_date.size(); i++) {
            //Goal date = progress_date.get(i);
            Goal date = progress_date.get(i);
            System.out.println(i+"回目ーー");
            System.out.println(date.getday_of_week());
            System.out.println("progress_date.get(i)表示");
            System.out.println(date);
            System.out.println("progress_date.get(i)表示終了");
            System.out.println("ar.get(i).get(0)表示!!!");
            //System.out.println(Integer.parseInt(ar.get(i).get(0)));
            System.out.println("ar.get(i).get(0)表示終わり");
            //ar1.add(Integer.parseInt(ar.get(i).get(0)));
            //ar3.add(ar.get(i).get(2));
            System.out.println("Integer.parseInt(date.get_achievement_rate())表示");
            System.out.println(date.get_achievement_rate());
            String tmp = String.valueOf(date.get_achievement_rate());//double型をStringに変換する
            System.out.println("tmp");
            System.out.println(tmp);
            String[] tmp2 = tmp.split("\\.");//小数点の前を取得。
            System.out.println("tmp2表示");
            System.out.println(tmp2[0]);
            ar1.add(Integer.parseInt(tmp2[0]));
            System.out.println("Integer.parseInt(date.get_achievement_rate())表示終了");
            ar2.add("進捗率");
            System.out.println("date.getgoal_progress_date()表示");
            String date2 = date.getgoal_progress_date();
            System.out.println(date.getday_of_week());
            ar3.add(date2.substring(5, 10)+"("+date.getday_of_week()+")");
            System.out.println("date.getgoal_progress_date()表示終了");

            //System.out.println(ar12);
        }
        ChartFactory.setChartTheme(StandardChartTheme.createLegacyTheme());

        JFreeChartFunctions ChartFunctions  = new JFreeChartFunctions();
        DefaultCategoryDataset ds_cat = ChartFunctions.createDS_LineChart(ar1,ar2,ar3);
        JFreeChart chart=ChartFactory.createLineChart("","","",ds_cat, PlotOrientation.VERTICAL, true, false, false);

        
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setOutlineVisible(false);
        NumberAxis numberAxis = (NumberAxis)plot.getRangeAxis();
        numberAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        numberAxis.setLowerBound(0);
        numberAxis.setUpperBound(100);

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
