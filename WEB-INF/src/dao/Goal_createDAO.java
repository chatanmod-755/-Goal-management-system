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
    public boolean insert(String user_id,String goal_type,String start_date,String end_date){ //ユーザーid、目標の種類、始まりの日付、終わりの日付
        //int rs = 0;
        Connection con = getConnection(); //DBに接続
        PreparedStatement st;
        PreparedStatement st2;
        Goal g=new Goal();

        try{
            st=con.prepareStatement("select MAX(goal_id)+1 from goal_create");
            ResultSet rs2 = st.executeQuery();
            System.out.println("rs2表示");
            System.out.println(rs2);
            if(rs2.next()){
                System.out.println("goal_idを取得");
                g.setgoal_id(rs2.getString("MAX(goal_id)+1"));
            }

            if(g.getgoal_id() != null){//goal_idが取得できてなければgoal_idとして1を追加する。
                st=con.prepareStatement("select MAX(goal_id)+1 from goal_create");
                g.setgoal_id(rs2.getString("MAX(goal_id)+1"));
            }else{
                g.setgoal_id("1");
            }

            System.out.println("goal_id表示");
            System.out.println(g.getgoal_id());
            st.close();
            con.setAutoCommit(false);//自動コミットモードの無効化
            st2=con.prepareStatement("insert into goal_create values (?, ?, ?, ?, ?)"); //goal_createDBにユーザーid、目標の種類、始まりの日付、終わりの日付を登録
            st2.setString(1,g.getgoal_id()); //goal_idを設定。
            st2.setString(2,user_id); //ユーザーidを設定。
            st2.setString(3,goal_type); //目標の種類を設定。
            st2.setString(4,start_date); //始まりの日付を設定。
            st2.setString(5,end_date); //終わりの日付を設定。
            int rs = st2.executeUpdate(); //更新行数を返却
            System.out.println("sql実行");
            st2.close();
            

            if (rs == 0){//sql文が失敗した時
                con.rollback();
                con.setAutoCommit(true);
                con.close();//コネクションを閉じる
                return false;
            }
            con.commit();
            con.setAutoCommit(true);
            con.close();
        }catch (SQLException e){
            System.out.println("クエリ文が失敗しました。");
            e.printStackTrace();
        };
        return true;//sql文が成功した時
    }

    public boolean insert_goal_progress_date(String goal_id,List<Goal> goal_progress_date){//goal_id,進捗表の日付を保存する処理
        Connection con = getConnection(); //DBに接続
        PreparedStatement st;
        Goal g=new Goal();

        try{
            for (int i = 0; i < goal_progress_date.size(); i++) {//設定した日付分回す
                Goal date = goal_progress_date.get(i);//listから値を取得
                con.setAutoCommit(false);//自動コミットモードの無効化
                st=con.prepareStatement("insert into achievement_rate_week values (?, ?, ?, ?)"); //週間進捗率DBにachievement_rateDBにgoal_id,進捗表の日付を入れる
                st.setString(1,goal_id); //goal_idを設定。
                st.setString(2,date.getgoal_progress_date()); //進捗表の日付を設定。
                st.setString(3,date.getday_of_week()); //進捗表の曜日を設定。
                st.setString(4,date.get_achievement_rate()); //目標の進捗率を設定。
                int rs = st.executeUpdate(); //更新行数を返却
                System.out.println("sql実行");
                System.out.println(date.getday_of_week());
                System.out.println(date.getgoal_progress_date());

                if (rs == 0){//sql文が失敗した時
                    con.rollback();
                    con.setAutoCommit(true);
                    con.close();//コネクションを閉じる
                    System.out.println("SQL文失敗した");
                    return false;
                }

                if(i+1 == goal_progress_date.size()){//これ以上DBに代入する日付なければDBの接続を終了する
                    st.close();
                    con.commit();
                    con.setAutoCommit(true);
                    con.close();
                }

            }
        }catch (SQLException e){
            System.out.println("クエリ文が失敗しました。");
            e.printStackTrace();
        };
        return true;//sql文が成功した時
    }

    public boolean insert_goal_progress_month_date(String goal_id,List<Goal> goal_progress_date){//goal_id,進捗表の日付を保存する処理
        Connection con = getConnection(); //DBに接続
        PreparedStatement st;
        Goal g=new Goal();

        try{
            for (int i = 0; i < goal_progress_date.size(); i++) {//設定した日付分回す
                Goal date = goal_progress_date.get(i);//listから値を取得
                con.setAutoCommit(false);//自動コミットモードの無効化
                st=con.prepareStatement("insert into achievement_rate_month values (?, ?, ?, ?)"); //週間進捗率DBにachievement_rateDBにgoal_id,進捗表の日付を入れる
                st.setString(1,goal_id); //goal_idを設定。
                st.setString(2,date.getgoal_progress_date()); //進捗表の日付を設定。
                st.setString(3,date.getday_of_week()); //進捗表の曜日を設定。
                st.setString(4,date.get_achievement_rate()); //目標の進捗率を設定。
                int rs = st.executeUpdate(); //更新行数を返却
                System.out.println("sql実行");
                System.out.println(date.getday_of_week());
                System.out.println(date.getgoal_progress_date());

                if (rs == 0){//sql文が失敗した時
                    con.rollback();
                    con.setAutoCommit(true);
                    con.close();//コネクションを閉じる
                    System.out.println("SQL文失敗した");
                    return false;
                }

                if(i+1 == goal_progress_date.size()){//これ以上DBに代入する日付なければDBの接続を終了する
                    st.close();
                    con.commit();
                    con.setAutoCommit(true);
                    con.close();
                }

            }
        }catch (SQLException e){
            System.out.println("クエリ文が失敗しました。");
            e.printStackTrace();
        };
        return true;//sql文が成功した時
    }

    public boolean insert_goal_progress_year_date(String goal_id,List<Goal> goal_progress_date){//goal_id,進捗表の日付を保存する処理
        Connection con = getConnection(); //DBに接続
        PreparedStatement st;
        Goal g=new Goal();

        try{
            for (int i = 0; i < goal_progress_date.size(); i++) {//設定した日付分回す
                Goal date = goal_progress_date.get(i);//listから値を取得
                con.setAutoCommit(false);//自動コミットモードの無効化
                st=con.prepareStatement("insert into achievement_rate_year values (?, ?, ?, ?)"); //週間進捗率DBにachievement_rateDBにgoal_id,進捗表の日付を入れる
                st.setString(1,goal_id); //goal_idを設定。
                st.setString(2,date.getgoal_progress_date()); //進捗表の日付を設定。
                st.setString(3,date.getday_of_week()); //進捗表の曜日を設定。
                st.setString(4,date.get_achievement_rate()); //目標の進捗率を設定。
                int rs = st.executeUpdate(); //更新行数を返却
                System.out.println("sql実行");
                System.out.println(date.getday_of_week());
                System.out.println(date.getgoal_progress_date());

                if (rs == 0){//sql文が失敗した時
                    con.rollback();
                    con.setAutoCommit(true);
                    con.close();//コネクションを閉じる
                    System.out.println("SQL文失敗した");
                    return false;
                }

                if(i+1 == goal_progress_date.size()){//これ以上DBに代入する日付なければDBの接続を終了する
                    st.close();
                    con.commit();
                    con.setAutoCommit(true);
                    con.close();
                }

            }
        }catch (SQLException e){
            System.out.println("クエリ文が失敗しました。");
            e.printStackTrace();
        };
        return true;//sql文が成功した時
    }
}
    /*public List<Goal> insert_goal_progress_date(String goal_id,List<Goal> goal_progress_date){//goal_id,進捗表の日付を保存する処理
        List<Goal> list=new ArrayList<>();//ArrayList作成
        List week_day_of_week=new ArrayList<>();//ArrayList作成
        Connection con = getConnection(); //DBに接続
        PreparedStatement st;
        Goal g=new Goal();
        HttpSession session = request.getSession();//セッション情報を取得。

        try{
            for (int i = 0; i < goal_progress_date.size(); i++) {//設定した日付分回す
                Goal date = goal_progress_date.get(i);//listから値を取得
                con.setAutoCommit(false);//自動コミットモードの無効化
                st=con.prepareStatement("insert into achievement_rate values (?, ?, ?)"); //achievement_rateDBにgoal_id,進捗表の日付を入れる
                st.setString(1,goal_id); //goal_idを設定。
                st.setString(2,date.getgoal_progress_date()); //進捗表の日付を設定。
                st.setString(3,date.get_achievement_rate()); //目標の進捗率を設定。
                int rs = st.executeUpdate(); //更新行数を返却
                System.out.println("sql実行");
                System.out.println(date.getday_of_week());
                System.out.println(date.getgoal_progress_date());
                week_day_of_week.add(date.getday_of_week());
                list.add(date);

                if (rs == 0){//sql文が失敗した時
                    con.rollback();
                    con.setAutoCommit(true);
                    con.close();//コネクションを閉じる
                    System.out.println("SQL文失敗した");
                }

                if(i+1 == goal_progress_date.size()){//これ以上DBに代入する日付なければDBの接続を終了する
                    st.close();
                    con.commit();
                    con.setAutoCommit(true);
                    con.close();
                }

            }
        }catch (SQLException e){
            System.out.println("クエリ文が失敗しました。");
            e.printStackTrace();
        };
        session.setAttribute("week_day_of_week",week_day_of_week);
        return list;//sql文が成功した時
    }*/