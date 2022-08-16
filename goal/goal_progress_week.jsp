<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../header.html" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="bean.Goal" %>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Date"%>
<%@page import = "bean.User"%>
<%@ page import="java.text.SimpleDateFormat"%>

<td><big><big><big>週間目標進捗</big></big></big></big></td>

<div style="display:inline-flex">
    
<form action="mypage" method="get">
    <p><input type="submit" value="Myページ" formaction="/UNION/login/mypage.jsp"></p>
</form>

<form action="mypage" method="get">
    <p><input type="submit" value="目標進捗"formaction="/UNION/goal/goal_progress.jsp"></p>
</form>

<form action="mypage" method="get">
    <p><input type="submit" value="目標編集" formaction="/UNION/goal/goal_edit.jsp"></p>
</form>

<form action="/UNION/logout/Logout" method="post">
    <p><input type="submit" value="ログアウト"></p>
</form>
</div>

<!--head>

</head!-->
<meta charset="UTF-8">
<link rel="stylesheet" href="button.css">

<p>
<c:forEach var="Goal" items="${week_date}">
    <tr>
        <td> <strong>目標期間</strong>  ${Goal.getgoal_start_date()} ---- ${Goal.getgoal_end_date()}</td>
    </tr>
</c:forEach>
</p>

<p><strong>達成した目標:</strong>
<c:forEach var="Goal" items="${Goal_achievement_list}">
    <tr>
        <td>${Goal.getgoal()}</td>
    </tr>
</c:forEach>
</p>


<form method="post" action="?">
    <strong>未達成目標</strong>
    <c:forEach var="Goal" items="${Goal_not_achievement_list}">
    <tr>
        <input id="marry-f" type="radio" name="goal_week_id" value=${Goal.getgoal_week_id()}>
        <!--td>目標id${Goal.getgoal_week_id()}</td-->
        <td>${Goal.getgoal()}</td>

        <td><strong>残り目標達成回数 </strong>${Goal.getgoal_condition()}</td>
    </tr>
    </c:forEach>
    <p>
        <select name="goal_condition">
        <option value="">目標達成回数を選択してください</option>
        <option value="1">1回</option>
        <option value="2">2回</option>
        <option value="3">3回</option>
        <option value="4">4回</option>
        <option value="5">5回</option>
        <option value="6">6回</option>
        <option value="7">7回</option>
        </select>
         <p><formaction="/UNION/goal/Goal_finish_week></p>
    </p>
    <p><input type="submit" class="btn btn--radius btn--orange" style="position: absolute; left: 0px; top: 685px" name="action" value="finish" formaction="/UNION/goal/Goal_finish_week"></p>
</form>
<%
Date datee = new Date();
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
String toda = dateFormat.format(datee);
Date today = dateFormat.parse(toda);
List<Goal> end_date = (List<Goal>)session.getAttribute("week_date");

Goal g = end_date.get(end_date.size()-1);
Date end_day = dateFormat.parse(g.getgoal_end_date());

if(today.compareTo(end_day) > 0){
    out.println("🚨🚨最後の日付が今日の日付より小さい為、目標を更新できません。目標を作成し直してください。🚨🚨");
}
%>
<c:forEach var="Goal" items="${progress_date}">
    <tr>
        <td>期間  ${Goal.getgoal_progress_date()} </td>
    </tr>
</c:forEach>

<html><head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Sample of calling JFreeChart Servlet to generate PNG chart image</title>
    </head><body>
    <br/><br/>


    <%
    User user = (User)session.getAttribute("user");
    if (user == null){
        String url = "/UNION/goal/session-error.jsp";
        response.sendRedirect(url);
    }else {

    List<Goal> progress_date = (List<Goal>)session.getAttribute("goal_progress_date");

    for (int i = 0; i < progress_date.size(); i++) {
        Goal date = progress_date.get(i);
        //out.println(date.getgoal_progress_date());
    }
    String [][] input = {
            {"1","",""},
            {"1","",""},
            {"1","",""},
            {"1","",""},
    };
    ArrayList<ArrayList<String>> ar1 = new ArrayList<ArrayList<String>>();
    ArrayList<String> tmp = new ArrayList<String>();
    for(int i=0; i<input.length; i++) {
    tmp = new ArrayList<String>();
    tmp.add(input[i][0]);
    tmp.add(input[i][1]);
    tmp.add(input[i][2]);
    ar1.add(tmp);
    }
    session.setAttribute("chart1", ar1);
    }
    %>
    <!--img src="/UNION/goal/Goal_progress_chart2?mode=2" /-->
    <img src="/UNION/goal/Goal_progress_chart2" />
    </body>
</html>




<%@include file="../footer.html" %>