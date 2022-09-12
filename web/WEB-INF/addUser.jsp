<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add user</title>
</head>
<body>
<h1 style="color: mediumpurple">ADD USER</h1>
<%
    String msg = (String) request.getAttribute("msg");
%>
<% if (msg != null) {%>
<p style="color: red"><%=msg%>
</p>
<%}%>
<h3 style="color: mediumpurple">Please input data about the user:</h3>
<form action="/users/add" , method="post">
    <input type="text" name="name" placeholder="Please input user's name"><br>
    <input type="text" name="surname" placeholder="Please input user's surname"><br>
    <input type="email" name="email" placeholder="Please input user's email"><br>
    <input type="password" name="password" placeholder="Please input user's password"><br>
    <input type="submit" value="Register"/>
</form>
</body>
</html>


