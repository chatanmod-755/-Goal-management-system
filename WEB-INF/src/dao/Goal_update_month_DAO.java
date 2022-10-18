package dao;

import bean.Goal;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
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
import java.sql.Date;

public class Goal_update_month_DAO extends DAO{
    public  Boolean goal_month_parent_rename(String goal_month_parent_id,String goal_rename){//親目標名更新
        Connection con = getConnection(); //DBに接続
        PreparedStatement st;

    try{
        st=con.prepareStatement("update goal_month_parent set goal=? where parent_id=?");
        st.setString(1,goal_rename);
        st.setString(2,goal_month_parent_id);
        int rs_update = st.executeUpdate();//sql文実施
        if (rs_update == 0){//sql文が失敗した時
            con.rollback();
            con.setAutoCommit(true);
            con.close();
            return false;
        }
            con.setAutoCommit(false);
            con.commit();
            con.close();
    }catch(SQLException e){
        return false;
    };
    return true;
    }

public  Boolean goal_month_child_rename(String goal_month_child_id,String goal_rename,String goal_month_parent_id){//子目標名更新
        Connection con = getConnection(); //DBに接続
        PreparedStatement st;

    try{
        st=con.prepareStatement("update goal_month_child set goal=? where child_id=? AND parent_id=?");
        st.setString(1,goal_rename);
        st.setString(2,goal_month_child_id);
        st.setString(3,goal_month_parent_id);
        int rs_update = st.executeUpdate();//sql文実施
        if (rs_update == 0){//sql文が失敗した時
            con.rollback();
            con.setAutoCommit(true);
            con.close();
            return false;
        }
            con.setAutoCommit(false);
            con.commit();
            con.close();
    }catch(SQLException e){
        return false;
    };
    return true;
    }
    /*public  Boolean goal_delete(String goal_week_id){//目標削除
        Connection con = getConnection(); //DBに接続
        PreparedStatement st;

    try{
        st=con.prepareStatement("delete from goal_week where goal_week_id=?");
        st.setString(1,goal_week_id);
        int rs_update = st.executeUpdate();//sql文実施
        if (rs_update == 0){//sql文が失敗した時
            con.rollback();
            con.setAutoCommit(true);
            con.close();//コネクションを閉じる
            return false;
        }
            con.commit();
            con.setAutoCommit(true);
            con.close();
    }catch(SQLException e){
        return false;
    };
    return true;
    }*/

    public  Boolean goal_month_parent_add(String goal_id,String user_id,String goal_name){//親目標追加
        Connection con = getConnection(); //DBに接続
        PreparedStatement st;
        PreparedStatement st2;
        Goal g=new Goal();

    try{
        st=con.prepareStatement("select MAX(parent_id)+1 from goal_month_parent");
        ResultSet rs = st.executeQuery();
        if(rs.next()){
            g.setgoal_month_parent_id(rs.getString("MAX(parent_id)+1"));
        }

        if(g.getgoal_month_parent_id() == null){//goal_month_parent_idがなければ1を設定する
            g.setgoal_month_parent_id("1");
        }

        st.close();
        con.setAutoCommit(false);
        st2=con.prepareStatement("insert into goal_month_parent values(?,?,?,?,null)");
        st2.setString(1,goal_id);
        st2.setString(2,g.getgoal_month_parent_id());
        st2.setString(3,user_id);
        st2.setString(4,goal_name);
        int rs_add = st2.executeUpdate();//sql文実施
        st2.close();
        if (rs_add == 0){//sql文が失敗した時
            con.rollback();
            con.setAutoCommit(true);
            con.close();
            return false;
        }
            con.setAutoCommit(false);
            con.commit();
            con.close();
    }catch(SQLException e){
        return false;
    };
    return true;
    }


