<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
      <%@ taglib uri="myTags" prefix="myt" %>
    
<!DOCTYPE html>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="local.loc" var="loc"/>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
<title><fmt:message bundle="${loc}" key="local.title" /></title>
</head>
<body>

<h1 class = "title">Transport Logistic</h1>

<div>
	<form action="MainController?command=local" method="post">
		<input type="hidden" name="local" value="ru"/> 
		<input type="submit" class="button25" value=<fmt:message bundle="${loc}" key="local.namebutton.rus"/> /><br />
		<input type="hidden" name="url" value="GO_TO_MAIN_PAGE" />
		<br>		
	</form>

	<form action="MainController?command=local" method="post">
		<input type="hidden" name="local" value="en" />
		<input type="submit" class="button25" value=<fmt:message bundle="${loc}" key="local.namebutton.en"/> /><br />
		<input type="hidden" name="url" value="GO_TO_MAIN_PAGE" />
		<br>
	</form>	
</div>
<myt:viewTag role = "${role}">
<h3>!Тест! Вы зашли как <c:out value="${role}" /> ваш логин <c:out value="${login}" /> </h3>
</myt:viewTag>
<div class = "card">  
    <a href="MainController?command=GO_TO_LOGINATION_PAGE"><fmt:message bundle="${loc}" key="local.mainbutton.log" /></a> <br />
    <a href="MainController?command=GO_TO_REGISTRATION_PAGE"><fmt:message bundle="${loc}" key="local.mainbutton.reg" /></a>   
</div>
<br/>
<br/>
<div>
<a href="MainController?command=GO_TO_USER_PAGE"><fmt:message bundle="${loc}" key="local.mypage" /></a> <br />
</div>  
<div class = "footer">
(C) 2021 GDF
</div>
</body>
</html>