package dao;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.io.IOException;

public class DAO{
    static DataSource ds;
    Connection connection;
    public Connection getConnection(){
        try{
            if(ds == null ){ //データベースを1度接続しているか確認
                InitialContext ic = new InitialContext();//データベース情報を取得する為にインスタンス生成
                ds = (DataSource)ic.lookup("java:/comp/env/jdbc/unionn");//DBにアクセス
            }
                connection = ds.getConnection();
        }catch (NamingException e){
            try{
                ds.getConnection().close();//接続できなかったのでコネクションを閉じる
            }catch (SQLException s){
                s.printStackTrace();
            };
        }catch (SQLException e){
            try{
                ds.getConnection().close();//接続できなかったのでコネクションを閉じる
            }catch (SQLException s){
                s.printStackTrace();
            };    
        };

        return connection;//接続情報を返す
    }
}