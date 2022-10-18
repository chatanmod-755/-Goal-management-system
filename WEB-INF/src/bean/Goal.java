package bean;

public class Goal implements java.io.Serializable{

    private String goal_id;//目標id
    private String goal_week_id;//週間目標id
    private String goal_type;//目標の種類
    private String goal_start_date;//最初の日付
    private String goal_end_date;//最後の日付
    private String goal_month_parent_id;//月間親id
    private String goal_month_child_id;//月間子id
    private String goal_year_parent_id;//年間親id
    private String goal_year_child_id;//年間子id
    private String goal;//目標
    private String goal_condition;//目標達成条件回数
    private String goal_count;//目標達回数
    private String achieved_goal;//月間・年間子目標達成の判定
    private String achievement_rate;//目標達成率
    private String goal_progress_date;//目標進捗の日付
    private String day_of_week;//週間目標の曜日

    public void setgoal_id(String goal_id){ //目標id保持
        this.goal_id = goal_id;
    }

    public void setgoal_week_id(String goal_week_id){ //週間目標id保持
        this.goal_week_id = goal_week_id;
    }

    public void setgoal_type(String goal_type){ //目標の種類保持
        this.goal_type = goal_type;
    }

    public void setgoal_start_date(String goal_start_date){ //最初の日付保持
        this.goal_start_date = goal_start_date;
    }

    public void setgoal_end_date(String goal_end_date){ //最後の日付保持
        this.goal_end_date = goal_end_date;
    }

    public void setgoal_month_parent_id(String parent_id){ //月間親id保持
        this.goal_month_parent_id = parent_id;
    }

    public void setgoal_month_child_id(String child_id){ //月間子id保持
        this.goal_month_child_id = child_id;
    }

    public void setgoal_year_parent_id(String parent_id){ //年間親id保持
        this.goal_year_parent_id = parent_id;
    }

    public void setgoal_year_child_id(String child_id){ //年間子id保持
        this.goal_year_child_id = child_id;
    }

    public void setgoal(String goal){ //目標保持
        this.goal = goal;
    }

    public void setgoal_condition(String goal_condition){ //目標達成条件回数保持
        this.goal_condition = goal_condition;
    }

    public void setgoal_count(String goal_count){ //目標達成回数保持
        this.goal_count = goal_count;
    }

    public void setachieved_goal(String achieved_goal){ //月間・年間子目標達成の判定
        this.achieved_goal = achieved_goal;
    }

    public void setachievement_rate(String achievement_rate){//目標達成率保持
        this.achievement_rate = achievement_rate;
    }

    public void setgoal_progress_date(String goal_progress_date){//目標進捗の日付保持
        this.goal_progress_date = goal_progress_date;
    }

    public void setday_of_week(String day_of_week){//週間目標進捗の曜日保持
        this.day_of_week = day_of_week;
    }

    public String getgoal_id(){ //目標idを取得
        return goal_id;
    }

    public String getgoal_week_id(){ //週間目標idを取得
        return goal_week_id;
    }

    public String getgoal_type(){ //目標の種類を取得
        return goal_type;
    }

    public String getgoal_start_date(){ //最初の日付を取得
        return goal_start_date;
    }

    public String getgoal_end_date(){ //最後の日付を取得
        return goal_end_date;
    }

    public String getgoal_month_parent_id(){ //月間目標親idを取得
        return goal_month_parent_id;
    }

    public String getgoal_month_child_id(){ //月間目標子idを取得
        return goal_month_child_id;
    }

    public String getgoal_year_parent_id(){ //年間目標親idを取得
        return goal_year_parent_id;
    }

    public String getgoal_year_child_id(){ //年間目標子idを取得
        return goal_year_child_id;
    }

    public String getgoal(){ //目標を取得
        return goal;
    }

    public String getgoal_condition(){ //目標達成条件回数を取得
        return goal_condition;
    }

    public String getgoal_count(){ //目標達回数を取得
        return goal_count;
    }

    public String get_achieved_goal(){ //月間・年間子目標達成の取得
        return achieved_goal;
    }

    public String get_achievement_rate(){ //目標達成率の取得
        return achievement_rate;
    }

    public String getgoal_progress_date(){ //進捗の目標日付を取得
        return goal_progress_date;
    }

    public String getday_of_week(){ //週間目標進捗の曜日を取得
        return day_of_week;
    }
}