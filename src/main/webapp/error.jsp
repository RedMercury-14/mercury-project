<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8"
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
<title><fmt:message bundle="${loc}" key="error.title" /></title>
</head>
<body>
<h1><fmt:message bundle="${loc}" key="error.heading" /></h1>
<h2><fmt:message bundle="${loc}" key="error.heading2" /></h2>
<h3>Massage: <c:out value="${errorMessage}"/> <c:out value="${param.errorMessage}"/></h3>
<br/>
<myt:mainViewTag target="block" value="${role}">
<div >   
   <a href="MainController?command=EXIT"><fmt:message bundle="${loc}" key="main.exit" /></a>    
</div>
<br> 
</myt:mainViewTag>
<input type="button" onclick="history.back();" value="<fmt:message bundle="${loc}" key="main.backbutton" />"/>
</body>
</html>