<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.html" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import = "bean.User"%>
<%
User user = (User)session.getAttribute("user");
if (user == null){
    String url = "/UNION/goal/session-error.jsp";
    response.sendRedirect(url);
}
%>

<td><big><big><big>Myページ</big></big></big></big></td>

<p>こんにちは ${user.user_name}さん</p>
<form action="/UNION/logout/Logout" method="post">
    <p><input type="submit" value="ログアウト" style="position: absolute; right: 0px; top: 0px"/></p>
</form>
<form action="/UNION/goal/Goal_progress" method="post">
    <p><input type="submit" value="目標進捗"></p>
</form>
<form action="/UNION/goal/Goal_edit" method="post">
    <p><input type="submit" value="目標編集"></p>
</form>
</form>
<!--abel for="file">進捗率:</label>
<progress id="file" max="100" value="70"> 70% </progress>
<p>70%</p-->

<%@include file="../footer.html" %>