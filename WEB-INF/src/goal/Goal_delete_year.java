package goal;

import dao.Delete_year_DAO;
import dao.Goal_select_year_DAO;
import bean.Goal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Goal_delete_year {
    public Boolean delete(String goal_id) throws Exception{

        Goal_select_year_DAO dao = new Goal_select_year_DAO();
        Delete_year_DAO del = new Delete_year_DAO();

        System.out.println("年間目標select文sql実施");
        System.out.println(goal_id);
        String goal_year_parent_id = dao.check_parent(goal_id);//親目標idを取得
        if(goal_year_parent_id != null){//親目標にデータがあるか判定
            if(dao.check_child(goal_year_parent_id)){//子目標にデータがあるか判定
                System.out.println("子目標にデータあるので削除しにいきます");
                if(del.child_del_all(goal_id) && del.parent_del_all(goal_id) && del.goal_del(goal_id)){
                    return true;
                }else{
                    return false;
                }
                /*del.child_del_all(goal_id);//親目標idをもとに子目標を削除
                del.parent_del_all(goal_id);//年間目表からgoal_idをもとに親目標を削除
                del.goal_del(goal_id);//Goal_create表から目標を削除
                System.out.println("年間目標削除成功");*/
            }else{//親目標を削除
                System.out.println("親目標削除成功");
                if(del.parent_del_all(goal_id) && del.goal_del(goal_id)){
                    return true;
                }else{
                    return false;
                }
                //del.parent_del_all(goal_id);//年間目表から親目標を削除
                //del.goal_del(goal_id);//Goal_create表から目標を削除
            }
        }else{//親目標がなければGoal_create表から削除。
            System.out.println("Goal_create表の年間目標を削除します");
            if(del.goal_del(goal_id)){
                return true;
            }else{
                return false;
            }
            //del.goal_del(goal_id);//Goal_create表から目標を削除
        }
    }
}