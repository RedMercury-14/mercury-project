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
<title><fmt:message bundle="${loc}" key="workeredit.title" /> </title>
</head>
<body>
<h3><fmt:message bundle="${loc}" key="workeredit.heading" /> <c:out value="${UserControl.name}"/> <c:out value="${UserControl.surName}"/></h3>
<form method="post" action="MainController?command=editControll">
<input type="hidden" value="GO_TO_CONTROL_PAGE" name="commandPage" />
<input type="hidden" value="<c:out value="${UserControl.id}"/>" name="id" />

<label><fmt:message bundle="${loc}" key="control.username" /> <c:out value="${UserControl.name}"/></label><br>
<input name="name" value="<c:out value="${UserControl.name}"/>" /><br><br>

<label><fmt:message bundle="${loc}" key="control.usersurname" /></label><br>
<input name="surname" value="<c:out value="${UserControl.surName}"/>" /><br><br>

<label><fmt:message bundle="${loc}" key="control.userpatronymic" /></label><br>
<input name="patronymic" value="<c:out value="${UserControl.patronymic}"/>" /><br><br>

<label><fmt:message bundle="${loc}" key="control.usernumpass" /></label><br>
<input name="numpass" value="<c:out value="${UserControl.numberOfPassport}"/>" /><br><br>

<label><fmt:message bundle="${loc}" key="control.userposition" /></label><br>
<input name="position" value=<c:out value="${UserControl.position}" /> /><br><br>

<label><fmt:message bundle="${loc}" key="control.useremail" /></label><br>
<input name="email" value=<c:out value="${UserControl.EMail}" /> /><br><br>

<label><fmt:message bundle="${loc}" key="control.role" /></label><br>	
					<select name="isRole">
					<option value="0"></option>
						<c:forEach var="role"	items="${Role}">						
							<option value="<c:out value="${role.id}"/>"><c:out value="${role.role}"/></option>
						</c:forEach>
						</select>
				

<br />
	<c:out value="${errorMessageTruck}" />	
<br />

<input type="submit" value="<fmt:message bundle="${loc}" key="main.edit" />" />
</form>
<div >    
    <a href="MainController?command=GO_TO_CONTROL_PAGE"><fmt:message bundle="${loc}" key="main.backbutton" /></a>   
</div>
</body>
</html>