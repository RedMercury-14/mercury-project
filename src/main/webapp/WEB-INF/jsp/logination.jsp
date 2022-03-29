<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    
<!DOCTYPE html>
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="local.loc" var="loc"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><fmt:message bundle="${loc}" key="log.title" /></title>
</head>
<body>
<div>
<h2><fmt:message bundle="${loc}" key="log.login.user" /></h2>
	<form action="MainController" method="post">
		<input type="hidden"  name="command" value="logination">
	    <fmt:message bundle="${loc}" key="log.login" />
		<input type="text" name="login" value="" />
		<br/><br/>
		<fmt:message bundle="${loc}" key="log.password" />
	    <input type="password" name="password" value="" />
	    <br/><br/>
	    <div class="pozition">
		<input type="submit" value=" <fmt:message bundle="${loc}" key="log.loginbutton" />"/>
		</div>
		</div>
	</form>
	<br />
	<br />
	<c:out value="${param.errorMessage}" />	
	<br />
	<br />	
	
<div >    
    <a href="MainController?command=GO_TO_MAIN_PAGE"><fmt:message bundle="${loc}" key="main.backbutton" /></a>   
</div>
</body>
</html>