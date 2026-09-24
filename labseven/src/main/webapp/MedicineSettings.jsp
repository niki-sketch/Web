<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Настройки просмотра аптеки</title>
</head>
<body>

<h2>Личный кабинет аптеки</h2>

<form method="GET" action="MedicineSettingsProcessor.jsp">
    Ваше имя:
    <input type="text" name="username"
    <%
        // Поиск ранее сохранённого имени в Cookie
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                if ("medicine.username".equals(cookies[i].getName())) {
                    out.print(" value='" + java.net.URLDecoder.decode(cookies[i].getValue(), "UTF-8") + "' ");
                    break;
                }
            }
        }
    %>
    ><br><br>

    Цвет страницы:
    <select name="color">
        <option value="white">Белый</option>
        <option value="lightyellow">Светло-жёлтый</option>
        <option value="lightgreen">Светло-зелёный</option>
        <option value="lightblue">Светло-голубой</option>
    </select><br><br>

    <input type="submit" value="Продолжить">
</form>

</body>
</html>