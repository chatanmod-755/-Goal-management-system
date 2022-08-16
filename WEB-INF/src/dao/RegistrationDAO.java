package dao;

import bean.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;

public class RegistrationDAO extends DAO{
    
    public boolean insert(String user_id,String user_name,String user_password){ //ユーザーとパスワード入力をDBと照合
        //int rs = 0;
        Connection con = getConnection(); //DBに接続
        PreparedStatement st;
        try{
            con.setAutoCommit(false);//自動コミットモードの無効化
            st=con.prepareStatement("insert into user values (?, ?, ?)"); //userDBにユーザー名・パスワードを登録
            st.setString(1,user_id); //入力されたユーザーid
            st.setString(2,user_name); //入力されたユーザーid
            String hashed = BCrypt.hashpw(user_password, BCrypt.gensalt());//パスワードをハッシュ化
            st.setString(3,hashed); //入力されたパスワード
            int rs = st.executeUpdate(); //更新行数を返却
            System.out.println("sql実行");
            st.close();
            
            if (rs == 0){//sql文が失敗した時
                con.rollback();
                con.setAutoCommit(true);
                con.close();//コネクションを閉じる
                return false;
            }
            con.setAutoCommit(false);
            con.commit();
            con.close();
        }catch (SQLException e){
            System.out.println("ユーザーidもしくは、ユーザー名が重複しています。");
            e.printStackTrace();
            return false;
        };
        return true;//userの情報を返す
    }
}