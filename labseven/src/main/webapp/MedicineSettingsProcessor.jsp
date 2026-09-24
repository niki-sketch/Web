<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<!DOCTYPE html>
<%
    request.setCharacterEncoding("UTF-8");

    String username = request.getParameter("username");
    String color = request.getParameter("color");

    if (username == null || username.trim().isEmpty()) {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Не задано имя пользователя");
        return;
    }
    if (color == null || color.trim().isEmpty()) {
        color = "white";
    }

    // Сохранение имени пользователя в Cookie на 1 час
    Cookie userCookie = new Cookie("medicine.username", URLEncoder.encode(username, "UTF-8"));
    userCookie.setMaxAge(3600);
    response.addCookie(userCookie);

    // Сохранение выбранного цвета в Cookie на 1 час
    Cookie colorCookie = new Cookie("medicine.color", color);
    colorCookie.setMaxAge(3600);
    response.addCookie(colorCookie);

    // Работа с сессией: счётчик посещений
    HttpSession sess = request.getSession(true);
    Integer visitCount = (Integer) sess.getAttribute("visitCount");
    if (visitCount == null) {
        visitCount = 1;
    } else {
        visitCount = visitCount + 1;
    }
    sess.setAttribute("visitCount", visitCount);

    // Сохранение даты текущего обращения в сессии
    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
    sess.setAttribute("lastVisitDate", sdf.format(new Date()));

    // Сохранение имени в сессии (для отображения на следующей странице)
    sess.setAttribute("username", username);

    // Перенаправление на страницу отображения данных
    response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/MedicineInfo.jsp"));
%>