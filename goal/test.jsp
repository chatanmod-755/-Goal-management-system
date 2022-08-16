<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import = "bean.User"%>

<%
User user = (User)session.getAttribute("user");
if (user == null){
    String url = "/UNION/goal/session-error.jsp";
    response.sendRedirect(url);
}
%>
<html><head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sample of calling JFreeChart Servlet to generate PNG chart image</title>
</head><body>
JFreeChartで作ったグラフをJSP画面に表示させるプログラム<br/><br/>
<%
String [][] input = {
        {"120","","3月"},
        {"130","","4月"},
        {"140","","6月"},
        {"150","","7月"},
        {"160","","8月"},
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
%>
<img src="/UNION/goal/Goal_progress_chart2?mode=2" />
</body>
</html>