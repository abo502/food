
<%@ page import="java.util.List" %>
<%@ page import="entity.Food" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>食品列表</title>
</head>
<body>
<div style="height:500px; width:500px; margin:0 auto;text-align: center;border: 2px solid; border-radius: 50px;background-color: indianred;">
    <h1 align="center">食品列表:</h1><br>
    <form action="/find" method="get">
        筛选大于该数量的食品：
        <input type="number" name="num">&nbsp;<input type="submit" value="搜索">
        <%List<Food> foods = (List<Food>) session.getAttribute("foods");%>
        <table style="margin-top: 80px;margin-left: 130px;border: 2px solid;border-radius: 20px;">
            <th>食品名称：</th>
            <th>食品数量：</th>
            <th>食品价格：</th>
            <%
                for (Food food : foods) {
            %>
            <tr>
                <td><%=food.getName()%>
                </td>
                <td><%=food.getNumber()%>
                </td>
                <td>$<%=food.getPrice()%>.00
                </td>
                <%}%>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
