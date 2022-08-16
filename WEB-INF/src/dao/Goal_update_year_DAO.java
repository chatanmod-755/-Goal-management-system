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

public class Goal_update_year_DAO extends DAO{
    public  Boolean goal_year_parent_rename(String goal_year_parent_id,String goal_rename){//目標情報更新
        System.out.println("目標情報更新SQL文実施するよー");
        System.out.println(goal_year_parent_id);
        System.out.println(goal_rename);
        Connection con = getConnection(); //DBに接続
        PreparedStatement st;

    try{
        st=con.prepareStatement("update goal_year_parent set goal=? where parent_id=?");
        st.setString(1,goal_rename);
        st.setString(2,goal_year_parent_id);
        int rs_update = st.executeUpdate();//sql文実施
        if (rs_update == 0){//sql文が失敗した時
            con.rollback();
            con.setAutoCommit(true);
            con.close();//コネクションを閉じる
            return false;
        }
            con.setAutoCommit(false);
            con.commit();
            con.close();
    }catch(SQLException e){
        System.out.println("リストの取得に失敗");
        return false;
    };
    return true;
    }

    public  Boolean goal_year_child_rename(String goal_year_child_id,String goal_rename,String goal_year_parent_id){//目標情報更新
        System.out.println("目標情報更新SQL文実施するよー");
        System.out.println(goal_year_child_id);
        System.out.println(goal_rename);
        System.out.println(goal_year_parent_id);
        Connection con = getConnection(); //DBに接続
        PreparedStatement st;

    try{
        st=con.prepareStatement("update goal_year_child set goal=? where child_id=?  AND parent_id=?");
        st.setString(1,goal_rename);
        st.setString(2,goal_year_child_id);
        st.setString(3,goal_year_parent_id);
        int rs_update = st.executeUpdate();//sql文実施
        if (rs_update == 0){//sql文が失敗した時
            con.rollback();
            con.setAutoCommit(true);
            con.close();//コネクションを閉じる
            return false;
        }
            con.setAutoCommit(false);
            con.commit();
            con.close();
    }catch(SQLException e){
        System.out.println("リストの取得に失敗");
        return false;
    };
    return true;
    }

    public  Boolean goal_year_parent_add(String goal_id,String user_id){//親目標追加
        System.out.println("目標情報更新SQL文実施するよー");
        Connection con = getConnection(); //DBに接続
        PreparedStatement st;
        PreparedStatement st2;
        Goal g=new Goal();

    try{
        st=con.prepareStatement("select MAX(parent_id)+1 from goal_year_parent");
        ResultSet rs = st.executeQuery();
        System.out.println("rs表示");
        System.out.println(rs);
        if(rs.next()){
            System.out.println("goal_year_parent_idが空白か確認する");
            g.setgoal_year_parent_id(rs.getString("MAX(parent_id)+1"));
        }

        if(g.getgoal_year_parent_id() == null){//goal_year_parent_idがなければ1を設定する
            g.setgoal_year_parent_id("1");
        }

        System.out.println("goal_year_parent_id表示");
        System.out.println(g.getgoal_year_parent_id());
        st.close();
        con.setAutoCommit(false);//自動コミットモードの無効化
        st2=con.prepareStatement("insert into goal_year_parent values(?,?,?,null,null)");
        System.out.println("insertするよーーー");
        st2.setString(1,goal_id);
        st2.setString(2,g.getgoal_year_parent_id());
        st2.setString(3,user_id);
        int rs_add = st2.executeUpdate();//sql文実施
        st2.close();
        if (rs_add == 0){//sql文が失敗した時
            con.rollback();
            con.setAutoCommit(true);
            con.close();//コネクションを閉じる
            return false;
        }
            con.setAutoCommit(false);
            con.commit();
            con.close();
    }catch(SQLException e){
        System.out.println("リストの取得に失敗");
        return false;
    };
    return true;
    }


    public  Boolean goal_year_child_add(String goal_id,String goal_year_parent_id,String user_id){//親目標追加
        System.out.println("目標情報更新SQL文実施するよー");
        Connection con = getConnection(); //DBに接続
        PreparedStatement st;
        PreparedStatement st2;
        Goal g=new Goal();

    try{
        st=con.prepareStatement("select MAX(child_id)+1 from goal_year_child");
        ResultSet rs = st.executeQuery();
        System.out.println("rs表示");
        System.out.println(rs);
        if(rs.next()){
            System.out.println("goal_yearが空白か確認する");
            g.setgoal_year_child_id(rs.getString("MAX(child_id)+1"));

        }   
        if(g.getgoal_year_child_id() == null){//goal_year_child_idがなければ1を設定する
            g.setgoal_year_child_id("1");
        }

        System.out.println("goal_year_child_id表示");
        System.out.println(g.getgoal_year_child_id());
        st.close();
        con.setAutoCommit(false);//自動コミットモードの無効化
        st2=con.prepareStatement("insert into goal_year_child values(?,?,?,?,null,null)");
        System.out.println("insertするよーーー");
        st2.setString(1,g.getgoal_year_child_id());
        st2.setString(2,goal_year_parent_id);
        st2.setString(3,goal_id);
        st2.setString(4,user_id);
        int rs_add = st2.executeUpdate();//sql文実施
        st2.close();
        if (rs_add == 0){//sql文が失敗した時
            con.rollback();
            con.setAutoCommit(true);
            con.close();//コネクションを閉じる
            return false;
        }
            con.setAutoCommit(false);
            con.commit();
            con.close();
    }catch(SQLException e){
        System.out.println("リストの取得に失敗");
        return false;
    };
    return true;
    }

