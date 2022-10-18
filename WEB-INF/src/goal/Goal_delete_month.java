package goal;

import java.util.List;
import java.util.ArrayList;
import dao.Delete_month_DAO;
import dao.Goal_select_month_DAO;
import bean.Goal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Goal_delete_month{
    public Boolean delete(String goal_id) throws Exception{ //月間目標削除
        Goal_select_month_DAO dao = new Goal_select_month_DAO();
        Delete_month_DAO del = new Delete_month_DAO();
        String goal_month_parent_id = dao.check_parent(goal_id);//親目標idを取得

        if(goal_month_parent_id != null){//親目標にデータがあるか判定
            if(dao.check_child(goal_id)){//子目標にデータがあるか判定
                if(del.child_del_all(goal_id) && del.parent_del_all(goal_id) && del.goal_del(goal_id)){//子・親目標・月間目標削除
                    return true;
                }else{
                    return false;
                }
            }else{//親目標・月間目標削除
                if(del.parent_del_all(goal_id) && del.goal_del(goal_id)){
                    return true;
                }else{
                    return false;
                }
            }
        }else{
            if(del.goal_del(goal_id)){//月間目標削除
                return true;
            }else{
                return false;
            }
        }
    }
}