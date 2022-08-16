package goal;

import java.util.ArrayList;
 
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
 
public class JFreeChartFunctions {
 
        /*(1) Create Dataset for "createPieChart3D"
        public DefaultPieDataset createDS_PieChart(ArrayList<ArrayList> ar1) {
 
                //### ⑤データセットの作成処理 ###
                DefaultPieDataset defpie = new DefaultPieDataset();
                for(int i=0; i<ar1.size(); i++) {
                        //defpie.setValue(ar1.get(i).get(0),Integer.parseInt(ar1.get(i).get(1)));
                        defpie.setValue(ar1.get(i).get(0),ar1.get(i).get(1));
                }
                return defpie;
        }*/
        //(2) Create DataSet for "createLineChart"
        public DefaultCategoryDataset createDS_LineChart(ArrayList key1,ArrayList key2, ArrayList key3) {
        //public DefaultCategoryDataset createDS_LineChart(Integer key1,String key2, String key3) {
                //### ⑤データセットの作成処理 ###
                DefaultCategoryDataset defcat = new DefaultCategoryDataset();
                //for(int i=0; i<5; i++) {
                for(int i=0; i<key1.size(); i++) {        
                        //System.out.println(key1.get(i));
                        //defcat.addValue(key1.get(i), key2.get(i), key3.get(i));
                        defcat.addValue((int)key1.get(i),String.valueOf(key2.get(i)),String.valueOf(key3.get(i)));
                }
                return defcat;
        }
}