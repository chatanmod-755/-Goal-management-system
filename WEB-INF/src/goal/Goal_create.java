package goal;

import bean.User;
import login.Login;
import dao.Goal_createDAO;
import dao.Goal_listDAO;
import bean.Goal;
import java.util.List;
import java.util.ArrayList;
import goal.Goal_create_month_date;
import goal.Goal_create_year_date;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.Date;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import org.apache.commons.lang3.StringUtils;
import java.util.List;
import java.time.temporal.Temporal;
import java.time.temporal.ChronoUnit;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@WebServlet(urlPatterns={"/goal/Goal_create"})
public class Goal_create extends HttpServlet {//目標作成
  public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException{
    Goal g=new Goal();
    long date=0;//差分変数の初期化
    String goal_type   = request.getParameter("goal_type"); //目標の種類を取得。
    String start_date = request.getParameter("start"); //開始する日付を取得。
    String end_date   = request.getParameter("end"); //終了する日付を取得。
    
    if(check_goal(goal_type,response) && check_start(start_date,response) && check_end(end_date,response)){   //目標・始まりの日付・終わりの日付記載チェック
        long diff_date = check_date(start_date,end_date,response);//始まの日付が終わりの日付より小さいかチェック
        if(diff_date > 0){
        HttpSession session = request.getSession();//セッション情報を取得。
        String user_id = (String)session.getAttribute("user_id");//セッション情報からユーザーidを取得
        try{
              Goal_listDAO list_dao = new Goal_listDAO();//Goal_listDAOのインスタンスを生成
              if(goal_type.equals("1")){//週間目標
                if(diff_date <= 7){
                  Goal_createDAO dao = new Goal_createDAO(); //Goal_createDAOのインスタンスを生成
                  Boolean create = dao.insert(user_id,goal_type,start_date,end_date); //目標を登録             
                  List<Goal> goal_progress_date = create_goal_date(diff_date,start_date);//目標日付生成
                  List<Goal> list = list_dao.search(user_id);//ユーザーが存在するか確認
                  session.setAttribute("list",list); //sessionへlistの情報をセット
                  Goal tmp = list.get(list.size() - 1);//目標idの末尾を取得
                  String goal_id = tmp.getgoal_id();
                  Boolean goal_progress_date_jsp = dao.insert_goal_progress_date(goal_id,goal_progress_date); //週間の進捗率表に目標情報を登録
                  create(create,request,response);              
                }else{
                  String url = "/UNION/goal/create-week-error.jsp";
                  try{
		                response.sendRedirect(url);//目標作成失敗ページへリダイレクト。
                  }catch(IOException e){
                    e.printStackTrace();
                  };
                }
              }

              if(goal_type.equals("2")){//月間目標
                if(diff_date >= 31){
                    Goal_createDAO dao = new Goal_createDAO(); //Goal_createDAOのインスタンスを生成
                    Boolean create = dao.insert(user_id,goal_type,start_date,end_date); //Goal_createDAOに,目標を登録する。                  
                    Goal_create_month_date month = new Goal_create_month_date();
                    List<Goal> goal_progress_date = month.create_goal_date(diff_date,start_date);//日付事の曜日設定
                    List<Goal> list = list_dao.search(user_id);//ユーザーが存在するか確認
                    session.setAttribute("list",list); //sessionへlistの情報をセット
                    Goal tmp = list.get(list.size() - 1);//目標idの末尾を取得
                    String goal_id = tmp.getgoal_id();
                    Boolean goal_progress_date_jsp = dao.insert_goal_progress_month_date(goal_id,goal_progress_date); //月間の進捗率表に目標情報を登録
                    create(create,request,response);
                }else if((diff_date == 30) && (start_date.substring(5, 7).equals("04") || start_date.substring(5, 7).equals("06") || start_date.substring(5, 7).equals("09") || start_date.substring(5, 7).equals("11"))){
                    Goal_createDAO dao = new Goal_createDAO(); //Goal_createDAOのインスタンスを生成
                    Boolean create = dao.insert(user_id,goal_type,start_date,end_date); //目標を登録                  
                    Goal_create_month_date month = new Goal_create_month_date();
                    List<Goal> goal_progress_date = month.create_goal_date(diff_date,start_date);//日付事の曜日設定
                    List<Goal> list = list_dao.search(user_id);//ユーザーが存在するか確認
                    session.setAttribute("list",list); //sessionへlistの情報をセット
                    Goal tmp = list.get(list.size() - 1);//目標idの末尾を取得
                    String goal_id = tmp.getgoal_id();
                    Boolean goal_progress_date_jsp = dao.insert_goal_progress_month_date(goal_id,goal_progress_date); //月間の進捗率表に目標情報を登録。
                    create(create,request,response);
                }else if((diff_date == 30) && (start_date.substring(5, 7).equals("03") || start_date.substring(5, 7).equals("05") || start_date.substring(5, 7).equals("08") || start_date.substring(5, 7).equals("10")) && (start_date.substring(8, 10).equals("31"))){
                    Goal_createDAO dao = new Goal_createDAO(); //Goal_createDAOのインスタンスを生成
                    Boolean create = dao.insert(user_id,goal_type,start_date,end_date); //目標を登録             
                    Goal_create_month_date month = new Goal_create_month_date();
                    List<Goal> goal_progress_date = month.create_goal_date(diff_date,start_date);//日付事の曜日設定
                    List<Goal> list = list_dao.search(user_id);//ユーザーが存在するか確認
                    session.setAttribute("list",list); //sessionへlistの情報をセット
                    Goal tmp = list.get(list.size() - 1);//目標idの末尾を取得
                    String goal_id = tmp.getgoal_id();
                    Boolean goal_progress_date_jsp = dao.insert_goal_progress_month_date(goal_id,goal_progress_date);//月間の進捗率表に目標情報を登録
                    create(create,request,response);
                }else if((diff_date >= 28) && (start_date.substring(5, 7).equals("02"))){ //最初・最後の日付が28日しかない為、目標を作成できるようにする
                    Goal_createDAO dao = new Goal_createDAO(); //Goal_createDAOのインスタンスを生成
                    Boolean create = dao.insert(user_id,goal_type,start_date,end_date); //目標を登録                  
                    Goal_create_month_date month = new Goal_create_month_date();
                    List<Goal> goal_progress_date = month.create_goal_date(diff_date,start_date);//日付事の曜日設定
                    List<Goal> list = list_dao.search(user_id);//ユーザーが存在するか確認
                    session.setAttribute("list",list); //sessionへlistの情報をセット
                    Goal tmp = list.get(list.size() - 1);//目標idの末尾を取得
                    String goal_id = tmp.getgoal_id();
                    Boolean goal_progress_date_jsp = dao.insert_goal_progress_month_date(goal_id,goal_progress_date);//月間の進捗率表に目標情報を登録
                    create(create,request,response);
                }else if( (diff_date >= 28) && (start_date.substring(5, 7).equals("01")) && (start_date.substring(8, 10).equals("31")) && (end_date.substring(5, 7).equals("02")) ){ //最初・最後の日付が28日しかない為、目標を作成できるようにする
                    Goal_createDAO dao = new Goal_createDAO(); //Goal_createDAOのインスタンスを生成
                    Boolean create = dao.insert(user_id,goal_type,start_date,end_date); //標を登録                  
                    Goal_create_month_date month = new Goal_create_month_date();
                    List<Goal> goal_progress_date = month.create_goal_date(diff_date,start_date);//日付事の曜日設定
                    List<Goal> list = list_dao.search(user_id);//ユーザーが存在するか確認
                    session.setAttribute("list",list); //sessionへlistの情報をセット
                    Goal tmp = list.get(list.size() - 1);//目標idの末尾を取得
                    String goal_id = tmp.getgoal_id();
                    Boolean goal_progress_date_jsp = dao.insert_goal_progress_month_date(goal_id,goal_progress_date);//月間の進捗率表に目標情報を登録
                    create(create,request,response);
                }else{
                    String url = "/UNION/goal/create-month-error.jsp";
                    try{
		                  response.sendRedirect(url);//目標作成失敗ページへリダイレクト。
                    }catch(IOException e){
                      e.printStackTrace();
                    };
                 }                 
              }

              if(goal_type.equals("3")){//年間目標
                if(diff_date >= 365){
                    Goal_createDAO dao = new Goal_createDAO(); //Goal_createDAOのインスタンスを生成
                    Boolean create = dao.insert(user_id,goal_type,start_date,end_date); //目標を登録
                    Goal_create_year_date year = new Goal_create_year_date();
                    List<Goal> goal_progress_date = year.create_goal_date(diff_date,start_date);//日付事の曜日設定
                    List<Goal> list = list_dao.search(user_id);//ユーザーが存在するか確認
                    session.setAttribute("list",list); //sessionへlistの情報をセット
                    Goal tmp = list.get(list.size() - 1);//目標idの末尾を取得
                    String goal_id = tmp.getgoal_id();
                    Boolean goal_progress_date_jsp = dao.insert_goal_progress_year_date(goal_id,goal_progress_date); //年間の進捗率表に目標情報を登録
                    create(create,request,response);
                }else{
                    String url = "/UNION/goal/create-year-error.jsp";
                    try{
		                  response.sendRedirect(url);//目標作成失敗ページへリダイレクト。
                    }catch(IOException e){
                      e.printStackTrace();
                    };
                 }
              }
          }catch(Exception e){
              e.printStackTrace();
          }
      }else{
        String url = "/UNION/goal/create-date-error.jsp";
        try{
		      response.sendRedirect(url);//日数差分失敗ページへリダイレクト
        }catch(IOException e){};
            e.printStackTrace();
      }
    }else{
        String url = "/UNION/goal/create-goal-error.jsp";
        try{
		        response.sendRedirect(url);//nullの場合は、check_start.jspへリダイレクト。
        }catch(IOException e){
            e.printStackTrace();
        }
    }
  }

