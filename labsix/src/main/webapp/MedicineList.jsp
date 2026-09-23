<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Список лекарств</title>
</head>
<body>

<h2>Поиск лекарств по аптеке</h2>
<form action="MedicineList.jsp" method="get">
    Название аптеки: <input type="text" name="pharmacy" />
    <input type="submit" value="Показать" />
</form>

<%
    request.setCharacterEncoding("UTF-8");
    String pharmacy = request.getParameter("pharmacy");

    if (!lab.web.MedicineUtils.isPharmacyValid(pharmacy)) {
%>
        <jsp:forward page="ErrorManager.jsp" />
<%
    }
    pharmacy = lab.web.MedicineUtils.formatPharmacyName(pharmacy);
%>

<h1>Список лекарств аптеки <%= pharmacy %></h1>
<%@ include file="MedicineData.jsp" %>

</body>
</html>