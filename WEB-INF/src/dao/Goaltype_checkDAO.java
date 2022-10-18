package dao;

import bean.Goal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Goaltype_checkDAO extends DAO{
    public String check_type(String goal_id) throws Exception{ //目標の種類を返す
    String goal_type = null;//初期化

    try{
        Connection con = getConnection(); //DBに接続
        PreparedStatement st;
        st = con.prepareStatement("select goal_type from goal_create where goal_id=?"); 
        st.setString(1,goal_id); //入力されたユーザー名
        ResultSet rs_goal_type = st.executeQuery(); //sql文実施
        
        while(rs_goal_type.next()){
            Goal g = new Goal();//goalインスタンス生成
            g.setgoal_type(rs_goal_type.getString("goal_type"));//取得した親目標idをgoalインスタンスに設定
            goal_type = g.getgoal_type();//親idを代入
        }
        st.close();
        con.close();//コネクションを閉じる
        return goal_type;
    }catch(SQLException e){
        e.printStackTrace();
        return null;
    }
    }
}
