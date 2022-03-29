<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
     <%@ taglib uri="myTags" prefix="myt" %>
<!DOCTYPE html>
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="local.loc" var="loc"/>
<html>
<head>
<meta charset="utf-8">
<title><fmt:message bundle="${loc}" key="userpage.title"/></title>
</head>
<body class = "body">
<div>
	<form action="MainController?command=local" method="post">
		<input type="hidden" name="local" value="ru" /> <input type="submit" value=<fmt:message bundle="${loc}" key="local.namebutton.rus"/> /><br />
		<input type="hidden" name="url" value="GO_TO_USER_PAGE" />
	</form>

	<form action="MainController?command=local" method="post">
		<input type="hidden" name="local" value="en" /> <input type="submit" value=<fmt:message bundle="${loc}" key="local.namebutton.en"/> /><br />
		<input type="hidden" name="url" value="GO_TO_USER_PAGE" />
	</form>	
</div>

<h1> <fmt:message bundle="${loc}" key="userpage.heading"/> <c:out value="${login}"/></h1>
<p><fmt:message bundle="${loc}" key="userpage.user"/> <c:out value="${name}"/>  <c:out value="${surname}"/> <p>

<div > 
<myt:viewTag role = "${role}">
Вы вошли как: <c:out value="${login}" /> с ролью: <c:out value="${role}" />
</myt:viewTag>

</div>
<br><br>
<div >    
    <a href="MainController?command=GO_TO_ORDERLIST_PAGE"><fmt:message bundle="${loc}" key="userpage.orderlist"/></a>   
</div>
<br> 
<div >    
    <a href="MainController?command=GO_TO_TRUCKLIST_PAGE"><fmt:message bundle="${loc}" key="userpage.carlist"/></a>   
</div>
<br> 
<div >    
    <a href="MainController?command=DISPOSITION_COMMAND"><fmt:message bundle="${loc}" key="userpage.dispo"/></a>   
</div>
<br> 
<div >    
    <a href="MainController?command=GO_TO_SETTING_PAGE"><fmt:message bundle="${loc}" key="main.setting"/></a>   
</div>

<br> 
<myt:mainViewTag target="admin" value="${role}">
<div >    
    <a href="MainController?command=GO_TO_WAYBILL_PAGE"><fmt:message bundle="${loc}" key="userpage.waybill"/></a>   
</div><br></myt:mainViewTag> 
<myt:viewTag role = "${role}">
<div >   
    <a href="MainController?command=GO_TO_CONTROL_PAGE"><fmt:message bundle="${loc}" key="userpage.controllist"/></a>   
</div>
<br> 
</myt:viewTag>


<div >   
    <a href="MainController?command=GO_TO_LOGINATION_PAGE"><fmt:message bundle="${loc}" key="main.backbutton"/></a>   
</div>
<br> 
<div >   
    <a href="MainController?command=EXIT"><fmt:message bundle="${loc}" key="main.exit"/></a>   
</div>

</body>
</html>