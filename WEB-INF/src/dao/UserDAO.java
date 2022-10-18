package dao;

import bean.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import org.mindrot.jbcrypt.BCrypt;
import javax.sql.DataSource;
import javax.naming.InitialContext;

public class UserDAO extends DAO{
    public User search(String user_id, String user_password){ //ユーザーとパスワードが合致していればユーザー情報を返す
        User user = null;
        User user_name2;
        try{
            PreparedStatement st;
            InitialContext ic=new InitialContext();
            DataSource ds=(DataSource)ic.lookup("java:/comp/env/jdbc/unionn");
            Connection con = ds.getConnection(); //DBに接続
            st=con.prepareStatement("select * from user where user_id=?");
            st.setString(1,user_id); //入力されたユーザー名
            ResultSet rs = st.executeQuery(); //sql文実施

            while(rs.next()){ //sql文が帰ってきたらtrue
                String hased_password = rs.getString("user_password");//DBからハッシュ化されたパスワードを取得。
                if(BCrypt.checkpw(user_password,hased_password)){
                    user = new User(); //userインスタンス生成
                    user.setUser_id(rs.getString("user_id")); //DBから取得したユーザーidをUser_idに設定。
                    user.setUser_name(rs.getString("user_name"));//DBから取得したユーザー名をUser_nameに設定。
                }
            }
            st.close();
            con.close();//コネクションを閉じる
        }catch (SQLException |  NamingException e){
            e.printStackTrace();
        };
            return user;//userの情報を返す
    }
}