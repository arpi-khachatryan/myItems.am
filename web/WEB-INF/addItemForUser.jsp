<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Category" %>
<%@ page import="model.User" %>
<%@ page import="model.Item" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add item</title>
</head>
<body>
<h1 style="color: mediumpurple">ADD ITEM</h1>
<%
    List<Category> categories = (List<Category>) request.getAttribute("categories");
    User user = (User) session.getAttribute("user");
%>
<h3 style="color:mediumpurple">Please input data about the item:</h3>

<form action="/items/add/user" , method="post" enctype="multipart/form-data">
    <input type="text" name="title" placeholder="Please input title"><br>
    <input type="number" name="price" placeholder="Please input price"><br>
    <h3 style="color: mediumpurple">Please select category:</h3>
    <select name="category_id">
        <% for (Category category : categories) { %>
        <option value="<%=category.getId()%>"><%=category.getName()%>
        </option>
        <% }%>
    </select><br>
    <h3 style="color: mediumpurple">Profile Picture:</h3>
    <input type="file" name="picUrl"/><br>
    <input type="hidden" name="user_id" value="<%=user.getId()%>"> <br>
    <input type="submit" value="Register">
</form>
</body>
</html>


