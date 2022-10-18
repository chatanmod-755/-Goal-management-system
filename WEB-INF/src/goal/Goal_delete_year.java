package goal;

import dao.Delete_year_DAO;
import dao.Goal_select_year_DAO;
import bean.Goal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Goal_delete_year {
    public Boolean delete(String goal_id) throws Exception{//年間目標削除
        Goal_select_year_DAO dao = new Goal_select_year_DAO();
        Delete_year_DAO del = new Delete_year_DAO();
        String goal_year_parent_id = dao.check_parent(goal_id);//親目標idを取得

        if(goal_year_parent_id != null){//親目標にデータがあるか判定
            if(dao.check_child(goal_id)){//子目標にデータがあるか判定
                if(del.child_del_all(goal_id) && del.parent_del_all(goal_id) && del.goal_del(goal_id)){//子・親・年間目標削除
                    return true;
                }else{
                    return false;
                }
            }else{
                if(del.parent_del_all(goal_id) && del.goal_del(goal_id)){//親・年間目標削除
                    return true;
                }else{
                    return false;
                }
            }
        }else{
            if(del.goal_del(goal_id)){//年間目標削除
                return true;
            }else{
                return false;
            }
        }
    }
}