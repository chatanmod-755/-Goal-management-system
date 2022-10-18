<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.html" %>

<form action="/UNION/login/Login" method="post">
    <h1>目標管理システム</h1>
    <p>ユーザーID<input type="text" name="user_id"></p>
    <p>パスワード<input type="password" name="user_password"></p>
    <p><input type="submit" value="ログイン"></p>
</form>

<%@include file="../footer.html" %>