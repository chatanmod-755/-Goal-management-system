package bean;

public class User implements java.io.Serializable{ 

    private String user_id; //ユーザーid
    private String user_name; //ユーザー名
    private String user_password; //パスワード

    public void setUser_id(String user_id){ //ユーザーidを代入
        this.user_id = user_id;
    }

    public void setUser_name(String user_name){ //ユーザー名を代入
        this.user_name = user_name;
    }

    public void setUser_password(String user_password){ //パスワードを代入
        this.user_password = user_password;
    }

    public String getUser_id(){ //ユーザーid取得
        return user_id;
    }

    public String getUser_name(){ //ユーザー名取得
        return user_name;
    }

    public String getUser_password(){ //パスワード取得
        return user_password;
    }
}