    public  Boolean goal_month_child_add(String goal_id,String goal_month_parent_id,String user_id,String goal_name){//子目標追加
        Connection con = getConnection(); //DBに接続
        PreparedStatement st;
        PreparedStatement st2;
        Goal g=new Goal();

    try{
        st=con.prepareStatement("select MAX(child_id)+1 from goal_month_child");
        ResultSet rs = st.executeQuery();
        if(rs.next()){
            g.setgoal_month_child_id(rs.getString("MAX(child_id)+1"));

        }   
        if(g.getgoal_month_child_id() == null){//goal_month_child_idがなければ1を設定する
            g.setgoal_month_child_id("1");
        }

        st.close();
        con.setAutoCommit(false);
        st2=con.prepareStatement("insert into goal_month_child values(?,?,?,?,?,null)");
        st2.setString(1,g.getgoal_month_child_id());
        st2.setString(2,goal_month_parent_id);
        st2.setString(3,goal_id);
        st2.setString(4,user_id);
        st2.setString(5,goal_name);
        int rs_add = st2.executeUpdate();//sql文実施
        st2.close();
        if (rs_add == 0){//sql文が失敗した時
            con.rollback();
            con.setAutoCommit(true);
            con.close();
            return false;
        }
            con.setAutoCommit(false);
            con.commit();
            con.close();
    }catch(SQLException e){
        e.printStackTrace();
        return false;
    };
    return true;
    }

    public  Boolean goal_achieve_update(String goal_month_parent_id,String goal_month_child_id){//子目標進捗更新
        Connection con = getConnection(); //DBに接続
        PreparedStatement st;

    try{
        st=con.prepareStatement("update goal_month_child set achieved_goal = 1 where parent_id =? and child_id =?");
        st.setString(1,goal_month_parent_id);
        st.setString(2,goal_month_child_id);
        int rs_update = st.executeUpdate();//sql文実施
        if (rs_update == 0){//sql文が失敗した時
            con.rollback();
            con.close();
            return false;
        }
            con.setAutoCommit(false);
            con.commit();
            con.close();
    }catch(SQLException e){
        e.printStackTrace();
        return false;
    };
        return true;
    }

    public  Boolean goal_achieve_parent_update(String goal_month_child_id){//子目標が全て達成した場合、親目標達処理する
        Connection con = getConnection(); //DBに接続
        Connection con2 = getConnection(); //DBに接続
        PreparedStatement st;
        PreparedStatement st2;
        PreparedStatement st3;
    try{
        st=con.prepareStatement("select parent_id from goal_month_child where achieved_goal is null and parent_id =(select parent_id from goal_month_child where child_id =?)");
        st.setString(1,goal_month_child_id);
        ResultSet rs = st.executeQuery();

        if(rs.next()){
            System.out.println("データが返ってきたので親目標は更新しない");
        }else{
            st=con.prepareStatement("select parent_id from goal_month_child where child_id = ?");
            st.setString(1,goal_month_child_id);
            ResultSet rs2 = st.executeQuery();
            if(rs2.next()){
                String parent_id = rs2.getString("parent_id");
                st=con.prepareStatement("update goal_month_parent set achieved_goals = 100 where parent_id = ?");
                st.setString(1,parent_id);
                int rs_update = st.executeUpdate();//sql文実施
            }
        }
            con.setAutoCommit(false);
            con.commit();
            con.close();
    }catch(SQLException e){
        e.printStackTrace();
        return false;
    };
    return true;
    }

    public  Boolean achievement_rate_update(String goal_id,Date date,String Goal_achievement_rate){//目標進捗率更新
        Connection con = getConnection(); //DBに接続
        PreparedStatement st;
    try{
        st=con.prepareStatement("update achievement_rate_month set goal_rate = ? where goal_id = ? and date >= ?");
        st.setString(1,Goal_achievement_rate);
        st.setString(2,goal_id);
        st.setDate(3,date);
        int rs_update = st.executeUpdate();//sql文実施

        if (rs_update == 0){//sql文が失敗(今日の日付が最後の日付を超えている場合)
            con.setAutoCommit(false);
            con.commit();
            con.close();
            return false;
        }
            con.setAutoCommit(false);
            con.commit();
            con.close();
    }catch(SQLException e){
        e.printStackTrace();
        return false;
    };
    return true;
    }
}
