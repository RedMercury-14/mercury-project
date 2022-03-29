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
<title><fmt:message bundle="${loc}" key="crew.title" /></title>
</head>
<body>
	<h3><fmt:message bundle="${loc}" key="crew.heading" /></h3>

	<form method="post" action="MainController?command=newCrew">
		
		<label><fmt:message bundle="${loc}" key="crew.driver" /></label><br>
		<select	name="isWorker" autofocus>				
				<c:forEach var="worker" items="${Worker}">
					<option value="<c:out value="${worker}"/> "><c:out value="${worker.surName}" /> <c:out value="${worker.name}" />  </option>
				</c:forEach>
			</select> 	<br><br> 		
		
		<label><fmt:message bundle="${loc}" key="trucklist.trucknum" /></label><br>			
			<select	name="isTruck">
				<c:forEach var="truck" items="${Truck}">
					<option value="<c:out value="${truck.numCar}"/>"><c:out
							value="${truck.numCar}" /></option>
				</c:forEach>
			</select> 	<br><br>
			

		<label><fmt:message bundle="${loc}" key="crew.datestart" /></label><br> 
		<input  name="dates"><br><br>
		 <label><fmt:message bundle="${loc}" key="crew.datefinish" /></label><br> 
		<input   name="datef"><br>
		<br> <br />
			

		
		<c:out value="${errorMessageWaybill}" />
		<br /> <input type="submit" value="<fmt:message bundle="${loc}" key="main.save" />" />
	</form>
</body>
</html>