<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../header.html" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="bean.Goal" %>
<%@ page import="java.util.List"%>
<%@page import = "bean.User"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>


<td><big><big><big>年間目標進捗</big></big></big></big></td>

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

<meta charset="UTF-8">
<link rel="stylesheet" href="button.css">

<p>
<c:forEach var="Goal" items="${week_date}">
    <tr>
        <td> <strong> 目標期間 </strong> ${Goal.getgoal_start_date()} ---- ${Goal.getgoal_end_date()}</td>
    </tr>
</c:forEach>
</p>

<strong>達成した親目標</strong>
<c:forEach var="Goal" items="${Goal_achievement_parent_list}">
    <tr>
        <td>${Goal.getgoal()}</td>
    </tr>
</c:forEach>

<p><strong>達成した子目標</strong>
<c:forEach var="Goal" items="${Goal_achievement_child_list}">
    <tr>
        <td>${Goal.getgoal()}</td>
    </tr>
</c:forEach>
</p>

<strong>未達成の親目標</strong>
<form method="post" action="?">
    <c:forEach var="Goal" items="${Goal_not_achievement_parent_list}">
    <tr>
        <!--td>目標id${Goal.getgoal_month_parent_id()}</td-->
        <td>目標${Goal.getgoal()}  </td>
    </tr>
    </c:forEach>
    <p><input type="submit" class="btn btn--radius btn--orange" style="position: absolute; left: 0px; top: 630px" name="action" value="edit" formaction="/UNION/goal/Goal_edit_contlloer"></p>
</form>

<strong>未達成の子目標</strong>
<form method="post" action="?">
    <c:forEach var="Goal" items="${Goal_not_achievement_child_list}">
    <tr>
        <input id="marry-f" type="radio" name="goal_year_child_id" value=${Goal.getgoal_year_child_id()}>
        <!--td>目標id${Goal.getgoal_month_child_id()}</td-->
        <td>${Goal.getgoal()}  </td>
    </tr>
    </c:forEach>
    <p><input type="submit" class="btn btn--radius btn--orange" style="position: absolute; left: 0px; top: 630px" name="action" value="finish" formaction="/UNION/goal/Goal_finish_year"></p>
</form>

<%
Date datee = new Date();
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
String toda = dateFormat.format(datee);
Date today = dateFormat.parse(toda);
List<Goal> end_date = (List<Goal>)session.getAttribute("year_date");

Goal g = end_date.get(end_date.size()-1);
Date end_day = dateFormat.parse(g.getgoal_end_date());

if(today.compareTo(end_day) > 0){
    out.println("🚨🚨最後の日付が今日の日付より小さい為、目標を更新できません。目標を作成し直してください。🚨🚨");
}
%>

<html><head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Sample of calling JFreeChart Servlet to generate PNG chart image</title>
    </head><body>
    <br/><br/>
    <%
    //セッション有無確認
    User user = (User)session.getAttribute("user");
    if (user == null){
        String url = "/UNION/goal/session-error.jsp";
        response.sendRedirect(url);
    }else{
    List<Goal> progress_date = (List<Goal>)session.getAttribute("goal_progress_date");

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
    <img src="/UNION/goal/Goal_progress_chart2" />
    </body>
</html>

<%@include file="../footer.html" %>