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

<td><big><big><big>年間目標編集</big></big></big></big></td>

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
<c:forEach var="Goal" items="${year_date}">
    <tr>
        <td><strong>目標期間 </strong>${Goal.getgoal_start_date()} ---- ${Goal.getgoal_end_date()}</td>
    </tr>
</c:forEach>
</p>

<form method="post" action="?">
    <c:forEach var="Goal" items="${year_parent_all_list}">
    <tr>
        <input id="marry-f" type="radio" name="goal_year_parent_id" value=${Goal.getgoal_year_parent_id()}>
        <!--td>目標id${Goal.getgoal_year_parent_id()}</td-->
        <td> <strong>目標名 : </strong>${Goal.getgoal()}</td>
    </tr>
    </c:forEach>
    <p>目標名変更<input type="text" name="goal_rename"></p>
    <p><input type="submit" class="btn btn--radius btn--orange" style="position: absolute; left: 0px; top: 580px" name="edit" value="edit" formaction="/UNION/goal/Goal_edit_year_child"></p>
    <p><input type="submit" class="btn btn--radius btn--orange" style="position: absolute; left: 0px; top: 630px" name="goal_add" value="goal_add" formaction="/UNION/goal/Goal_add_year_parent"></p>
    <p><input type="submit" class="btn btn--radius btn--orange" style="position: absolute; left: 0px; top: 680px" value="goal_rename" formaction="/UNION/goal/Goal_rename_year_parent"></p>
    <p><input type="submit" class="btn btn--radius btn--orange" style="position: absolute; left: 0px; top: 730px" name="goal_delete" value="goal_delete" formaction="/UNION/goal/Goal_delete_year_parent"></p>
</form>

<%@include file="../footer.html" %>