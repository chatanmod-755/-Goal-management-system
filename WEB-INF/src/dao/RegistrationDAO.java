package dao;

import bean.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;

public class RegistrationDAO extends DAO{//ユーザー登録
    public boolean insert(String user_id,String user_name,String user_password){ 
        Connection con = getConnection(); //DBに接続
        PreparedStatement st;
        try{
            con.setAutoCommit(false);
            st=con.prepareStatement("insert into user values (?, ?, ?)");
            st.setString(1,user_id); //入力されたユーザーid
            st.setString(2,user_name); //入力されたユーザー名
            String hashed = BCrypt.hashpw(user_password, BCrypt.gensalt());//パスワードをハッシュ化
            st.setString(3,hashed); //入力されたパスワード
            int rs = st.executeUpdate(); //更新行数を返却
            st.close();
            
            if (rs == 0){//sql文が失敗した時
                con.rollback();
                con.setAutoCommit(true);
                con.close();
                return false;
            }
            con.setAutoCommit(false);
            con.commit();
            con.close();
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        };
        return true;
    }
}