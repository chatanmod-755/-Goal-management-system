package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Delete_month_DAO extends DAO{
    public Boolean goal_del(String goal_id){//目標idに紐づく月間進捗・目標表の目標情報を削除。
        try{
            Connection con = getConnection(); //DBに接続
            PreparedStatement st;
            st = con.prepareStatement("delete from achievement_rate_month where goal_id=?");
            st.setString(1,goal_id); 
            int del_goal_rate = st.executeUpdate(); //sql文実施

            st = con.prepareStatement("delete from goal_create where goal_id=?");
            st.setString(1,goal_id); 
            int del_goal = st.executeUpdate(); //sql文実施

            if (del_goal == 0){//sql文が失敗した時
                con.rollback();
                con.setAutoCommit(true);
                con.close();
                return false;
            }else{
                con.setAutoCommit(false);
                con.commit();
                st.close();
                con.close();
                return true;
            }
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public Boolean parent_del_all(String goal_id){//目標idに紐づく月間の親目標を全て削除。
        try{
            Connection con = getConnection(); //DBに接続
            PreparedStatement st;
            st = con.prepareStatement("delete from goal_month_parent where goal_id=?"); 
            st.setString(1,goal_id);
            int del_goal = st.executeUpdate(); //sql文実施

            if (del_goal == 0){//sql文が失敗した時
                con.rollback();
                con.setAutoCommit(true);
                con.close();
                return false;
            }else{
                con.setAutoCommit(false);
                con.commit();
                st.close();
                con.close();
                return true;
            }
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public Boolean parent_del(String parent_id){//親目標idに紐づく月間親目標を削除。
        try{
            Connection con = getConnection(); //DBに接続
            PreparedStatement st;
            st = con.prepareStatement("delete from goal_month_parent where parent_id=?"); //目標DBを削除。
            st.setString(1,parent_id);
            int del_goal = st.executeUpdate(); //sql文実施

            if (del_goal == 0){//sql文が失敗した時
                con.rollback();
                con.setAutoCommit(true);
                con.close();
                return false;
            }else{
                con.setAutoCommit(false);
                con.commit();
                st.close();
                con.close();
                return true;
            }
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public Boolean child_del_all(String goal_id){//目標idに紐づく月間子目標全てを削除。
        try{
            Connection con = getConnection(); //DBに接続
            PreparedStatement st;
            st = con.prepareStatement("delete from goal_month_child where goal_id=?"); 
            st.setString(1,goal_id);
            int del_goal = st.executeUpdate(); //sql文実施

            if (del_goal == 0){//sql文が失敗した時
                con.rollback();
                con.setAutoCommit(true);
                con.close();
                return false;
            }else{
                con.setAutoCommit(false);
                con.commit();
                st.close();
                con.close();
                return true;
            }
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public Boolean childs_del(String parent_id){//親目標idに紐づく特定の月間子目標を全て削除。
        try{
            Connection con = getConnection(); //DBに接続
            PreparedStatement st;
            st = con.prepareStatement("delete from goal_month_child where parent_id=?"); //目標DBを削除。
            st.setString(1,parent_id); 
            int del_goal = st.executeUpdate(); //sql文実施

            if (del_goal == 0){//sql文が失敗した時
                con.rollback();
                con.setAutoCommit(true);
                con.close();
                return false;
            }else{
                con.setAutoCommit(false);
                con.commit();
                st.close();
                con.close();//コネクションを閉じる
                return true;
            }
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public Boolean child_del(String child_id,String parent_id){//親目標idに紐づく特定の月間子目標を削除。
        try{
            Connection con = getConnection(); //DBに接続
            PreparedStatement st;
            st = con.prepareStatement("delete from goal_month_child where child_id=? AND parent_id=?"); 
            st.setString(1,child_id);
            st.setString(2,parent_id);
            int del_goal = st.executeUpdate(); //sql文実施

            if (del_goal == 0){//sql文が失敗した時
                con.rollback();
                con.setAutoCommit(true);
                con.close();
                return false;
            }else{
                con.setAutoCommit(false);
                con.commit();
                st.close();
                con.close();
                return true;
            }
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }
}