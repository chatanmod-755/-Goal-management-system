package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;

public class Goal_deleteDAO_week extends DAO{
    public Boolean delete(String goal_id) throws Exception{ //goal_idをDBと照合

    Connection con = getConnection(); //DBに接続
    PreparedStatement st;
    PreparedStatement st2;


    try{
        System.out.println("sql実施");
        System.out.println(goal_id);
        st=con.prepareStatement("select * from goal_week where goal_id=?"); //週間目標DBに目標idが合致しているか検索
        st.setString(1,goal_id);
        ResultSet rs_check = st.executeQuery(); //sql文実施
        if(rs_check.next()){//目標を作成していたら、削除。
            System.out.println("goal_week削除しにきた");
            st=con.prepareStatement("delete from goal_week where goal_id=?"); 
            st.setString(1,goal_id);
            int rs_weekdel = st.executeUpdate();//sql文実施
        }
        st=con.prepareStatement("delete from achievement_rate_week where goal_id=?"); 
        st.setString(1,goal_id);
        int rs_week_ratedel = st.executeUpdate();//sql文実施
        st2=con.prepareStatement("delete from goal_create where goal_id=?"); //目標DBにユーザーidが合致しているか検索
        st2.setString(1,goal_id); //入力されたユーザー名
        int rs_goal_del = st2.executeUpdate(); //sql文実施

        if (rs_goal_del == 0){//sql文が失敗した時
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
            st2.close();
            con.close();
            return true;
        }
    }catch(SQLException e){
        System.out.println("週間削除に失敗しました。");
        e.printStackTrace();
        return false;
    }
    }
}