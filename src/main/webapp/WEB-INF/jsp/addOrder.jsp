<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    
<!DOCTYPE html>
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="local.loc" var="loc"/>
<html>
<head>
<meta charset="UTF-8">
<title><fmt:message bundle="${loc}" key="order.title" /></title>
</head>
<body>
<h3><fmt:message bundle="${loc}" key="order.heading" /></h3>

<form method="post" action="MainController?command=addOrder">

<label><fmt:message bundle="${loc}" key="order.route" /></label><br>
<input name="route"/><br><br>

<label><fmt:message bundle="${loc}" key="order.uploaddate" /></label><br>
<input name="datel"/><br><br>

<label><fmt:message bundle="${loc}" key="order.customdate" /></label><br>
<input name="datec"/><br><br>

<label><fmt:message bundle="${loc}" key="order.unloadingdate" /></label><br>
<input name="dateun"/><br><br>

<label><fmt:message bundle="${loc}" key="order.client" /></label><br>
<input name="client"/><br><br>

<label><fmt:message bundle="${loc}" key="order.price" /></label><br>
<input name="price"/><br><br>

<label><fmt:message bundle="${loc}" key="order.cargo" /></label><br>
<input name="cargo"/><br><br>

<br />
	<c:out value="${errorMessageOrder}" />	
<br />

<input type="submit" value="<fmt:message bundle="${loc}" key="main.save" />"/>
</form>
</body>
</html>