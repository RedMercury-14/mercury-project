<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
 <%@ taglib uri="myTags" prefix="myt" %>

<!DOCTYPE html>
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="local.loc" var="loc"/>
<html>
<head>
<meta charset="UTF-8">
<title><fmt:message bundle="${loc}" key="orderlist.title" /></title>
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
		<a href="MainController?command=GO_TO_ADDORDER_PAGE"><fmt:message bundle="${loc}" key="orderlist.addorder" /></a>
	</p>
	<table>
		<tr>
			<th><fmt:message bundle="${loc}" key="orderlist.numberorder" /></th>
			<th><fmt:message bundle="${loc}" key="order.route" /></th>
			<th><fmt:message bundle="${loc}" key="orderlist.comment" /></th>
			<th><fmt:message bundle="${loc}" key="order.uploaddate" /></th>
			<th><fmt:message bundle="${loc}" key="order.customdate" /></th>
			<th><fmt:message bundle="${loc}" key="order.unloadingdate" /></th>
			<myt:mainViewTag target="admin" value="${role}">
			<th><fmt:message bundle="${loc}" key="order.client" /></th>
			<th><fmt:message bundle="${loc}" key="order.price" /></th>
			</myt:mainViewTag>
			<th><fmt:message bundle="${loc}" key="order.cargo" /></th>
			<th><fmt:message bundle="${loc}" key="trucklist.trucknum" /></th>
			<th><fmt:message bundle="${loc}" key="orderlist.forwarder" /></th>
			<th><fmt:message bundle="${loc}" key="orderlist.admin" /></th>
			<th><fmt:message bundle="${loc}" key="orderlist.status" /></th>
			<myt:mainViewTag target="admin" value="${role}">
			<th><fmt:message bundle="${loc}" key="main.setting" /></th>
			</myt:mainViewTag>
		</tr>
		<c:forEach var="order" items="${Order}">
			<tr>
				<td><c:out value="${order.numOrd}" /></td>
				<td><c:out value="${order.route}" /></td>
				<td><c:out value="${order.ordCol}" /></td>
				<td><c:out value="${order.dateLoad}" /></td>
				<td><c:out value="${order.dateCust}" /></td>
				<td><c:out value="${order.dateUnload}" /></td>
				<myt:mainViewTag target="admin" value="${role}">
				<td><c:out value="${order.client}" /></td>
				<td><c:out value="${order.prise}" /></td>
				</myt:mainViewTag>
				<td><c:out value="${order.cargo}" /></td>
				<td><c:out value="${order.carNum}" /></td>
				<td><c:out value="${order.user}" /></td>
				<td><c:out value="${order.admin}" /></td>				
				<td><c:out value="${order.status}" /><myt:mainViewTag target="admin" value="${role}">
				
				<form method="post" action="MainController?command=proof">		
				<input type="hidden" name="numord" value="<c:out value="${order.numOrd}"/>"> 		
				<input type="checkbox" value="<fmt:message bundle="${loc}" key="orderlist.statusnow" />" name="accept"><br>
				<input type="submit" value="<fmt:message bundle="${loc}" key="orderlist.statuspoint" />">
				</form>
				</td>
				<td><a href="MainController?command=GO_TO_EDITORDER_PAGE&numord=<c:out value="${order.numOrd}"/>"><fmt:message bundle="${loc}" key="main.edit" /></a>
					
					<form method="post" action="MainController?command=editCarInOrder">
					<input type="hidden" name="numord" value="<c:out value="${order.numOrd}"/>"> 
					<select name="isTruck">
					<option></option>
						<c:forEach var="truck"	items="${Truck}">						
							<option value="<c:out value="${truck.numCar}"/>"><c:out value="${truck.numCar}"/></option>
						</c:forEach>
						</select>																
					<input type="submit" value="<fmt:message bundle="${loc}" key="orderlist.proof" />"><br>
					</form>
					
					<form method="post" action="MainController?command=delOrder">
						<input type="hidden" name="numord" value="<c:out value="${order.numOrd}"/>"> 						
						<input type="submit" value="<fmt:message bundle="${loc}" key="main.del" />"><br>
						<br>
					</form>
					</myt:mainViewTag></td>
			</tr>
		</c:forEach>
		
	</table>
	<a href="MainController?command=GO_TO_USER_PAGE"><fmt:message bundle="${loc}" key="main.backbutton" /></a>
</body>
</html>