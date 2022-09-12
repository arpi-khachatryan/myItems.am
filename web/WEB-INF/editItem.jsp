<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.User" %>
<%@ page import="model.Item" %>
<%@ page import="model.Category" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Item</title>
</head>
<body>
<%
    Item item = (Item) request.getAttribute("items");
    List<Category> categories = (List<Category>) request.getAttribute("categories");
    List<User> users = (List<User>) request.getAttribute("users");
%>
<h3 style="color: mediumpurple">Update the data:</h3><br>
<form action="/items/edit" method="post">
    <input type="hidden" name="id" value="<%=item.getId()%>">
    <input type="text" name="title" value="<%=item.getTitle()%>"><br>
    <input type="number" name="price" value="<%=item.getPrice()%>"><br>
    <select name="category_id">
        <% for (Category category : categories) {
            if (category.equals(item.getCategory())) {
        %>
        <option selected value="<%=category.getId()%>"> <%=category.getName()%>
        </option>
        <% } else { %>
        <option value="<%=category.getId()%>"> <%=category.getName()%>
        </option>
        <% }
        } %>
    </select>
<%--    <select name="user_id">--%>
<%--        <% for (User user : users) {--%>
<%--            if (user.equals(item.getUser())) {--%>
<%--        %>--%>
<%--        <option selected value="<%=user.getId()%>"><%=user.getName()%> <%=user.getSurname()%>--%>
<%--        </option>--%>
<%--        <% } else { %>--%>
<%--        <option value="<%=user.getId()%>"><%=user.getName()%> <%=user.getSurname()%>--%>
<%--        </option>--%>
<%--        <% }--%>
<%--        } %>--%>
<%--    </select>--%>
    <input type="submit" value="UPDATE">
</form>
</body>
</html>


