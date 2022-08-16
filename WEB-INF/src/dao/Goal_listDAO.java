package dao;

import bean.Goal;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.Date;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Goal_listDAO extends DAO{
    public List<Goal> search(String user_id) throws Exception{ //ユーザーidをDBと照合
        List<Goal> list=new ArrayList<>();//ArrayList作成

        Connection con = getConnection(); //DBに接続
        PreparedStatement st;

        try{
              st=con.prepareStatement("select * from goal_create where user_id=?"); //目標DBにユーザーidが合致しているか検索
              st.setString(1,user_id); //入力されたユーザー名
              ResultSet rs = st.executeQuery(); //sql文実施
              while(rs.next()){
                  Goal g=new Goal();//goalのインスタンス化
                  g.setgoal_id(rs.getString("goal_id"));//目標idを設定.
                  g.setgoal_type(rs.getString("goal_type"));//目標の種類を設定.
                  g.setgoal_start_date(rs.getString("goal_start_date"));//最初の日付を設定。
                  g.setgoal_end_date(rs.getString("goal_end_date"));//最後の日付を設定。
                  list.add(g);
                  System.out.println(list);
            }
              st.close();
              con.close();//コネクションを閉じる
            }catch (SQLException e){
              System.out.println("クエリ文が失敗しました。");
              e.printStackTrace();
            };
    return list;
    }

    public List<Goal> search_date(String goal_id) throws Exception{ //ゴールidをDBと照合
        List<Goal> list=new ArrayList<>();//ArrayList作成

        Connection con = getConnection(); //DBに接続
        PreparedStatement st;

        try{
              System.out.println("進捗率取得するよー");
              st=con.prepareStatement("select * from goal_create where goal_id=?"); //目標DBにゴールidが合致しているか検索
              st.setString(1,goal_id); //入力されたユーザー名
              ResultSet rs = st.executeQuery(); //sql文実施
              while(rs.next()){
                  Goal g=new Goal();
                  g.setgoal_start_date(rs.getString("goal_start_date"));//最初の日付を設定。
                  g.setgoal_end_date(rs.getString("goal_end_date"));//最後の日付を設定。
                  list.add(g);
                  System.out.println(list);
            }
              st.close();
              con.close();//コネクションを閉じる
            }catch (SQLException e){
              System.out.println("クエリ文が失敗しました。");
              e.printStackTrace();
            };
    return list;
    }

  public List<Goal> select_goal_progress_date(String goal_id) throws Exception{ //goal_idをDBと照合
        List<Goal> list=new ArrayList<>();//ArrayList作成

        Connection con = getConnection(); //DBに接続
        PreparedStatement st;

        try{
              st=con.prepareStatement("select * from achievement_rate_week  where goal_id=?"); //goal_idに紐づく進捗表の日付を取得
              st.setString(1,goal_id); //入力されたユーザー名
              ResultSet rs = st.executeQuery(); //sql文実施
              while(rs.next()){
                  Goal g=new Goal();//goalのインスタンス化
                  g.setgoal_progress_date(rs.getString("date"));//進捗表の日付を取得
                  g.setachievement_rate(rs.getString("goal_rate"));//進捗表率を取得
                  g.setday_of_week(rs.getString("day_of_week"));//進捗表の曜日を取得
                  list.add(g);
            }
              st.close();
              con.close();//コネクションを閉じる
            }catch (SQLException e){
              System.out.println("クエリ文が失敗しました。");
              e.printStackTrace();
            };
    return list;
    }

  public List<Goal> select_goal_progress_month_date(String goal_id) throws Exception{ //goal_idをDBと照合
        List<Goal> list=new ArrayList<>();//ArrayList作成

        Connection con = getConnection(); //DBに接続
        PreparedStatement st;

        try{
              st=con.prepareStatement("select * from achievement_rate_month  where goal_id=?"); //goal_idに紐づく進捗表の日付を取得
              st.setString(1,goal_id); //入力されたユーザー名
              ResultSet rs = st.executeQuery(); //sql文実施
              while(rs.next()){
                  Goal g=new Goal();//goalのインスタンス化
                  g.setgoal_progress_date(rs.getString("date"));//進捗表の日付を取得
                  g.setachievement_rate(rs.getString("goal_rate"));//進捗表率を取得
                  g.setday_of_week(rs.getString("day_of_week"));//進捗表の曜日を取得
                  list.add(g);
            }
              st.close();
              con.close();//コネクションを閉じる
            }catch (SQLException e){
              System.out.println("クエリ文が失敗しました。");
              e.printStackTrace();
            };
    return list;
    }

  public List<Goal> select_goal_progress_year_date(String goal_id) throws Exception{ //goal_idをDBと照合
        List<Goal> list=new ArrayList<>();//ArrayList作成

        Connection con = getConnection(); //DBに接続
        PreparedStatement st;

        try{
              st=con.prepareStatement("select * from achievement_rate_year  where goal_id=?"); //goal_idに紐づく進捗表の日付を取得
              st.setString(1,goal_id); //入力されたユーザー名
              ResultSet rs = st.executeQuery(); //sql文実施
              while(rs.next()){
                  Goal g=new Goal();//goalのインスタンス化
                  g.setgoal_progress_date(rs.getString("date"));//進捗表の日付を取得
                  g.setachievement_rate(rs.getString("goal_rate"));//進捗表率を取得
                  g.setday_of_week(rs.getString("day_of_week"));//進捗表の曜日を取得
                  list.add(g);
            }
              st.close();
              con.close();//コネクションを閉じる
            }catch (SQLException e){
              System.out.println("クエリ文が失敗しました。");
              e.printStackTrace();
            };
    return list;
    }
}
