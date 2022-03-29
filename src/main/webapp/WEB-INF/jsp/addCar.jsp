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
<title><fmt:message bundle="${loc}" key="addcar.title" /></title>
</head>
<body>
<h3><fmt:message bundle="${loc}" key="addcar.heading" /></h3>

<form method="post" action="MainController?command=addTruck">

<label><fmt:message bundle="${loc}" key="trucklist.trucknum" /></label><br>
<input name="carnum"/><br><br>

<label><fmt:message bundle="${loc}" key="trucklist.trailernum" /></label><br>
<input name="trnum"/><br><br>

<label><fmt:message bundle="${loc}" key="trucklist.truckmod" /></label><br>
<input name="carmod"/><br><br>

<label><fmt:message bundle="${loc}" key="trucklist.trailermod" /></label><br>
<input name="trmod"/><br><br>

<label><fmt:message bundle="${loc}" key="trucklist.type" /></label><br>
<input name="typetr"/><br><br>

<label><fmt:message bundle="${loc}" key="trucklist.yeartruck" /></label><br>
<input name="datecar"/><br><br>

<label><fmt:message bundle="${loc}" key="trucklist.yeartrailer" /></label><br>
<input name="datetr"/><br><br>
<br />
	<c:out value="${param.errorMessageTruck}" />	
<br />

<input type="submit" value="<fmt:message bundle="${loc}" key="main.save" />" />
<div >    
    <a href="MainController?command=GO_TO_TRUCKLIST_PAGE"><fmt:message bundle="${loc}" key="main.backbutton" /></a>   
</div>
</form>
</body>
</html>