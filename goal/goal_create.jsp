<%@page contentType="text/html; charset=UTF-8" %>
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

<td><big><big><big>目標作成</big></big></big></big></td>
<p>こんにちは${user.user_name}さん</p>
<div style="display:inline-flex">

<form action="目標編集のjava" method="post">
    <p><input type="submit" tyle="position: absolute; left: 0px; top: 100px" value="目標編集"></p>
</form>
</div>

<div style="display:inline-flex">
<form action="Goal_create" method="post">
<select name="goal_type" style="position: absolute; left: 0px; top: 100px">
    <option>選択してください</option>
    <option value="1" name="goal_type">週間</option>
    <option value="2" name="goal_type">月間</option>
    <option value="3" name="goal_type">年間</option>
</select>

<label for="start"style="position: absolute; left: 150px; top: 80px" >始まりの日付</label>
<input type="date" name="start"
    style="position: absolute; left: 150px; top: 100px"
    min="2022-03-01" max="2030-03-01">

<label for="start"style="position: absolute; left: 300px; top: 80px">終わりの日付</label>
<input type="date" name="end" style="position: absolute; left: 300px; top: 100px"
    min="2022-03-01" max="2030-03-01">
</div>

<meta charset="UTF-8">
<link rel="stylesheet" href="button.css">
    <p><input type="submit" class="btn btn--radius btn--orange" style="position: absolute; right: 0px; top: 730px" value="保存"></p>
</form>

<%@include file="../footer.html" %>