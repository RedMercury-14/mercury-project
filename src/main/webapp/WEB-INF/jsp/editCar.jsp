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
<title> ><fmt:message bundle="${loc}" key="caredit.title" /> </title>
</head>
<body>
<h3><fmt:message bundle="${loc}" key="caredit.heading" /> <c:out value="${Truck.numCar}"/> </h3>
<form method="post" action="MainController?command=editTruck">
<input type="hidden" value="GO_TO_TRUCKLIST_PAGE" name="commandPage" />
<input type="hidden" value="<c:out value="${Truck.numCar}"/>" name="carnum" />

<label><fmt:message bundle="${loc}" key="trucklist.trailernum" /> </label><br>
<input name="trnum" value="<c:out value="${Truck.numTr}"/>" /><br><br>

<label><fmt:message bundle="${loc}" key="trucklist.truckmod" /></label><br>
<input name="carmod" value="<c:out value="${Truck.carModel}"/>" /><br><br>

<label><fmt:message bundle="${loc}" key="trucklist.trailermod" /></label><br>
<input name="trmod" value="<c:out value="${Truck.trModel}"/>" /><br><br>

<label><fmt:message bundle="${loc}" key="trucklist.type" /></label><br>
<input name="typetr" value="<c:out value="${Truck.typeTr}"/>" /><br><br>

<label><fmt:message bundle="${loc}" key="trucklist.yeartruck" /></label><br>
<input name="datecar" value=<c:out value="${Truck.dateCar}" /> /><br><br>

<label><fmt:message bundle="${loc}" key="trucklist.yeartrailer" /></label><br>
<input name="datetr" value=<c:out value="${Truck.dateTr}" /> /><br><br>

<br />
	<c:out value="${errorMessageTruck}" />	
<br />

<input type="submit" value="<fmt:message bundle="${loc}" key="main.edit" />" />
</form>
<div >    
    <a href="MainController?command=GO_TO_TRUCKLIST_PAGE"><fmt:message bundle="${loc}" key="main.backbutton" /></a>   
</div>
</body>
</html>