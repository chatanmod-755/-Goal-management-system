package dao;

import bean.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import bean.Goal;
import java.util.List;
import java.util.ArrayList;

public class Goal_createDAO extends DAO{
    public boolean insert(String user_id,String goal_type,String start_date,String end_date){ //目標情報を登録
        //int rs = 0;
        Connection con = getConnection(); //DBに接続
        PreparedStatement st;
        PreparedStatement st2;
        Goal g=new Goal();
        try{
            st=con.prepareStatement("select MAX(goal_id)+1 from goal_create");//目標表にある目標idの最大値+1を取得
            ResultSet rs2 = st.executeQuery();

            if(rs2.next()){
                g.setgoal_id(rs2.getString("MAX(goal_id)+1"));
            }

            if(g.getgoal_id() != null){//目標idが取得できてなければ目標idとして1を追加する。
                st=con.prepareStatement("select MAX(goal_id)+1 from goal_create");
                g.setgoal_id(rs2.getString("MAX(goal_id)+1"));
            }else{
                g.setgoal_id("1");
            }

            st.close();
            con.setAutoCommit(false);
            st2=con.prepareStatement("insert into goal_create values (?, ?, ?, ?, ?)");
            st2.setString(1,g.getgoal_id()); //goal_idを設定。
            st2.setString(2,user_id); //ユーザーidを設定。
            st2.setString(3,goal_type); //目標の種類を設定。
            st2.setString(4,start_date); //始まりの日付を設定。
            st2.setString(5,end_date); //終わりの日付を設定。
            int rs = st2.executeUpdate(); //sql文実施
            st2.close();
            
            if (rs == 0){//sql文が失敗した時
                con.rollback();
                con.setAutoCommit(true);
                con.close();
                return false;
            }

            con.commit();
            con.setAutoCommit(true);
            con.close();
        }catch (SQLException e){
            e.printStackTrace();
        };
        return true;
    }

    public boolean insert_goal_progress_date(String goal_id,List<Goal> goal_progress_date){//週間の進捗率表に目標情報を登録
        Connection con = getConnection(); //DBに接続
        PreparedStatement st;
        Goal g=new Goal();
        try{
            for (int i = 0; i < goal_progress_date.size(); i++) {//設定した日付分回す
                Goal date = goal_progress_date.get(i);//listから値を取得
                con.setAutoCommit(false);
                st=con.prepareStatement("insert into achievement_rate_week values (?, ?, ?, ?)"); //週間進捗率DBにachievement_rateDBにgoal_id,進捗表の日付を入れる
                st.setString(1,goal_id); //目標idを設定。
                st.setString(2,date.getgoal_progress_date()); //日付を設定。
                st.setString(3,date.getday_of_week()); //曜日を設定。
                st.setString(4,date.get_achievement_rate()); //目標の進捗率を設定。
                int rs = st.executeUpdate(); //sql文実施

                if (rs == 0){//sql文が失敗した時
                    con.rollback();
                    con.setAutoCommit(true);
                    con.close();
                    return false;
                }

                if(i+1 == goal_progress_date.size()){//代入する情報がなくなればDBの接続を終了する
                    st.close();
                    con.commit();
                    con.setAutoCommit(true);
                    con.close();
                }

            }
        }catch (SQLException e){
            e.printStackTrace();
        };
        return true;
    }

    public boolean insert_goal_progress_month_date(String goal_id,List<Goal> goal_progress_date){//月間の進捗率表に目標情報を登録
        Connection con = getConnection(); //DBに接続
        PreparedStatement st;
        Goal g=new Goal();
        try{
            for (int i = 0; i < goal_progress_date.size(); i++) {//設定した日付分回す
                Goal date = goal_progress_date.get(i);//listから値を取得
                con.setAutoCommit(false);
                st=con.prepareStatement("insert into achievement_rate_month values (?, ?, ?, ?)"); 
                st.setString(1,goal_id); //目標idを設定。
                st.setString(2,date.getgoal_progress_date()); //日付を設定。
                st.setString(3,date.getday_of_week()); //曜日を設定。
                st.setString(4,date.get_achievement_rate()); //進捗率を設定。
                int rs = st.executeUpdate();//sql文実施

                if (rs == 0){//sql文が失敗した時
                    con.rollback();
                    con.setAutoCommit(true);
                    con.close();
                    return false;
                }

                if(i+1 == goal_progress_date.size()){//代入する情報がなくなればDBの接続を終了する
                    st.close();
                    con.commit();
                    con.setAutoCommit(true);
                    con.close();
                }

            }
        }catch (SQLException e){
            e.printStackTrace();
        };
        return true;//sql文が成功した時
    }

    public boolean insert_goal_progress_year_date(String goal_id,List<Goal> goal_progress_date){//年間の進捗率表に目標情報を登録
        Connection con = getConnection(); //DBに接続
        PreparedStatement st;
        Goal g=new Goal();
        try{
            for (int i = 0; i < goal_progress_date.size(); i++) {//設定した日付分回す
                Goal date = goal_progress_date.get(i);//listから値を取得
                con.setAutoCommit(false);
                st=con.prepareStatement("insert into achievement_rate_year values (?, ?, ?, ?)");
                st.setString(1,goal_id); //目標idを設定。
                st.setString(2,date.getgoal_progress_date()); //日付を設定。
                st.setString(3,date.getday_of_week()); //曜日を設定。
                st.setString(4,date.get_achievement_rate()); //進捗率を設定。
                int rs = st.executeUpdate();//sql文実施

                if (rs == 0){//sql文が失敗した時
                    con.rollback();
                    con.setAutoCommit(true);
                    con.close();
                    return false;
                }

                if(i+1 == goal_progress_date.size()){//代入する情報がなくなればDBの接続を終了する
                    st.close();
                    con.commit();
                    con.setAutoCommit(true);
                    con.close();
                }

            }
        }catch (SQLException e){
            e.printStackTrace();
        };
        return true;
    }
}