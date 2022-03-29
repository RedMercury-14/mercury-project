<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="myTags" prefix="myt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Регистрация</title>
</head>
<body>

<h1>Поздравляю!</h1>
<br/>
<h3> Регистриция прошла успешно! Можете <a href="MainController?command=GO_TO_LOGINATION_PAGE">войти</a> на свою страницу</h3>
<br/>
<myt:viewTag role = "${role}">
<div >   
    <a href="MainController?command=GO_TO_CONTROL_PAGE">Управление персоналом</a>   
</div>
</myt:viewTag>
</body>
</html>