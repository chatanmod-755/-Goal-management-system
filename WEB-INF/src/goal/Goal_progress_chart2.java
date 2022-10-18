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
public class Goal_progress_chart2 extends HttpServlet {//進捗表に必要な情報を取得
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        displayProduct(request,response);
        request.setCharacterEncoding("UTF-8");
    }

    @SuppressWarnings("unchecked")
    public static void displayProduct(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html; charset=Shift_JIS");

        try{
            request.setCharacterEncoding("UTF-8");//曜日が文字化けしないように設定
        }catch(UnsupportedEncodingException e){
            e.printStackTrace();
        }

        HttpSession session = request.getSession();
        List<Goal> progress_date = (List<Goal>)session.getAttribute("goal_progress_date");//目標期間取得
        ArrayList ar1 = new ArrayList();
        ArrayList ar2 = new ArrayList();
        ArrayList ar3 = new ArrayList();

        for (int i = 0; i < progress_date.size(); i++) {
            Goal date = progress_date.get(i);
            String tmp = String.valueOf(date.get_achievement_rate());//double型をStringに変換する
            String[] tmp2 = tmp.split("\\.");//小数点の前を取得。
            ar1.add(Integer.parseInt(tmp2[0]));//進捗率を代入
            ar2.add("進捗率");
            String date2 = date.getgoal_progress_date();
            ar3.add(date2.substring(5, 10)+"("+date.getday_of_week()+")");//曜日を代入
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
            ChartUtilities.writeChartAsJPEG(objSos, chart, 1400, 400);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
