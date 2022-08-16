package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Delete_year_DAO extends DAO{
    public boolean goal_del(String goal_id){//goal_createの目標を削除。
        System.out.println("eeeeeee");
        try{    
            Connection con = getConnection(); //DBに接続
            PreparedStatement st;//目標削除。
            st = con.prepareStatement("delete from achievement_rate_year where goal_id=?"); //進捗目標DBを削除。
            st.setString(1,goal_id); //入力されたユーザー名
            int del_goal_rate = st.executeUpdate(); //sql文実施

            st = con.prepareStatement("delete from goal_create where goal_id=?"); //目標DBを削除。
            st.setString(1,goal_id); //入力されたユーザー名
            int del_goal = st.executeUpdate(); //sql文実施
            if (del_goal == 0){//sql文が失敗した時
                con.rollback();
                con.setAutoCommit(true);
                con.close();
                System.out.println("目標削除失敗");
                return false;
            }else{
                System.out.println("目標削除成功");
                con.setAutoCommit(false);
                con.commit();
                st.close();
                con.close();//コネクションを閉じる
                return true;
            }
        }catch(SQLException e){
            System.out.println("目標削除に失敗しました。");
            e.printStackTrace();
            return false;
        }
    }

    public boolean parent_del_all(String goal_id){//親目標を削除。
        try{
            Connection con = getConnection(); //DBに接続
            PreparedStatement st;//目標削除。
            st = con.prepareStatement("delete from goal_year_parent where goal_id=?"); //目標DBを削除。
            st.setString(1,goal_id); //入力されたユーザー名
            int del_goal = st.executeUpdate(); //sql文実施
            if (del_goal == 0){//sql文が失敗した時
                con.rollback();
                con.setAutoCommit(true);
                con.close();
                System.out.println("目標削除失敗");
                return false;
            }else{
                System.out.println("目標削除成功");
                con.setAutoCommit(false);
                con.commit();
                st.close();
                con.close();//コネクションを閉じる
                return true;
            }
        }catch(SQLException e){
            System.out.println("目標削除に失敗しました。");
            e.printStackTrace();
            return false;
        }
    }

    public boolean parent_del(String parent_id){//親目標を削除。
        try{
            Connection con = getConnection(); //DBに接続
            PreparedStatement st;//目標削除。
            System.out.println("親目標削除しにきた");
            st = con.prepareStatement("delete from goal_year_parent where parent_id=?"); //目標DBを削除。
            st.setString(1,parent_id);
            int del_goal = st.executeUpdate(); //sql文実施
            if (del_goal == 0){//sql文が失敗した時
                con.rollback();
                con.setAutoCommit(true);
                con.close();
                System.out.println("目標削除失敗");
                return false;
            }else{
                System.out.println("目標削除成功");
                con.setAutoCommit(false);
                con.commit();
                st.close();
                con.close();//コネクションを閉じる
                return true;
            }
        }catch(SQLException e){
            System.out.println("親目標削除に失敗しました。");
            e.printStackTrace();
            return false;
        }
    }

    public boolean childs_del(String parent_id){//子目標を削除。
        try{
            Connection con = getConnection(); //DBに接続
            PreparedStatement st;//目標削除。
            st = con.prepareStatement("delete from goal_year_child where parent_id=?"); //目標DBを削除。
            st.setString(1,parent_id); //入力されたユーザー名
            int del_goal = st.executeUpdate(); //sql文実施
            if (del_goal == 0){//sql文が失敗した時
                con.rollback();
                con.setAutoCommit(true);
                con.close();
                System.out.println("目標削除失敗");
                return false;
            }else{
                System.out.println("目標削除成功");
                con.setAutoCommit(false);
                con.commit();
                st.close();
                con.close();//コネクションを閉じる
                return true;
            }
        }catch(SQLException e){
            System.out.println("目標削除に失敗しました。");
            e.printStackTrace();
            return false;
        }
    }

    public boolean child_del_all(String goal_id){//子目標全てを削除。
        try{
            Connection con = getConnection(); //DBに接続
            PreparedStatement st;//目標削除。
            st = con.prepareStatement("delete from goal_year_child where goal_id=?"); //目標DBを削除。
            st.setString(1,goal_id); //入力されたユーザー名
            int del_goal = st.executeUpdate(); //sql文実施
            if (del_goal == 0){//sql文が失敗した時
                con.rollback();
                con.setAutoCommit(true);
                con.close();
                System.out.println("目標削除失敗");
                return false;
            }else{
                System.out.println("目標削除成功");
                con.setAutoCommit(false);
                con.commit();
                st.close();
                con.close();//コネクションを閉じる
                return true;
            }
        }catch(SQLException e){
            System.out.println("目標削除に失敗しました。");
            e.printStackTrace();
            return false;
        }
    }

    public boolean child_del(String child_id,String parent_id){//子目標を削除。
        try{
            Connection con = getConnection(); //DBに接続
            PreparedStatement st;//目標削除。
            st = con.prepareStatement("delete from goal_year_child where child_id=? AND parent_id=?"); //目標DBを削除。
            st.setString(1,child_id); //入力されたユーザー名
            st.setString(2,parent_id); //入力されたユーザー名
            int del_goal = st.executeUpdate(); //sql文実施
            if (del_goal == 0){//sql文が失敗した時
                con.rollback();
                con.setAutoCommit(true);
                con.close();
                System.out.println("目標削除失敗");
                return false;
            }else{
                System.out.println("目標削除成功");
                con.setAutoCommit(false);
                con.commit();
                st.close();
                con.close();//コネクションを閉じる
                return true;
            }
        }catch(SQLException e){
            System.out.println("目標削除に失敗しました。");
            e.printStackTrace();
            return false;
        }
    }
}