<%@ page import="model.User" %>
<%@ page import="model.Category" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Item" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main page</title>
</head>
<%
    User user = (User) session.getAttribute("user");
    List<Category> categories = (List<Category>) request.getAttribute("categories");
%>
<body>
<div>
    <%
        if (user == null) {
    %>
    <div style="float: right;">
        <a href="/login" style="color: mediumpurple">Login</a>
        <a href="/users/add" style="color: mediumpurple">Register</a>
    </div>
    <div style="position: fixed; left: 30%">
        <a href="/item/main" style="color: mediumpurple">Main</a>
        <% for (Category category : categories) { %>
        <a href="/items?id=<%=category.getId()%>"><%=category.getName()%>
        </a>
        <% }%>
    </div>
    <% } else {%>
    <span>Hello,  <%=user.getName()%></span>
<%--    <a href="/items/add/user?id=<%=user.getId()%>">Add Item</a>--%>
    <a href="/items/add/user">Add Item</a>

    <a href="/item?id=<%=user.getId()%>">My Items</a>
    <a href="/logout" style="color: dodgerblue">Logout</a>
    <%}%>
</div>
</body>
</html>
<%--    <a href="/items/add" style="color: mediumpurple">Add Item</a><br>--%>