  public void create(Boolean create,HttpServletRequest request, HttpServletResponse response){
    if(create){//DBに登録成功した場合は、ログイン画面へ遷移
      String url = "/UNION/goal/goal_edit.jsp";
      try{
		    response.sendRedirect(url);//目標編集ページへリダイレクト。
      }catch(IOException e){
        e.printStackTrace();
      };
    }else{
      String url = "/goal/create-date-error.jsp";
      try{
		    response.sendRedirect(url);//目標作成失敗ページへリダイレクト。
      }catch(IOException e){};
          e.printStackTrace();
      }
    }

    private Boolean check_goal (String goal_type,HttpServletResponse response){ //目標が入力されているか確認
      if(!type_check(goal_type)){ //nulcheckメソッドでnullか確認。
        return false;
      }else{
        return true;
      }
    }
    
    private Boolean check_start(String start_date,HttpServletResponse response){//最初の日付が入力されているか確認
      if(!Empty_check(start_date)){//nulcheckメソッドでnullか確認。
        return false;
      }else{
        return true;
      }
    }

    private Boolean check_end(String end_date,HttpServletResponse response){//最後の日付が入力されているか確認
      if(!Empty_check(end_date)){//nulcheckメソッドでnullか確認。
        return false;
      }else{
        return true;
      }
    }

