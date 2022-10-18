package dao;

import bean.Goal;

import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Goal_select_month_DAO extends DAO{
    public List<Goal> select_achievement_parent_list(String goal_id) throws Exception{ //子目標が全て達成している親目標を返す
        List<Goal> list=new ArrayList<>();//ArrayList作成
        Connection con = getConnection(); //DBに接続
        PreparedStatement st;
        try{
              st=con.prepareStatement("select parent_id,goal from goal_month_parent where goal_id = ? and achieved_goals = 100");
              st.setString(1,goal_id);
              ResultSet rs = st.executeQuery();
              while(rs.next()){
                  Goal g=new Goal();
                  g.setgoal_month_parent_id(rs.getString("parent_id"));//親間目標idを取得
                  g.setgoal(rs.getString("goal"));//目標を取得
                  list.add(g);
            }
              st.close();
              con.close();
            }catch (SQLException e){
              e.printStackTrace();
            };
    return list;
    }

    public List<Goal> select_achievement_child_list(String goal_id) throws Exception{ //目標が達成している子目標を返す
        List<Goal> list=new ArrayList<>();//ArrayList作成
        Connection con = getConnection(); //DBに接続
        PreparedStatement st;
        try{
              st=con.prepareStatement("select child_id,goal from goal_month_child where achieved_goal =1 and goal_id =?");
              st.setString(1,goal_id);
              ResultSet rs = st.executeQuery();
              while(rs.next()){
                  Goal g=new Goal();
                  g.setgoal_month_child_id(rs.getString("child_id"));//子目標idを取得
                  g.setgoal(rs.getString("goal"));//目標を取得
                  list.add(g);
            }
              st.close();
              con.close();
            }catch (SQLException e){
              e.printStackTrace();
            };
    return list;
    }

    public List<Goal> select_not_achievement_parent_list(String goal_id) throws Exception{ //目標未達の親情報を返す
        List<Goal> list=new ArrayList<>();//ArrayList作成
        Connection con = getConnection(); //DBに接続
        PreparedStatement st;
        try{
              st=con.prepareStatement("select parent_id,goal from goal_month_parent where achieved_goals != 100 or achieved_goals is null and goal_id = ?"); 
              st.setString(1,goal_id);
              ResultSet rs = st.executeQuery();
              while(rs.next()){
                  Goal g=new Goal();
                  g.setgoal_month_parent_id(rs.getString("parent_id"));//親間目標idを取得
                  g.setgoal(rs.getString("goal"));//目標を取得
                  list.add(g);
              }
              st.close();
              con.close();
            }catch (SQLException e){
              e.printStackTrace();
            };
    return list;
    }

    public List<Goal> select_not_achievement_child_list(String goal_id) throws Exception{ //目標未達の子情報を返す
        List<Goal> list=new ArrayList<>();//ArrayList作成
        Connection con = getConnection(); //DBに接続
        PreparedStatement st;
        try{
              st=con.prepareStatement("select child_id,goal from goal_month_child where achieved_goal is null and goal_id = ?");
              st.setString(1,goal_id);
              ResultSet rs = st.executeQuery();
              while(rs.next()){
                  Goal g=new Goal();
                  g.setgoal_month_child_id(rs.getString("child_id"));//子間目標idを取得
                  g.setgoal(rs.getString("goal"));//目標idを取得
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
              st=con.prepareStatement("select TRUNCATE(count(achieved_goal) / (sum(achieved_goal is null) + count(achieved_goal)) * 100,0) from goal_month_child where goal_id = ?");
              st.setString(1,goal_id);
              ResultSet rs = st.executeQuery();
              while(rs.next()){
                  Goal g=new Goal();
                  g.setachievement_rate(rs.getString("TRUNCATE(count(achieved_goal) / (sum(achieved_goal is null) + count(achieved_goal)) * 100,0)"));
                  list.add(g);
            }
              st.close();
              con.close();
            }catch (SQLException e){
              e.printStackTrace();
            };
    return list;
    }

    public List<Goal> select_goal_parent_all(String goal_id) throws Exception{ //目標idに紐づく親目標を返す
        List<Goal> list=new ArrayList<>();//ArrayList作成
        Connection con = getConnection(); //DBに接続
        PreparedStatement st;
        try{
              st=con.prepareStatement("select * from goal_month_parent where goal_id=?"); 
              st.setString(1,goal_id);
              ResultSet rs = st.executeQuery();
              while(rs.next()){
                  Goal g=new Goal();
                  g.setgoal(rs.getString("goal"));//目標を取得
                  g.setgoal_month_parent_id(rs.getString("parent_id"));//親間目標idを取得
                  list.add(g);       
            }
              st.close();
              con.close();
            }catch (SQLException e){
              e.printStackTrace();
            };
    return list;//listを返す
    }

    public List<Goal> select_goal_parent(String parent_id) throws Exception{ //親目標idに紐づく親目標を返す
        List<Goal> list=new ArrayList<>();//ArrayList作成
        Connection con = getConnection(); //DBに接続
        PreparedStatement st;
        try{
              st=con.prepareStatement("select * from goal_month_parent where parent_id=?");
              st.setString(1,parent_id);
              ResultSet rs = st.executeQuery();
              while(rs.next()){
                  Goal g=new Goal();
                  g.setgoal(rs.getString("goal"));//目標を取得
                  g.setgoal_month_parent_id(rs.getString("parent_id"));//親間目標idを取得
                  list.add(g);
            }
              st.close();
              con.close();
            }catch (SQLException e){
              e.printStackTrace();
            };
    return list;//listを返す
    }

    public String check_parent(String goal_id){//目標idに紐づく親目標idを返す
        try{
            String goal_month_parent_id = null;
            Connection con = getConnection(); //DBに接続
            PreparedStatement st;//目標削除。
            st = con.prepareStatement("select parent_id from goal_month_parent where goal_id=?"); 
            st.setString(1,goal_id);
            ResultSet rs_check_parent_id = st.executeQuery(); //sql文実施

            while(rs_check_parent_id.next()){
                Goal g = new Goal();
                g.setgoal_month_parent_id(rs_check_parent_id.getString("parent_id"));//取得した親目標idをgoalインスタンスに設定
                goal_month_parent_id = g.getgoal_month_parent_id();//親目標idを取得
            }
            st.close();
            con.close();
            return goal_month_parent_id;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public List<Goal> select_goal_child(String parent_id) throws Exception{ //親目標idに紐づく子目標を返す
        List<Goal> list=new ArrayList<>();//ArrayList作成
        Connection con = getConnection(); //DBに接続
        PreparedStatement st;
        try{
              st=con.prepareStatement("select * from goal_month_child where parent_id=?");
              st.setString(1,parent_id);
              ResultSet rs = st.executeQuery();
              while(rs.next()){
                  Goal g=new Goal();
                  g.setgoal(rs.getString("goal"));//目標を取得
                  g.setachieved_goal(rs.getString("achieved_goal"));//目標達成判定取得
                  g.setgoal_month_child_id(rs.getString("child_id"));//子間目標idを取得
                  list.add(g);  
              }
              st.close();
              con.close();
            }catch (SQLException e){
              e.printStackTrace();
            };
    return list;//listを返す
    }

    public List<Goal> select_goal_parent_id(String child_id) throws Exception{ //子目標に紐づく親目標idを返す
        List<Goal> list=new ArrayList<>();//ArrayList作成
        Connection con = getConnection(); //DBに接続
        PreparedStatement st;
        try{
              st=con.prepareStatement("select parent_id from goal_month_child where child_id = ?");
              st.setString(1,child_id);
              ResultSet rs = st.executeQuery();
              while(rs.next()){
                  Goal g=new Goal();
                  g.setgoal_month_parent_id(rs.getString("parent_id"));//親間目標idを取得
                  list.add(g);       
              }
              st.close();
              con.close();
            }catch (SQLException e){
              e.printStackTrace();
            };
    return list;
    }

    public boolean check_child(String goal_id){//子目標が存在するかチェック
        Goal g=new Goal();
        try{
            Connection con = getConnection(); //DBに接続
            PreparedStatement st;
            st = con.prepareStatement("select * from goal_month_child where goal_id=?"); //目標DBを削除。
            st.setString(1,goal_id);
            ResultSet rs_check_child_id = st.executeQuery(); //sql文実施

            if (rs_check_child_id.next()){
              g.setgoal_month_child_id(rs_check_child_id.getString("child_id"));//子目標のidを取得
            }
            st.close();
            con.close();//コネクションを閉じる

            if(g.getgoal_month_child_id() != null){ //子目標が存在するか確認
              return true;
            }else{
              return false;
            }
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }
}