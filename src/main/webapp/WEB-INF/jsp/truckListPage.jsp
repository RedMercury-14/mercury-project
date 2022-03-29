<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="myTags" prefix="myt" %>

<!DOCTYPE html>
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="local.loc" var="loc"/>
<html>
<head>
<meta charset="utf-8">
<title><fmt:message bundle="${loc}" key="trucklist.title"/></title>

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
		<a href="MainController?command=GO_TO_ADDTRUCK_PAGE"><fmt:message bundle="${loc}" key="trucklist.add"/></a>
	</p>
	<table>
		<tr>
			<th><fmt:message bundle="${loc}" key="trucklist.trucknum"/></th>
			<th><fmt:message bundle="${loc}" key="trucklist.trailernum"/></th>
			<th><fmt:message bundle="${loc}" key="trucklist.truckmod"/></th>
			<th><fmt:message bundle="${loc}" key="trucklist.trailermod"/></th>
			<th><fmt:message bundle="${loc}" key="trucklist.type"/></th>
			<th><fmt:message bundle="${loc}" key="trucklist.yeartruck"/></th>
			<th><fmt:message bundle="${loc}" key="trucklist.yeartrailer"/></th>
			<myt:viewTag role = "${login}">
			<th><fmt:message bundle="${loc}" key="trucklist.setting"/></th>
			</myt:viewTag>
			
		</tr>
		<c:forEach var="truck" items="${Truck}">
			<tr>
				<td><c:out value="${truck.numCar}" /></td>
				<td><c:out value="${truck.numTr}" /></td>
				<td><c:out value="${truck.carModel}" /></td>
				<td><c:out value="${truck.trModel}" /></td>
				<td><c:out value="${truck.typeTr}" /></td>
				<td><c:out value="${truck.dateCar}" /></td>
				<td><c:out value="${truck.dateTr}" /></td>
				<myt:viewTag role = "${login}">
						<td><a href="MainController?command=GO_TO_EDITTRUCK_PAGE&carnum=<c:out value="${truck.numCar}"/>"><fmt:message bundle="${loc}" key="trucklist.edit"/></a>

							<form method="post" action="MainController?command=delTruck">
								<input type="hidden" name="numcar"
									value="<c:out value="${truck.numCar}"/>"> <input
									type="submit" value="<fmt:message bundle="${loc}" key="trucklist.del"/>"><br> <br>
							</form></td></myt:viewTag>
			</tr>
		</c:forEach>
	</table>
	<a href="MainController?command=GO_TO_USER_PAGE"><fmt:message bundle="${loc}" key="main.backbutton"/></a>
</body>
</html>