    private Boolean Empty_check(String value){//nullの値かチェック
      if(StringUtils.isEmpty(value)){//nullの場合はfalseを返す。
        return false;
      }else{
        return true;
      }
    }

    private Boolean type_check(String value){//目標の種類を選択しているか確認
      if(value.equals("1") || value.equals("2") || value.equals("3")){//選択してない場合はfalseをかえす
        return true;
      }else{
        return false;
      }
    }

    public long check_date(String start_date,String end_date,HttpServletResponse response){//始まりと終わりの差分を表示
      LocalDate dBefore = LocalDate.parse(String.valueOf(start_date), DateTimeFormatter.ISO_LOCAL_DATE);
      LocalDate dAfter = LocalDate.parse(String.valueOf(end_date), DateTimeFormatter.ISO_LOCAL_DATE);
      long diff_date = dBefore.until(dAfter,ChronoUnit.DAYS);//日数の差分を取得
      return diff_date;
    }

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
              ("日数表示");
              (int_day);
              if(int_day <= 9){//日付が9以下月が変わることはない
                  String newday = start_date.substring(0, 9) + int_day+ start_date.substring(9 + 1);                    
                  start_date = newday.replace('-', '/');
                  DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
                  Date dt1=df.parse(start_date);
                  DateFormat format2=new SimpleDateFormat("EEEE");
                  String day_of_week=format2.format(dt1);
                  g.setday_of_week(day_of_week);//曜日設定
                  g.setgoal_progress_date(start_date);//日付を設定
              }else if(int_day <= 27){//日付が27以下月が変わることはない
                  String newday = start_date.substring(0, 8) + int_day+ start_date.substring(9 + 1);
                  start_date =  newday.replace('-', '/');
                  DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
                  Date dt1=df.parse(start_date);
                  DateFormat format2=new SimpleDateFormat("EEEE");
                  String day_of_week=format2.format(dt1);
                  g.setday_of_week(day_of_week);//曜日設定
                  g.setgoal_progress_date(start_date);//日付を設定
              }

              if(int_day >= 28 && int_month <= 8 && int_year <= 9){//月が一桁、年は変わらない
                  try {
                      String str2 = start_date.substring(0, 8) + int_day.toString()+ start_date.substring(9 + 1);//日付に1加算
                      DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
                      df.setLenient(false); // ←これで厳密にチェックしてくれるようになる
                      start_date = str2.replace('-', '/');
                      df.format(df.parse(start_date)); // ←df.ｘparseでParseExceptionがThrowされる
                      (start_date);
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
                      g.setgoal_progress_date(start_date);//日付を設定
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
                      g.setday_of_week(day_of_week);//曜日設定
                      g.setgoal_progress_date(start_date);
                  }catch (Exception p) {
                      int_day = 1;//月が変わったので日付を初期化。
                      int_month += 1;
                      DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
                      Date dt1=df.parse(start_date);
                      DateFormat format2=new SimpleDateFormat("EEEE");
                      String day_of_week=format2.format(dt1);
                      g.setday_of_week(day_of_week);//曜日設定
                      g.setgoal_progress_date(start_date);//日付を設定
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
                      g.setgoal_progress_date(start_date);//日付を設定
                  } catch (Exception p) {
                      int_day = 1;//年が変わったので1を設定。
                      int_month = 1; //月を初期化
                      int_year += 1;//年をカウントアップ
                      DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
                      DateFormat format2=new SimpleDateFormat("EEEE");
                      Date dt1=df.parse(start_date);
                      String day_of_week=format2.format(dt1);
                      g.setday_of_week(day_of_week);//曜日設定
                      g.setgoal_progress_date(start_date);//日付を設定
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
              list.add(g);//リストに値を追加
            }
    return list;//listを返す
    }
}
