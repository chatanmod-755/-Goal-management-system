package goal;
import goal.JFreeChartFunctions;
import java.io.IOException;
import java.util.ArrayList;
 
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.jfree.chart.ChartColor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYSeriesCollection;
 
@WebServlet("/goal/JFreeChartTest")
public class IT0090_JFreeChartDrawServlet extends HttpServlet {
        JFreeChartFunctions jfc = new JFreeChartFunctions();
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                String mode = request.getParameter("mode");
                if(mode.equals("1")) {
                        drawPieChart(request,response);
                }else if(mode.equals("2")) {
                        drawLineGraph(request,response);
                }
        }
        //##(1) Pie Chart/円グラフ //
        @SuppressWarnings("unchecked")
        protected void drawPieChart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                response.setContentType("image/png");
 
                //### ③元データの取得 ###
                ArrayList<ArrayList> ar1 = (ArrayList<ArrayList>) request.getSession().getAttribute("chart1");
 
                //### ④データセットの作成 ###
                DefaultPieDataset ds_pie = jfc.createDS_PieChart(ar1);
 
                //### ⑥チャートの作成 ###
                JFreeChart chart=ChartFactory.createPieChart3D("好きなフルーツは？", ds_pie,true,false,false);
                chart.setBackgroundPaint(ChartColor.WHITE);
 
                //### ⑦PNG画像生成 ###
                ServletOutputStream objSos=response.getOutputStream();
                ImageIO.write(chart.createBufferedImage(600, 400), "png", objSos);
        }
        //##(2) Line Chart/折れ線グラフ //
        @SuppressWarnings("unchecked")
        protected void drawLineGraph(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                response.setContentType("image/png");
 
                //### ③元データの取得 ###
                ArrayList<ArrayList> ar = (ArrayList<ArrayList>)request.getSession().getAttribute("chart1");
                
                //### ④データセットの作成 ###
                ArrayList ar1 = new ArrayList();
                ArrayList ar2 = new ArrayList();
                ArrayList ar3 = new ArrayList();
                for(int i=0; i<ar.size(); i++) {
                        //ar1.add(Integer.parseInt(ar.get(i).get(0)));
                        ar1.add(ar.get(i).get(0));
                        ar2.add(ar.get(i).get(1));
                        ar3.add(ar.get(i).get(2));
                }
                DefaultCategoryDataset ds_cat = jfc.createDS_LineChart(ar1,ar2,ar3);
                //### ⑥チャートの作成 ###
                //JFreeChart chart=ChartFactory.createLineChart("お菓子の売上数", "月", "売れた数", ds_cat, PlotOrientation.VERTICAL, true, false, false);
                JFreeChart chart=ChartFactory.createLineChart("sunday", ds_cat, PlotOrientation.VERTICAL, true, false, false);
 
                //### ⑦PNG画像生成 ###
                ServletOutputStream objSos=response.getOutputStream();
                ChartUtilities.writeChartAsJPEG(objSos, chart, 600, 400);
        }
}