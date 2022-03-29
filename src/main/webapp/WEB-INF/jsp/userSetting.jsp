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
<title><fmt:message bundle="${loc}" key="usersetting.title" /> <c:out value="${login}" /></title>
</head>
<body>
<h1><fmt:message bundle="${loc}" key="usersetting.heading" /></h1>

	<form action="MainController" method="post">			
	    <input type="hidden"  name="command" value="setting">
	    <fmt:message bundle="${loc}" key="control.username" />:
		<input type="text" name="name" value="<c:out value="${name}"/>" />
		<br />
		<br />
		<fmt:message bundle="${loc}" key="control.usersurname" />:
	    <input type="text" name="surname" value="<c:out value="${surname}"/>"  />
	    <br />
	    <br />
	    <fmt:message bundle="${loc}" key="control.userpatronymic" />:
	    <input type="text" name="patronymic" value="<c:out value="${patronymic}"/>"  />
	    <br />
	    <br />
	    <fmt:message bundle="${loc}" key="control.usernumpass" />:
	    <input type="text" name="numpass" value="<c:out value="${numpass}"/>"/>
	    <br />
	    <br />
	    <fmt:message bundle="${loc}" key="reg.password" />:
	    <input type="text" name="password" value="<c:out value="${password}"/>" />
	    <br />
	    <br />
	    <fmt:message bundle="${loc}" key="control.useremail" />:
	    <input type="text" name="email" value="<c:out value="${email}"/>" />
	    <br />
	    <br />
        <div class="pozition">
		<input type="submit" value="<fmt:message bundle="${loc}" key="main.save" />" class="buttonColor" />
        </div>
	</form>
	<br />
	<br />
        <div class="pozition">
         <c:out value="${param.errorMessage}" />
        <br>        
        </div>
		<br />
	<br />
         </div>
        <div>        
	<button type="button" class="btn btn-primary" onclick="history.back()">Вернуть</button>
	<br/>
	<a href="MainController?command=GO_TO_USER_PAGE_PROOF"><fmt:message bundle="${loc}" key="main.backbutton" /></a>
	</div>

</body>
</html>