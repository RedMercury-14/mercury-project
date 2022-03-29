<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    
<!DOCTYPE html>
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="local.loc" var="loc"/>
<html>
<head>
<meta charset="UTF-8">
<title><fmt:message bundle="${loc}" key="waybill.title" /></title>
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
		<a href="MainController?command=GO_TO_NEWCREW_PAGE"><fmt:message bundle="${loc}" key="waybill.addlist" /></a>
	</p>
	<table>
		<tr>
			<th><fmt:message bundle="${loc}" key="waybill.num" /></th>
			<th><fmt:message bundle="${loc}" key="waybill.numworker" /></th>
			<th><fmt:message bundle="${loc}" key="waybill.numcar" /></th>
			<th><fmt:message bundle="${loc}" key="waybill.worker" /></th>
			<th><fmt:message bundle="${loc}" key="waybill.datestart" /></th>
			<th><fmt:message bundle="${loc}" key="waybill.datefinish" /></th>
			<th><fmt:message bundle="${loc}" key="main.setting" /></th>
		</tr>
		<c:forEach var="workerHasTruck" items="${WorkerHasTruck}">
			<tr>
				<td><c:out value="${workerHasTruck.id}" /></td>
				<td><c:out value="${workerHasTruck.idWorkers}" /></td>
				<td><c:out value="${workerHasTruck.trucksNumber}" /></td>
				<td><c:out value="${workerHasTruck.workersPosition}" /></td>
				<td><c:out value="${workerHasTruck.dateOfStart}" /></td>
				<td><c:out value="${workerHasTruck.dateOfFinish}" /></td>
				
				
				<td><a href="MainController?command=GO_TO_EDITCREW_PAGE&numid=<c:out value="${workerHasTruck.id}"/>"><fmt:message bundle="${loc}" key="main.edit" /></a>
														
					<form method="post" action="MainController?command=delCrew">
						<input type="hidden" name="numord" value="<c:out value="${workerHasTruck.id}"/>"> 						
						<input type="submit" value="<fmt:message bundle="${loc}" key="main.del" />"><br>
						<br>
					</form></td>
			</tr>
		</c:forEach>
		
	</table>
	<a href="MainController?command=GO_TO_USER_PAGE"><fmt:message bundle="${loc}" key="main.backbutton" /></a>

</body>
</html>