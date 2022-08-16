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
    private static final Logger logger = Logger.getLogger(DAO.class.getName());//DAOクラスからログ取得
    static DataSource ds;
    Connection connection;
    public Connection getConnection(){
        try{
            /*Handler handler = new FileHandler("/opt/tomcat/webapps/UNION/WEB-INF/classes/dao/DAOLog.log", true);//ログが保存されるログファイル名を設定。(追記モード)
            logger.addHandler(handler);
            handler.setFormatter(new java.util.logging.SimpleFormatter());//コンソールと同じ形式の出力方法に変換
            logger.setLevel(Level.SEVERE);
            */
            if(ds == null ){ //データベースを1度接続しているか確認
                InitialContext ic = new InitialContext();//データベース情報を取得する為にインスタンス生成
                ds = (DataSource)ic.lookup("java:/comp/env/jdbc/unionn");//DBにアクセス
            }
            connection = ds.getConnection();//接続情報を返す
        }catch (NamingException e){
            try{
                //logger.log(Level.SEVERE, e.toString(), e);//NamingExceptionのエラーログをDAOLogに保存
                ds.getConnection().close();//接続できなかったのでコネクションを閉じる
            }catch (SQLException k){
                    //logger.log(Level.SEVERE, k.toString(), k);//SQLExceptionのエラーログをDAOLogに保存
                    };
                
        }catch (SQLException e){
            try{
                //logger.log(Level.SEVERE, e.toString(), e);//SQLExceptionエラーログをDAOLogに保存
                ds.getConnection().close();//接続できなかったのでコネクションを閉じる
            }catch (SQLException k){
                //logger.log(Level.SEVERE, k.toString(), k);//SQLExceptionエラーログをDAOLogに保存
                };    
        /*}catch (IOException e){
                logger.log(Level.SEVERE, e.toString(), e);//SQLExceptionエラーログをDAOLogに保存
                */
        };
        return connection;
    }
}