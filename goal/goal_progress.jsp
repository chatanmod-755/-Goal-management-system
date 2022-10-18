<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../header.html" %>
<%@ page import="bean.Goal" %>
<%@ page import="java.util.List"%>
<%@page import = "bean.User"%>

<%
//セッション有無確認
User user = (User)session.getAttribute("user");
if (user == null){
    String url = "/UNION/goal/session-error.jsp";
    response.sendRedirect(url);
}
%>
<td><big><big><big>目標進捗</big></big></big></big></td>

<div style="display:inline-flex">
    
<form action="mypage" method="get">
    <p><input type="submit" value="Myページ" formaction="/UNION/login/mypage.jsp"></p>
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

<form method="post" action="?">
    <c:forEach var="Goal" items="${list}">
    <tr>
        <input id="marry-f" type="radio" name="goal_id" value=${Goal.getgoal_id()}>
        <!--td>目標id${Goal.getgoal_id()}</td-->
        <!--td>目標タイプ${Goal.getgoal_type()}</td-->
        <c:if test = "${Goal.getgoal_type() == '1'}">
            <!--font size="2"-->週間目標<!--/font-->
        </c:if>
        <c:if test = "${Goal.getgoal_type() == '2'}">
            <!--font size="2"-->月間目標<!--/font-->
        </c:if>
        <c:if test = "${Goal.getgoal_type() == '3'}">
            <!--font size="2"-->年間目標<!--/font-->
        </c:if>
        <%
        List<Goal> id_tmp = (List<Goal>)session.getAttribute("list");
        Goal g = id_tmp.get(0);
        String id = g.getgoal_id();
        if(id == "1"){
            out.print("週間目標");
        }
        %>
        <td><font color="red">期間 : </font>${Goal.getgoal_start_date()} ~~ ${Goal.getgoal_end_date()} </td>
    </tr>
    </c:forEach>
    <p><input type="submit" class="btn btn--radius btn--orange" style="position: absolute; left: 0px; top: 630px" name="action" value="edit" formaction="/UNION/goal/Goal_progress_contlloer"></p>
</form>
<%@include file="../footer.html" %>