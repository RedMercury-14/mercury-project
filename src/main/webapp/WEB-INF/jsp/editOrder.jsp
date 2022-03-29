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
<title> <fmt:message bundle="${loc}" key="orderedit.title" /> </title>
</head>
<body>
<h3><fmt:message bundle="${loc}" key="orderedit.heading" /> <c:out value="${Order.numOrd}"/>. </h3>
<form method="post" action="MainController?command=editOrder">
<input type="hidden" value="GO_TO_ORDERLIST_PAGE" name="commandPage" />
<input type="hidden" value="<c:out value="${Order.numOrd}"/>" name="numord" />

<label><fmt:message bundle="${loc}" key="order.route" /></label><br>
<input name="route" value="<c:out value="${Order.route}"/>" /><br><br>

<label>Колличество заказов(what??)</label><br>
<input name="col" value="<c:out value="${Order.ordCol}"/>" /><br><br>

<label><fmt:message bundle="${loc}" key="order.uploaddate" /></label><br>
<input name="datel" value="<c:out value="${Order.dateLoad}"/>" /><br><br>

<label><fmt:message bundle="${loc}" key="order.ucustomdate" /> </label><br>
<input name="datec" value="<c:out value="${Order.dateCust}"/>" /><br><br>

<label><fmt:message bundle="${loc}" key="order.unloadingdate" /> </label><br>
<input name="dateun" value="<c:out value="${Order.dateUnload}"/>" /><br><br>

<label><fmt:message bundle="${loc}" key="order.client" /></label><br>
<input name="client" value="<c:out value="${Order.client}"/>" /><br><br>

<label><fmt:message bundle="${loc}" key="order.price" /></label><br>
<input name="price" value="<c:out value="${Order.prise}"/>" /><br><br>

<label><fmt:message bundle="${loc}" key="order.cargo" /></label><br>
<input name="cargo" value="<c:out value="${Order.cargo}"/>" /><br><br>
<br />
	<c:out value="${errorMessageOrder}" />	
<br />
<input type="submit" value="<fmt:message bundle="${loc}" key="main.edit" />" />
</form>
<div >    
    <a href="MainController?command=GO_TO_ORDERLIST_PAGE"><fmt:message bundle="${loc}" key="main.backbutton" /></a>   
</div>
</body>
</html>