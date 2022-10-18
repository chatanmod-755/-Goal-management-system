package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;

public class Goal_deleteDAO_week extends DAO{
    public Boolean delete(String goal_id) throws Exception{//目標idに紐づく週間進捗・週間目標表の目標情報を削除。
    Connection con = getConnection(); //DBに接続
    PreparedStatement st;
    PreparedStatement st2;
    try{
        st=con.prepareStatement("select * from goal_week where goal_id=?"); //週間目標表に目標idが合致しているか検索
        st.setString(1,goal_id);
        ResultSet rs_check = st.executeQuery(); //sql文実施

        if(rs_check.next()){//目標を作成していたら、削除。
            st=con.prepareStatement("delete from goal_week where goal_id=?");//週目標表の目標を削除
            st.setString(1,goal_id);
            int rs_weekdel = st.executeUpdate();//sql文実施
        }
        st=con.prepareStatement("delete from achievement_rate_week where goal_id=?");//週目標表の進捗率表を削除
        st.setString(1,goal_id);
        int rs_week_ratedel = st.executeUpdate();//sql文実施
        st2=con.prepareStatement("delete from goal_create where goal_id=?"); //目標表の目標を削除
        st2.setString(1,goal_id);
        int rs_goal_del = st2.executeUpdate(); //sql文実施

        if ((rs_goal_del == 0) && (rs_week_ratedel == 0)){//sql文が失敗した時
            con.rollback();
            con.setAutoCommit(true);
            con.close();
            return false;
        }else{
            con.setAutoCommit(false);
            con.commit();
            st.close();
            st2.close();
            con.close();
            return true;
        }
    }catch(SQLException e){
        e.printStackTrace();
        return false;
    }
    }
}