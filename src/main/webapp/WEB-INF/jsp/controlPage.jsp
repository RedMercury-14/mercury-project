<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
     <%@ taglib uri="myTags" prefix="myt" %>
   
<!DOCTYPE html>
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="local.loc" var="loc"/>
<html>
<head>
<meta charset="UTF-8">
<title><fmt:message bundle="${loc}" key="control.title" /></title>
<style type="text/css">
TABLE {
	width: 1100px; /* Ширина таблицы */
	border-collapse: collapse; /* Убираем двойные линии между ячейками */
	text-align: center; /* по центру */
}

TD, TH {
	padding: 3px; /* Поля вокруг содержимого таблицы */
	border: 1px solid black; /* Параметры рамки */
}

TH {
	background: #b0e0e6; /* Цвет фона */
}

TD {
	white-space: nowrap; /* без переноса */
}
</style>
</head>
<body>
<p>
		<a href="MainController?command=GO_TO_ADDCONTROLL_PAGE"><fmt:message bundle="${loc}" key="control.adduser" /></a>
	</p>
	<table>
		<tr>
			<th><fmt:message bundle="${loc}" key="control.iduser" /></th>
			<th><fmt:message bundle="${loc}" key="control.userlogin" /></th>
			<th><fmt:message bundle="${loc}" key="control.username" /></th>
			<th><fmt:message bundle="${loc}" key="control.usersurname" /></th>
			<th><fmt:message bundle="${loc}" key="control.userpatronymic" /></th>
			<th><fmt:message bundle="${loc}" key="control.usernumpass" /></th>
			<th><fmt:message bundle="${loc}" key="control.userposition" /></th>
			<th><fmt:message bundle="${loc}" key="control.useremail" /></th>
			<th><fmt:message bundle="${loc}" key="control.roleid" /></th>
			<th><fmt:message bundle="${loc}" key="control.role" /></th>			
			<th>><fmt:message bundle="${loc}" key="trucklist.setting" /></th>
		</tr>
		<c:forEach var="сontroll" items="${UserControllArray}">
			<tr>
				<td><c:out value="${сontroll.id}" /></td>
				<td><c:out value="${сontroll.login}" /></td>
				<td><c:out value="${сontroll.name}" /></td>
				<td><c:out value="${сontroll.surName}" /></td>
				<td><c:out value="${сontroll.patronymic}" /></td>
				<td><c:out value="${сontroll.numberOfPassport}" /></td>
				<td><c:out value="${сontroll.position}" /></td>
				<td><c:out value="${сontroll.EMail}" /></td>
				<td><c:out value="${сontroll.roleID}" /></td>
				<td><c:out value="${сontroll.role}" /></td>
				<td><a href="MainController?command=GO_TO_EDITCONTROLL_PAGE&num=<c:out value="${сontroll.id}"/>"><fmt:message bundle="${loc}" key="main.edit" /></a>
															
					<form method="post" action="MainController?command=delControll">
						<input type="hidden" name="num" value="<c:out value="${сontroll.id}"/>"> 						
						<input type="submit" value="<fmt:message bundle="${loc}" key="main.del" />"><br>
						<br>
					</form></td>
			</tr>
		</c:forEach>
		
	</table>
	<a href="MainController?command=GO_TO_USER_PAGE"><fmt:message bundle="${loc}" key="main.backbutton" /></a>
</body>
</html>