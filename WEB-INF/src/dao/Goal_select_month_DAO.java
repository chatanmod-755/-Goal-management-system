package dao;

import bean.Goal;

import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Goal_select_month_DAO extends DAO{
    public List<Goal> select_achievement_parent_list(String goal_id) throws Exception{ //親目標達成リスト取得
        List<Goal> list=new ArrayList<>();//ArrayList作成
        System.out.println("goal_idきたー");
        System.out.println(goal_id);
        Connection con = getConnection(); //DBに接続
        PreparedStatement st;

        try{
              st=con.prepareStatement("select parent_id,goal from goal_month_parent where goal_id = ? and achieved_goals = 100"); //親目標の子目標全て達成している
              st.setString(1,goal_id);
              ResultSet rs = st.executeQuery();
              while(rs.next()){
                  Goal g=new Goal();
                  g.setgoal_month_parent_id(rs.getString("parent_id"));
                  g.setgoal(rs.getString("goal"));
                  list.add(g);
            }
              st.close();
              con.close();
            }catch (SQLException e){
              System.out.println("クエリ文が失敗しました。");
              e.printStackTrace();
            };
    return list;
    }

    public List<Goal> select_achievement_child_list(String goal_id) throws Exception{ //子目標達成リスト取得
        List<Goal> list=new ArrayList<>();//ArrayList作成
        System.out.println("goal_idきたー");
        System.out.println(goal_id);
        Connection con = getConnection(); //DBに接続
        PreparedStatement st;

        try{
              //st=con.prepareStatement("select child_id,goal from goal_month_child where parent_id = ANY (select parent_id from goal_month_parent where goal_id = ? and achieved_goals = 100)"); //親目標の子目標全て達成している
              st=con.prepareStatement("select child_id,goal from goal_month_child where achieved_goal =1 and goal_id =?"); //親目標の子目標全て達成している
              st.setString(1,goal_id);
              ResultSet rs = st.executeQuery();
              while(rs.next()){
                  Goal g=new Goal();
                  g.setgoal_month_child_id(rs.getString("child_id"));
                  g.setgoal(rs.getString("goal"));
                  list.add(g);
            }
              st.close();
              con.close();
            }catch (SQLException e){
              System.out.println("クエリ文が失敗しました。");
              e.printStackTrace();
            };
    return list;
    }

    public List<Goal> select_not_achievement_parent_list(String goal_id) throws Exception{ //子目標未達成リスト取得
        List<Goal> list=new ArrayList<>();//ArrayList作成
        System.out.println("goal_idきたー");
        System.out.println(goal_id);
        Connection con = getConnection(); //DBに接続
        PreparedStatement st;

        try{
              //st=con.prepareStatement("select goal,parent_id from goal_month_child where parent_id = ANY (select parent_id from goal_month_parent where goal_id = ? and achieved_goals is null)"); //目標未達成の情報取得
              st=con.prepareStatement("select parent_id,goal from goal_month_parent where achieved_goals != 100 or achieved_goals is null and goal_id = ?"); //目標未達成の情報取得
              st.setString(1,goal_id);
              ResultSet rs = st.executeQuery();
              while(rs.next()){
                  Goal g=new Goal();
                  g.setgoal_month_parent_id(rs.getString("parent_id"));
                  g.setgoal(rs.getString("goal"));
                  list.add(g);
              }
              st.close();
              con.close();
            }catch (SQLException e){
              System.out.println("クエリ文が失敗しました。");
              e.printStackTrace();
            };
    return list;
    }

    public List<Goal> select_not_achievement_child_list(String goal_id) throws Exception{ //子目標未達成リスト取得
        List<Goal> list=new ArrayList<>();//ArrayList作成
        System.out.println("goal_idきたー");
        System.out.println(goal_id);
        Connection con = getConnection(); //DBに接続
        PreparedStatement st;

        try{
              st=con.prepareStatement("select child_id,goal from goal_month_child where achieved_goal is null and goal_id = ?"); //目標未達成の情報取得
              st.setString(1,goal_id);
              ResultSet rs = st.executeQuery();
              while(rs.next()){
                  Goal g=new Goal();
                  g.setgoal_month_child_id(rs.getString("child_id"));
                  g.setgoal(rs.getString("goal"));
                  list.add(g);
              }
              st.close();
              con.close();
            }catch (SQLException e){
              System.out.println("クエリ文が失敗しました。");
              e.printStackTrace();
            };
    return list;
    }
    public List<Goal> select_achievement_rate(String goal_id) throws Exception{ //目標達成率取得
        List<Goal> list=new ArrayList<>();//ArrayList作成
        System.out.println("goal_idきたー");
        System.out.println(goal_id);
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
              System.out.println("クエリ文が失敗しました。");
              e.printStackTrace();
            };
    return list;
    }

    public List<Goal> select_some_achievement_list(String goal_id) throws Exception{ //目標を一つ以上達成している。
        List<Goal> list=new ArrayList<>();//ArrayList作成
        System.out.println("some_achievement_list取得する");
        System.out.println(goal_id);
        Connection con = getConnection(); //DBに接続
        PreparedStatement st;
        try{
              st=con.prepareStatement("select child_id,goal from goal_month_child where parent_id = ANY (select parent_id from goal_month_parent where goal_id = ? and achieved_goals = 1)"); //月間目標から目標を取得
              //st=con.prepareStatement("select child_id,goal from goal_month_child where goal_id = ?"); //月間目標から目標を取得
              st.setString(1,goal_id);
              ResultSet rs = st.executeQuery();
              //ResultSet rs2 = st2.executeQuery();
              while(rs.next()){
                  Goal g=new Goal();
                  System.out.println("-");
                  System.out.println("--");
                  g.setgoal_month_child_id(rs.getString("child_id"));
                  g.setgoal(rs.getString("goal"));
                  list.add(g);
              }
                  st.close();
                  con.close();
            }catch (SQLException e){
              System.out.println("クエリ文が失敗しました。");
              e.printStackTrace();
            };
      return list;//listを返す
    }

    public List<Goal> select_goal_parent_all(String goal_id) throws Exception{ //Goal_createから月間の親目標を取得。
        List<Goal> list=new ArrayList<>();//ArrayList作成
        System.out.println("goal_idきたー");
        System.out.println(goal_id);
        Connection con = getConnection(); //DBに接続
        PreparedStatement st;

        try{
              st=con.prepareStatement("select * from goal_month_parent where goal_id=?"); //月間目標から目標を取得
              st.setString(1,goal_id);
              ResultSet rs = st.executeQuery();
              while(rs.next()){
                  Goal g=new Goal();
                  g.setgoal(rs.getString("goal"));//目標を設定
                  g.setgoal_month_parent_id(rs.getString("parent_id"));//親間目標idを設定
                  list.add(g);//リストに値を追加        
                  System.out.println(list);
            }
              st.close();
              con.close();
            }catch (SQLException e){
              System.out.println("クエリ文が失敗しました。");
              e.printStackTrace();
            };
    return list;//listを返す
    }

    public List<Goal> select_goal_parent(String parent_id) throws Exception{ //親目標取得。
        List<Goal> list=new ArrayList<>();//ArrayList作成
        System.out.println("parent_idきたー");
        System.out.println(parent_id);
        Connection con = getConnection(); //DBに接続
        PreparedStatement st;

        try{
              st=con.prepareStatement("select * from goal_month_parent where parent_id=?");
              st.setString(1,parent_id);
              ResultSet rs = st.executeQuery();
              while(rs.next()){
                  Goal g=new Goal();
                  g.setgoal(rs.getString("goal"));//目標を設定
                  g.setgoal_month_parent_id(rs.getString("parent_id"));//親間目標idを設定
                  list.add(g);//リストに値を追加
                  System.out.println(list);
            }
              st.close();
              con.close();
            }catch (SQLException e){
              System.out.println("クエリ文が失敗しました。");
              e.printStackTrace();
            };
    return list;//listを返す
    }

    public String check_parent(String goal_id){//親目標にデータがあるか確認
        try{
            //Goal g = new Goal();//goalインスタンス生成
            String goal_month_parent_id = null;
            Connection con = getConnection(); //DBに接続
            PreparedStatement st;//目標削除。
            st = con.prepareStatement("select parent_id from goal_month_parent where goal_id=?"); //親目標DBから親idを取得
            st.setString(1,goal_id);
            ResultSet rs_check_parent_id = st.executeQuery(); //sql文実施
            while(rs_check_parent_id.next()){
                Goal g = new Goal();//goalインスタンス生成
                g.setgoal_month_parent_id(rs_check_parent_id.getString("parent_id"));//取得した親目標idをgoalインスタンスに設定
                goal_month_parent_id = g.getgoal_month_parent_id();//親idを返す
            }
            st.close();
            con.close();//コネクションを閉じる
            return goal_month_parent_id;
        }catch(SQLException e){
            System.out.println("親目標id取得に失敗しました。");
            e.printStackTrace();
            return null;
        }
    }

    public List<Goal> select_goal_child(String parent_id) throws Exception{ //子目標取得。
        List<Goal> list=new ArrayList<>();//ArrayList作成
        System.out.println("parent_idきたー");
        System.out.println(parent_id);
        Connection con = getConnection(); //DBに接続
        PreparedStatement st;

        try{
              st=con.prepareStatement("select * from goal_month_child where parent_id=?");
              st.setString(1,parent_id);
              ResultSet rs = st.executeQuery();
              while(rs.next()){
                  Goal g=new Goal();
                  g.setgoal(rs.getString("goal"));//目標を設定
                  g.setachieved_goal(rs.getString("achieved_goal"));//目標達成判定取得
                  g.setgoal_month_child_id(rs.getString("child_id"));//子間目標idを設定
                  list.add(g);//リストに値を追加        
                  System.out.println(list);
            }
              st.close();
              con.close();
            }catch (SQLException e){
              System.out.println("クエリ文が失敗しました。");
              e.printStackTrace();
            };
    return list;//listを返す
    }

    public boolean check_child(String parent_id){//子目標を表示
        Goal g=new Goal();
        try{
            Connection con = getConnection(); //DBに接続
            PreparedStatement st;//目標削除。
            st = con.prepareStatement("select * from goal_month_child where parent_id=?"); //目標DBを削除。
            st.setString(1,parent_id); //入力されたユーザー名
            ResultSet rs_check_child_id = st.executeQuery(); //sql文実施

            if (rs_check_child_id.next()){//子目標のidを取得
              System.out.println("子目標チェック");
              g.setgoal_month_child_id(rs_check_child_id.getString("child_id"));
            }

            st.close();
            con.close();//コネクションを閉じる

            if(g.getgoal_month_child_id() != null){ //子目標が存在するか確認
              return true;
            }else{
              System.out.println("子目標がない");
              return false;
            }

        }catch(SQLException e){
            System.out.println("目標削除に失敗しました。");
            e.printStackTrace();
            return false;
        }
    }
}