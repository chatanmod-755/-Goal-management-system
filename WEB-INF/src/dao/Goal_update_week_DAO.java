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

public class Goal_update_week_DAO extends DAO{
    public  Boolean condition_update(String goal_week_id, String goal_condition){//目標達成条件回数更新
        //List<Goal> list=new ArrayList<>();
        Connection con = getConnection(); //DBに接続
        PreparedStatement st;

    try{
        st=con.prepareStatement("update goal_week set goal_condition=? where goal_week_id=?");
        st.setString(1,goal_condition);
        st.setString(2,goal_week_id);
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
        e.printStackTrace();
        return false;
        
    };
    return true;
    }

    public  Boolean goal_rename(String goal_week_id,String goal_rename){//目標情報更新
        System.out.println("目標情報更新SQL文実施するよー");
        System.out.println(goal_rename);
        Connection con = getConnection(); //DBに接続
        PreparedStatement st;

    try{
        st=con.prepareStatement("update goal_week set goal=? where goal_week_id=?");
        st.setString(1,goal_rename);
        st.setString(2,goal_week_id);
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
        e.printStackTrace();
        return false;
    };
    return true;
    }

    public  Boolean goal_delete(String goal_week_id){//目標削除
        System.out.println("目標情報更新SQL文実施するよー");
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
            System.out.println("目標削除失敗");
            return false;
        }else{
            System.out.println("目標削除成功");
            con.setAutoCommit(false);
            con.commit();
            con.close();
            return true;
        }
    }catch(SQLException e){
        System.out.println("削除に失敗");
        e.printStackTrace();
        return false;
    }
    }

    public  Boolean goal_add(String goal_id,String user_id){//目標追加
        System.out.println("目標情報更新SQL文実施するよー");
        Connection con = getConnection(); //DBに接続
        PreparedStatement st;
        PreparedStatement st2;
        Goal g=new Goal();

    try{
        st=con.prepareStatement("select MAX(goal_week_id)+1 from goal_week");
        ResultSet rs = st.executeQuery();
        System.out.println("rs表示");
        System.out.println(rs);
        if(rs.next()){
            System.out.println("goal_weekが空白か確認する");
            g.setgoal_week_id(rs.getString("MAX(goal_week_id)+1"));

        }   
        if(g.getgoal_week_id() == null){//goal_week_idがなければ1を設定する
            g.setgoal_week_id("1");
        }

        System.out.println("goal_week_id表示");
        System.out.println(g.getgoal_week_id());
        st.close();
        con.setAutoCommit(false);//自動コミットモードの無効化
        st2=con.prepareStatement("insert into goal_week values(?,?,?,1,null,0,0,0)");
        System.out.println("insertするよーーー");
        st2.setString(1,goal_id);
        st2.setString(2,g.getgoal_week_id());
        st2.setString(3,user_id);
        int rs_add = st2.executeUpdate();//sql文実施
        st2.close();
        if (rs_add == 0){//sql文が失敗した時
            con.rollback();
            con.setAutoCommit(true);
            con.close();//コネクションを閉じる
            return false;
        }
            con.commit();
            con.setAutoCommit(true);
            con.close();
    }catch(SQLException e){
        System.out.println("リストの取得に失敗");
        return false;
    };
    return true;
    }

    public  Boolean goal_achieve_update(String goal_count,String goal_week_id){//週間目標進捗更新
        System.out.println("週間目標情進捗表更新するよー");
        Connection con = getConnection(); //DBに接続
        PreparedStatement st;
        String max_goal_condition = "0";

    try{
        System.out.println("----goal_count---");
        System.out.println(goal_count);
        System.out.println("---goal_week_id----");
        System.out.println(goal_week_id);
        //st=con.prepareStatement("update goal_week set goal_count = goal_count+? where goal_week_id = ?");
        st=con.prepareStatement("update goal_week set goal_count = goal_count+? where goal_week_id = ? and goal_condition >= goal_count+?");
        st.setString(1,goal_count);//目標達成回数
        st.setString(2,goal_week_id);//週間目標id
        st.setString(3,goal_count);//週間目標id
        int rs_update = st.executeUpdate();//sql文実施
        System.out.println("rs_update");
        System.out.println(rs_update);
        if (rs_update == 0){
            System.out.println("きたー");
            st=con.prepareStatement("select max(goal_condition) from goal_week where goal_week_id =?");
            st.setString(1,goal_week_id);//週間目標id
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                max_goal_condition = rs.getString("max(goal_condition)"); 
            }
            st=con.prepareStatement("update goal_week set goal_count = ? where goal_week_id = ?");
            st.setString(1,max_goal_condition);//週間目標id
            st.setString(2,goal_week_id);//週間目標id
            int rs_add = st.executeUpdate();//sql文実施
            con.setAutoCommit(false);
            con.commit();
            //con.setAutoCommit(true);
            con.close();//コネクションを閉じる
            return true;
        }
            con.setAutoCommit(false);
            con.commit();
            con.close();
    }catch(SQLException e){
        System.out.println("週間目標進捗更新失敗");
        e.printStackTrace();
        return false;
    };
    return true;
    }

    public  Boolean achievement_rate_update(String goal_id,Date date,String Goal_achievement_rate){//週間目標進捗率更新
        System.out.println("週間目標情進捗表更新するよー");
        Connection con = getConnection(); //DBに接続
        PreparedStatement st;

    try{
        st=con.prepareStatement("update achievement_rate_week set goal_rate = ? where goal_id = ? and date >= ?");
        st.setString(1,Goal_achievement_rate);//目標達成回数
        st.setString(2,goal_id);//目標id
        st.setDate(3,date);//目標進捗率
        int rs_update = st.executeUpdate();//sql文実施
        System.out.println("rs_update");
        System.out.println(rs_update);
        if (rs_update == 0){//sql文が失敗した時(今日の日付が最後の日付を超えている場合)
            System.out.println("きたー");
            con.setAutoCommit(false);
            con.commit();
            //con.rollback();
            //con.setAutoCommit(true);
            con.close();//コネクションを閉じる
            return false;
        }
            con.setAutoCommit(true);
            con.commit();
            con.close();
    }catch(SQLException e){
        System.out.println("週間目標進捗率更新失敗");
        e.printStackTrace();
        return false;
    };
    return true;
    }
}
