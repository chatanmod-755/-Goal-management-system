<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.html" %>

<form action="Registration" method="post">
    <h1>会員登録</h1>
    <p>ユーザーID<input type="text" name="user_id"></p>
    <p>ユーザー名<input type="text" name="user_name"></p>
    <p>パスワード<input type="password" name="user_password"></p>
    <p><input type="submit" value="登録"></p>
</form>
<%@include file="../footer.html" %>