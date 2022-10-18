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

public class Goal_select_week_DAO extends DAO{
    public List<Goal> select_goal_list(String goal_id) throws Exception{ //子目標が全て達成している親目標を返す
        List<Goal> list=new ArrayList<>();//ArrayList作成
        Connection con = getConnection(); //DBに接続
        PreparedStatement st;
        try{
              st=con.prepareStatement("select * from goal_week where goal_id=?");
              st.setString(1,goal_id);
              ResultSet rs = st.executeQuery();
              while(rs.next()){
                  Goal g=new Goal();
                  g.setgoal(rs.getString("goal"));//目標を取得
                  g.setgoal_week_id(rs.getString("goal_week_id"));//週間目標idを取得
                  g.setgoal_condition(rs.getString("goal_condition"));//目標達成条件回数を取得
                  g.setgoal_count(rs.getString("goal_count"));//目標達成回数を取得
                  g.setgoal_type(rs.getString("goal_type"));//目標の種類を取得
                  list.add(g);
            }
              st.close();
              con.close();
            }catch (SQLException e){
              e.printStackTrace();
            };
    return list;
    }

    public List<Goal> select_achievement_list(String goal_id) throws Exception{ //目標達成リストを返す
        List<Goal> list=new ArrayList<>();//ArrayList作成
        Connection con = getConnection(); //DBに接続
        PreparedStatement st;
        try{
              st=con.prepareStatement("select goal from goal_week where goal_condition = goal_count AND goal_id=?");
              st.setString(1,goal_id);
              ResultSet rs = st.executeQuery();
              while(rs.next()){
                  Goal g=new Goal();
                  g.setgoal(rs.getString("goal"));
                  list.add(g);
            }
              st.close();
              con.close();
            }catch (SQLException e){
              e.printStackTrace();
            };
    return list;
    }

    public List<Goal> select_achievement_rate(String goal_id) throws Exception{ //目標達成率を返す
        List<Goal> list=new ArrayList<>();//ArrayList作成
        Connection con = getConnection(); //DBに接続
        PreparedStatement st;
        try{
              st=con.prepareStatement("select TRUNCATE(sum(goal_count) / sum(goal_condition) * 100,0) from goal_week where goal_id =?");
              st.setString(1,goal_id);
              ResultSet rs = st.executeQuery();
              while(rs.next()){
                  Goal g=new Goal();
                  g.setachievement_rate(rs.getString("TRUNCATE(sum(goal_count) / sum(goal_condition) * 100,0)"));
                  list.add(g);
            }
              st.close();
              con.close();
            }catch (SQLException e){
              e.printStackTrace();
            };
    return list;
    }

    public List<Goal> select_not_achievement_list(String goal_id) throws Exception{ //目標未達成リストを返す
        List<Goal> list=new ArrayList<>();//ArrayList作成
        Connection con = getConnection(); //DBに接続
        PreparedStatement st;
        try{
              st=con.prepareStatement("select goal_week_id,goal,goal_condition - goal_count from goal_week where goal_condition - goal_count > 0 AND goal_id=?");
              st.setString(1,goal_id);
              ResultSet rs = st.executeQuery();
              while(rs.next()){
                  Goal g=new Goal();
                  g.setgoal_week_id(rs.getString("goal_week_id"));//週間目標idを取得
                  g.setgoal(rs.getString("goal"));//目標を取得
                  g.setgoal_condition(rs.getString("goal_condition - goal_count"));//目標達成条件回数を取得
                  list.add(g);
            }
              st.close();
              con.close();
            }catch (SQLException e){
              e.printStackTrace();
            };
    return list;
    }

    public List<Goal> select_goal_date(String goal_id){ //目標idに紐づく目標を返す
        try{
            List<Goal> list=new ArrayList<>();//ArrayList作成
            Connection con = getConnection(); //DBに接続
            PreparedStatement st;
            st = con.prepareStatement("select * from goal_week where goal_id=?");
            st.setString(1,goal_id);
            ResultSet rs = st.executeQuery(); //sql文実施
            while(rs.next()){
                Goal g = new Goal();
                g.setgoal_start_date(rs.getString("goal_start_date"));//最初の日付を取得
                g.setgoal_end_date(rs.getString("goal_end_date"));//最後の日付を取得
                list.add(g);
            }
            st.close();
            con.close();
            return list;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}
