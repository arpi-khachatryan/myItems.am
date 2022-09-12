<%@ page import="model.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<h1 style="color: mediumpurple">USERS</h1>
<% List<User> users = (List<User>) request.getAttribute("users");%>
<table border="1">
    <tr>
        <th>ID</th>
        <th>NAME</th>
        <th>SURNAME</th>
        <th>EMAIL</th>
        <th>PASSWORD</th>
        <th>ACTION</th>
    </tr>
    <% for (User user : users) { %>
    <tr>
        <td><%=user.getId()%>
        </td>
        <td><%=user.getName()%>
        </td>
        <td><%=user.getSurname()%>
        </td>
        <td><%=user.getEmail()%>
        </td>
        <td><%=user.getPassword()%>
        </td>
        <td>
            <a href="/users/remove?id=<%=user.getId()%>">Remove</a> |
            <a href="/users/edit?id=<%=user.getId()%>">Edit</a>
        </td>
    </tr>
    <% } %>
</table>
</body>
</html>
