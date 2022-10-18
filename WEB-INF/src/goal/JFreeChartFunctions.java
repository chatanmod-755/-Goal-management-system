package goal;

import java.util.ArrayList;
 
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
 
public class JFreeChartFunctions {
        public DefaultCategoryDataset createDS_LineChart(ArrayList key1,ArrayList key2, ArrayList key3) {
                DefaultCategoryDataset defcat = new DefaultCategoryDataset();
                for(int i=0; i<key1.size(); i++) {        
                        defcat.addValue((int)key1.get(i),String.valueOf(key2.get(i)),String.valueOf(key3.get(i)));
                }
                return defcat;
        }
}