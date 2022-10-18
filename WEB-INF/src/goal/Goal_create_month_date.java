package goal;
import bean.Goal;
import java.util.List;
import java.util.ArrayList;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Goal_create_month_date{
    public List<Goal> create_goal_date(long diff_date,String start_date) throws Exception{ //目標日付生成。
        List<Goal> list=new ArrayList<>();//ArrayList作成
            String year = start_date.substring(3, 4);//年の一桁取得
            String year_ten = start_date.substring(2, 3);//年の十桁取得
            String month = start_date.substring(5, 7);//月の値取得
            String day = start_date.substring(8, 10);//日の値取得
            Integer int_day = Integer.parseInt(day);
            Integer int_month = Integer.parseInt(month);
            Integer int_year = Integer.parseInt(year);
            Integer int_year_ten = Integer.parseInt(year_ten);

            for(int i = 0; i <= diff_date; i++){//差分日数を回す
                Goal g=new Goal();
                if(int_day <= 9){//日付が9以下月が変わることはない
                    String newday = start_date.substring(0, 9) + int_day+ start_date.substring(9 + 1);                    
                    start_date = newday.replace('-', '/');
                    DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
                    Date dt1=df.parse(start_date);
                    DateFormat format2=new SimpleDateFormat("EEEE");
                    String day_of_week=format2.format(dt1);
                    g.setday_of_week(day_of_week);//曜日設定
                    g.setgoal_progress_date(start_date);//目標を設定
                }else if(int_day <= 27){//日付が27以下月が変わることはない
                    String newday = start_date.substring(0, 8) + int_day+ start_date.substring(9 + 1);
                    start_date =  newday.replace('-', '/');
                    DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
                    Date dt1=df.parse(start_date);
                    DateFormat format2=new SimpleDateFormat("EEEE");
                    String day_of_week=format2.format(dt1);
                    g.setday_of_week(day_of_week);//曜日設定
                    g.setgoal_progress_date(start_date);//目標を設定
                }

                if(int_day >= 28 && int_month <= 8 && int_year <= 9){//月が一桁、年は変わらない
                    try {
                        String str2 = start_date.substring(0, 8) + int_day.toString()+ start_date.substring(9 + 1);//日付に1加算
                        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
                        df.setLenient(false); // ←これで厳密にチェックしてくれるようになる
                        start_date = str2.replace('-', '/');
                        df.format(df.parse(start_date)); // ←df.ｘparseでParseExceptionがThrowされる
                        Date dt1=df.parse(start_date);
                        DateFormat format2=new SimpleDateFormat("EEEE");
                        String day_of_week=format2.format(dt1);
                        g.setday_of_week(day_of_week);//曜日設定
                        g.setgoal_progress_date(start_date);//日付設定
                    }catch (Exception p) {
                        int_day = 1;//月が変わったので日付を初期化。
                        int_month +=1;
                        start_date = start_date.substring(0, 4) + "/0"+int_month+ "/01";//月を変更
                        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
                        Date dt1=df.parse(start_date);
                        DateFormat format2=new SimpleDateFormat("EEEE");
                        String day_of_week=format2.format(dt1);
                        g.setday_of_week(day_of_week);//曜日設定
                        g.setgoal_progress_date(start_date);//目標を設定
                    }
                }

                if(int_day >= 28 && int_month >= 9 && int_month <= 11 && int_year <= 9){//月が9月以上11月以前、年は変わらない
                    try {
                        String str2 = start_date.substring(0, 8) + int_day.toString()+ start_date.substring(9 + 1);//日付に1加算
                        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
                        df.setLenient(false); // ←これで厳密にチェックしてくれるようになる
                        start_date = str2.replace('-', '/');
                        df.format(df.parse(start_date)); // ←df.ｘparseでParseExceptionがThrowされる
                        Date dt1=df.parse(start_date);
                        DateFormat format2=new SimpleDateFormat("EEEE");
                        String day_of_week=format2.format(dt1);
                        g.setday_of_week(day_of_week);//曜日設定
                        g.setgoal_progress_date(start_date);
                    }catch (Exception p) {
                        int_day = 1;//月が変わったので日付を初期化。
                        int_month += 1;
                        //start_date = start_date.substring(0, 4) + "-"+int_month+ "-01";//月を変更
                        start_date = start_date.substring(0, 4) + "/"+int_month+ "/01";//月を変更
                        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
                        Date dt1=df.parse(start_date);
                        DateFormat format2=new SimpleDateFormat("EEEE");
                        String day_of_week=format2.format(dt1);
                        g.setday_of_week(day_of_week);//曜日設定
                        g.setgoal_progress_date(start_date);//目標を設定
                    }
                }

                if(int_day >= 28 && int_month == 12 && int_year <= 8){//年を変更する日付生成
                    try {
                        String str2 = start_date.substring(0, 8) + int_day.toString()+ start_date.substring(9 + 1);//日付に1加算
                        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
                        df.setLenient(false); // ←これで厳密にチェックしてくれるようになる
                        start_date = str2.replace('-', '/');
                        df.format(df.parse(start_date)); // ←df.ｘparseでParseExceptionがThrowされる
                        Date dt1=df.parse(start_date);
                        DateFormat format2=new SimpleDateFormat("EEEE");
                        String day_of_week=format2.format(dt1);
                        g.setday_of_week(day_of_week);//曜日設定
                        g.setgoal_progress_date(start_date);
                    } catch (Exception p) {
                        int_day = 1;//年が変わったので1を設定。
                        int_month = 1; //月を初期化
                        int_year += 1;//年をカウントアップ
                        start_date = start_date.substring(0, 3) + int_year +"/01/01";//年を変更
                        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
                        DateFormat format2=new SimpleDateFormat("EEEE");
                        Date dt1=df.parse(start_date);
                        String day_of_week=format2.format(dt1);
                        g.setday_of_week(day_of_week);//曜日設定
                        g.setgoal_progress_date(start_date);
                    }
                }

                if(int_day >= 28 && int_month == 12 && int_year == 9){//十桁の値が変更になる日付生成
                    try {
                        String str2 = start_date.substring(0, 8) + int_day.toString()+ start_date.substring(9 + 1);//日付に1加算
                        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
                        df.setLenient(false); // ←これで厳密にチェックしてくれるようになる
                        start_date = str2.replace('-', '/');
                        df.format(df.parse(start_date)); // ←df.ｘparseでParseExceptionがThrowされる
                        Date dt1=df.parse(start_date);
                        DateFormat format2=new SimpleDateFormat("EEEE");
                        String day_of_week=format2.format(dt1);
                        g.setday_of_week(day_of_week);//曜日設定
                        g.setgoal_progress_date(start_date);
                    } catch (Exception p) {
                        int_day = 1;//年が変わったので1を設定。
                        int_month = 1; //月を初期化
                        int_year  = 0;//一桁の年を0に初期化
                        int_year_ten += 1;//十桁の年をカウントアップ
                        start_date = start_date.substring(0, 2) + int_year_ten + int_year +"/01/01";//年を変更
                        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
                        Date dt1=df.parse(start_date);
                        DateFormat format2=new SimpleDateFormat("EEEE");
                        String day_of_week=format2.format(dt1);
                        g.setday_of_week(day_of_week);//曜日設定
                        g.setgoal_progress_date(start_date);
                    }
                }
                int_day += 1;
                g.setachievement_rate("0");//進捗率設定
                if(i == 0 || i % 7 == 0 || diff_date == i){//1週間ごとにlistに追加。
                    list.add(g);//リストに値を追加
                }
            }
    return list;
    }
}