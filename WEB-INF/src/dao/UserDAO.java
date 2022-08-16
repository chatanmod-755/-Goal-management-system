package dao;

import bean.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;

public class UserDAO extends DAO{
    
    public User search(String user_id, String user_password){ //ユーザーとパスワード入力をDBと照合
        User user = null;
        User user_name2;
        Connection con = getConnection(); //DBに接続

        PreparedStatement st;
        try{
            st=con.prepareStatement("select * from user where user_id=?"); //userDBにユーザーidが合致しているか検索
            st.setString(1,user_id); //入力されたユーザー名
            System.out.println("クエリ文発射。");
            ResultSet rs = st.executeQuery(); //sql文実施

            while(rs.next()){ //sql文が帰ってきたらtrue
                System.out.println("ハッシュ認証開始");
                String hased_password = rs.getString("user_password");//DBからハッシュ化されたパスワードを取得。
                if(BCrypt.checkpw(user_password,hased_password)){
                    user = new User(); //userインスタンス生成
                    user.setUser_id(rs.getString("user_id")); //DBから取得したユーザーidをUser_idに設定。
                    user.setUser_name(rs.getString("user_name"));//DBから取得したユーザー名をUser_nameに設定。
                    System.out.println("ハッシュ認証成功");
                }
            }
            st.close();
            con.close();//コネクションを閉じる
        }catch (SQLException e){
            System.out.println("クエリ文が失敗しました。");
            e.printStackTrace();
        };
            return user;//userの情報を返す
    }
}