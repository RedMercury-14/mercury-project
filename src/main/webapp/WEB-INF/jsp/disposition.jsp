<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    
<!DOCTYPE html>
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="local.loc" var="loc"/>
<html>
<head>
<meta charset="utf-8">
<title><fmt:message bundle="${loc}" key="dispo.title" /></title>
</head>
<body>

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

<h1><fmt:message bundle="${loc}" key="dispo.heading" /> <c:out value="${login}"/></h1>

	<table>
		<tr>
			<th><fmt:message bundle="${loc}" key="trucklist.trucknum" /></th>
			<th><fmt:message bundle="${loc}" key="trucklist.trailernum" /></th>
			<th><fmt:message bundle="${loc}" key="orderlist.numberorder" /></th>
			<th><fmt:message bundle="${loc}" key="order.route" /></th>
			<th><fmt:message bundle="${loc}" key="order.uploaddate" /></th>
			<th><fmt:message bundle="${loc}" key="order.customdate" /></th>
			<th><fmt:message bundle="${loc}" key="order.unloadingdate" /></th>
			<th><fmt:message bundle="${loc}" key="crew.driver" /></th>
			<th><fmt:message bundle="${loc}" key="order.cargo" /></th>	
			<th><fmt:message bundle="${loc}" key="trucklist.type" /></th>	
			<th><fmt:message bundle="${loc}" key="orderlist.forwarder" /></th>	
			<th><fmt:message bundle="${loc}" key="order.client" /></th>			
			<th><fmt:message bundle="${loc}" key="orderlist.status" /></th>	
			<th><fmt:message bundle="${loc}" key="orderlist.comment" /></th>	
			
		</tr>
		<c:forEach var="entry" items="${Entry}">		
			<tr>
				<td><c:out value="${entry.numTrack}"/></td>
				<td><c:out value="${entry.numTrailer}"/></td>
				<td><c:out value="${entry.numOrder}"/></td>
				<td><c:out value="${entry.route}"/></td>
				<td><c:out value="${entry.dateLoad}"/></td>
				<td><c:out value="${entry.dateCust}"/></td>
				<td><c:out value="${entry.dateUnload}"/></td>
				<td><c:out value="${entry.driver}"/></td>
				<td><c:out value="${entry.cargo}"/></td>
				<td><c:out value="${entry.typeOfTrailer}"/></td>
				<td><c:out value="${entry.user}" /></td>
				<td><c:out value="${entry.client}"/></td>				
				<td><c:out value="${entry.status}"/></td>
				<td><c:out value="${entry.comment}"/></td>
					<td><form method="post"	action="MainController?command=addComment&numord=<c:out value="${entry.numOrder}"/>">
					<input name="comment"/>											
					<input type="submit" value="<fmt:message bundle="${loc}" key="dispo.addcomment" />"><br><br>
					</form></td>
			</tr>			
		</c:forEach>
	</table>
	<a href="MainController?command=GO_TO_USER_PAGE"><fmt:message bundle="${loc}" key="main.backbutton" /></a> <br />

</body>
</html>