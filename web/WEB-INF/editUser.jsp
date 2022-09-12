
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit User</title>
</head>
<body>
<%
    User user = (User) request.getAttribute("user");
%>
<h3 style="color: dodgerblue">Update the data:</h3>
<form action="/users/edit" method="post">
    <input type="hidden" name="id" value="<%=user.getId()%>">
    <input type="text" name="name" value="<%=user.getName()%>"><br>
    <input type="text" name="surname" value="<%=user.getSurname()%>"> <br>
    <input type="email" name="email" value="<%=user.getEmail()%>"><br>
    <input type="password" name="password" value="<%=user.getPassword()%>"><br>
    <input type="submit" value="Update">
</form>
</body>
</html>


