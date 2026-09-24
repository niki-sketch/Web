<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    // Чтение цвета фона из Cookie
    String bgColor = "white";
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (int i = 0; i < cookies.length; i++) {
            if ("medicine.color".equals(cookies[i].getName())) {
                bgColor = cookies[i].getValue();
                break;
            }
        }
    }

    // Чтение данных из сессии
    String username = (String) session.getAttribute("username");
    Integer visitCount = (Integer) session.getAttribute("visitCount");
    String lastVisitDate = (String) session.getAttribute("lastVisitDate");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Информация о пользователе</title>
</head>
<body bgcolor="<%= bgColor %>">

<h1>Добро пожаловать, <%= username %>!</h1>

<table border='1'>
<tr>
<td><b>Параметр</b></td>
<td><b>Значение</b></td>
</tr>
<tr>
<td>Имя пользователя (из сессии)</td>
<td><%= username %></td>
</tr>
<tr>
<td>Количество посещений (сессия)</td>
<td><%= visitCount %></td>
</tr>
<tr>
<td>Дата последнего обращения (сессия)</td>
<td><%= lastVisitDate %></td>
</tr>
<tr>
<td>Выбранный цвет (Cookie)</td>
<td><%= bgColor %></td>
</tr>
</table>

<br>
<a href="MedicineSettings.jsp">Вернуться к настройкам</a>

</body>
</html>