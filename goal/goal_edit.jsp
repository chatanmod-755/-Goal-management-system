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
<td><big><big><big>目標編集</big></big></big></big></td>

<div style="display:inline-flex">
    
<form action="mypage" method="get">
    <p><input type="submit" value="Myページ" formaction="/UNION/login/mypage.jsp"></p>
</form>

<form action="mypage" method="get">
    <p><input type="submit" value="目標進捗"formaction="/UNION/goal/goal_progress.jsp"></p>
</form>

<form action="/UNION/logout/Logout" method="post">
    <p><input type="submit" value="ログアウト"></p>
</form>
</div>

<meta charset="UTF-8">
<link rel="stylesheet" href="button.css">

<form action="goal_create.jsp" method="get">
    <p><input type="submit" class="btn btn--radius btn--orange" style="position: absolute; left: 0px; top: 630px" name="action" value="create"></p>
</form>

<form method="post" action="?">
    <c:forEach var="Goal" items="${list}">
    <tr>
        <input id="marry-f" type="radio" name="goal_id" value=${Goal.getgoal_id()}>
        <!--td>目標id${Goal.getgoal_id()}</td-->
        <!--td>目標タイプ${Goal.getgoal_type()}</td-->
        <c:if test = "${Goal.getgoal_type() == '1'}">
            週間目標
        </c:if>
        <c:if test = "${Goal.getgoal_type() == '2'}">
            月間目標
        </c:if>
        <c:if test = "${Goal.getgoal_type() == '3'}">
            年間目標
        </c:if>
        <td><font color="red">期間 : </font>${Goal.getgoal_start_date()} ~~ ${Goal.getgoal_end_date()}</td>
    </tr>
    </c:forEach>
    <p><input type="submit" class="btn btn--radius btn--orange" style="position: absolute; left: 0px; top: 680px" name="action" value="edit" formaction="/UNION/goal/Goal_edit_contlloer"></p>
    <p><input type="submit" class="btn btn--radius btn--orange" style="position: absolute; left: 0px; top: 730px" name="action" value="delete" formaction="/UNION/goal/Goal_delete"></p>
</form>
<%@include file="../footer.html" %>