    public  Boolean goal_achieve_update(String goal_year_child_id){//年間子目標進捗更新
        System.out.println("年間子目標進捗表更新するよー");
        Connection con = getConnection(); //DBに接続
        PreparedStatement st;

    try{
        System.out.println("---goal_year_child_id----");
        System.out.println(goal_year_child_id);
        st=con.prepareStatement("update goal_year_child set achieved_goal = 1 where child_id =?");
        st.setString(1,goal_year_child_id);//子目標id
        int rs_update = st.executeUpdate();//sql文実施
        System.out.println("rs_update");
        System.out.println(rs_update);
        if (rs_update == 0){//sql文が失敗した時
            System.out.println("きたー");
            con.rollback();
            con.close();//コネクションを閉じる
            return false;
        }
            con.setAutoCommit(false);
            con.commit();
            con.close();
    }catch(SQLException e){
        System.out.println("年間目標進捗更新失敗");
        e.printStackTrace();
        return false;
    };
    return true;
    }

    public  Boolean goal_achieve_parent_update(String goal_year_child_id){//年間子目標進捗更新
        System.out.println("年間子目標進捗表更新するよー");
        Connection con = getConnection(); //DBに接続
        Connection con2 = getConnection(); //DBに接続
        PreparedStatement st;
        PreparedStatement st2;
        PreparedStatement st3;

    try{
        System.out.println("---goal_year_child_id----");
        System.out.println(goal_year_child_id);
        st=con.prepareStatement("select parent_id from goal_year_child where achieved_goal is null and parent_id =(select parent_id from goal_year_child where child_id =?)");//親目標を更新するか判断
        st.setString(1,goal_year_child_id);//子目標id
        ResultSet rs = st.executeQuery();
        System.out.println("rs");
        System.out.println(rs);
        /*if (rs == 0){//sql文が失敗した時
            System.out.println("きたー");
            con.rollback();
            con.close();//コネクションを閉じる
            return false;
        }*/
        System.out.println("親目標更新するか判断する");
        if(rs.next()){
            System.out.println("データが返ってきた");
            
        }else{
            st=con.prepareStatement("select parent_id from goal_year_child where child_id = ?");
            st.setString(1,goal_year_child_id);//子目標id
            ResultSet rs2 = st.executeQuery();
            if(rs2.next()){
                System.out.println("データが返ってこなかった!");
                String parent_id = rs2.getString("parent_id");
                System.out.println(parent_id);    
                st=con.prepareStatement("update goal_year_parent set achieved_goals = 100 where parent_id = ?");
                st.setString(1,parent_id);//子目標id
                int rs_update = st.executeUpdate();//sql文実施
            }
        }

            con.setAutoCommit(false);
            con.commit();
            con.close();
    }catch(SQLException e){
        System.out.println("年間目標進捗更新失敗");
        e.printStackTrace();
        return false;
    };
    return true;
    }

    public  Boolean achievement_rate_update(String goal_id,Date date,String Goal_achievement_rate){//年間目標進捗率更新
        System.out.println("年間目標情進捗表更新するよー");
        Connection con = getConnection(); //DBに接続
        PreparedStatement st;

    try{
        st=con.prepareStatement("update achievement_rate_year set goal_rate = ? where goal_id = ? and date >= ?");
        st.setString(1,Goal_achievement_rate);//目標達成回数
        st.setString(2,goal_id);//目標id
        st.setDate(3,date);//目標進捗率
        int rs_update = st.executeUpdate();//sql文実施
        System.out.println("rs_update");
        System.out.println(rs_update);
        if (rs_update == 0){//sql文が失敗した時(今日の日付が最後の日付を超えている場合)
            System.out.println("きたー");
            con.rollback();
            //con.setAutoCommit(true);
            con.close();//コネクションを閉じる
            return false;
        }
            con.setAutoCommit(false);
            con.commit();
            con.close();
    }catch(SQLException e){
        System.out.println("年間目標進捗率更新失敗");
        e.printStackTrace();
        return false;
    };
    return true;
    }
}
