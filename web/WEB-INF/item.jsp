<%@ page import="model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Item" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Items</title>
</head>
<body>
<h1 style="color: mediumpurple">ITEMS</h1>
<% List<Item> items = (List<Item>) request.getAttribute("items");
//    User user = (User) session.getAttribute("user");
%>
<table border="1">
    <tr>
        <th>IMAGE</th>
        <th>ID</th>
        <th>TITLE</th>
        <th>PRICE</th>
        <th>CATEGORY</th>
        <th>USER NAME AND SURNAME</th>
        <th>ACTION</th>
    </tr>
    <% for (Item item : items) { %>
    <tr>
        <td>
            <% if (item.getPicUrl() == null || item.getPicUrl().length() == 0) {%>
            <img src="/image/istockphoto-1197083298-1024x1024.jpg">
            <%} else {%>
            <img src="/getImage?picUrl=<%=item.getPicUrl()%>" width="100">
            <%}%>
        </td>
        <td><%=item.getId()%>
        </td>
        <td><%=item.getTitle()%>
        </td>
        <td><%=item.getPrice()%>
        </td>
        <td>
            <%if (item.getCategory() != null) {%>
            <%=item.getCategory().getName()%>
            <%} else {%>
            <span style="color: red">There is no category</span>
            <%}%>
        </td>
        <td>
            <%if (item.getUser() != null) {%>
            <%=item.getUser().getName()%>
            <%} else {%>
            <span style="color: red">There is no user</span>
            <%}%>
        </td>
        <td>
            <a href="/items/remove?id=<%=item.getId()%>">Remove</a>
            <a href="/items/edit?id=<%=item.getId()%>">Edit</a>
        </td>
    </tr>
    <% } %>
</table>
</body>
</html>
