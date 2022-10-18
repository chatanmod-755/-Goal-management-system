<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../header.html" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import = "bean.User"%>

<%
//セッション有無確認
User user = (User)session.getAttribute("user");
if (user == null){
    String url = "/UNION/goal/session-error.jsp";
    response.sendRedirect(url);
}
%>

<td><big><big><big>週間目標</big></big></big></big></td>

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
        <td><strong>目標期間 </strong>${Goal.getgoal_start_date()} ---- ${Goal.getgoal_end_date()}</td>
    </tr>
</c:forEach>
</p>

<form method="post" action="?">
    <c:forEach var="Goal" items="${week_list}">
    <tr>
        <input id="marry-f" type="radio" name="goal_week_id" value=${Goal.getgoal_week_id()}>
        <!--td>目標id${Goal.getgoal_week_id()}</td-->
        <td>目標名 : ${Goal.getgoal()}</td>
        <td>目標達成条件回数  ${Goal.getgoal_condition()}</td>
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
         <p><formaction="/UNION/goal/Goal_edit_week"></p>
    </p>
    <p>目標名変更<input type="text" name="goal_rename"></p>
    <p><input type="submit" class="btn btn--radius btn--orange" style="position: absolute; left: 0px; top: 630px" name="goal_add" value="goal_add" formaction="/UNION/goal/Goal_add_week"></p>
    <p><input type="submit" class="btn btn--radius btn--orange" style="position: absolute; left: 0px; top: 680px" value="goal_update" formaction="/UNION/goal/Goal_update_week"></p>
    <p><input type="submit" class="btn btn--radius btn--orange" style="position: absolute; left: 0px; top: 730px" name="goal_delete" value="goal_delete" formaction="/UNION/goal/Goal_delete_week"></p>
</form>

    

<%@include file="../footer.html" %>