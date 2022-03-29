<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>    

    
<!DOCTYPE html>
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="local.loc" var="loc"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<title><fmt:message bundle="${loc}" key="reg.title" /></title>
</head>

<body class = "body">
    <div class="card">
    
 <h2><fmt:message bundle="${loc}" key="reg.registration"/></h2>

	<form action="MainController" method="post">	
		
	    <input type="hidden"  name="command" value="registration">
	    <fmt:message bundle="${loc}" key="reg.name"/>
		<input type="text" name="name" value="" />
		<br />
		<br />
		<fmt:message bundle="${loc}" key="reg.surname"/>
	    <input type="text" name="surname" value="" />
	    <br />
	    <br />
	    <fmt:message bundle="${loc}" key="reg.patronymic"/>
	    <input type="text" name="patronymic" value="" />
	    <br />
	    <br />
	    <fmt:message bundle="${loc}" key="reg.login"/>
	    <input type="text" name="login" value="" />
	    <br />
	    <br />
	    <fmt:message bundle="${loc}" key="reg.addgender"/>
	    <input type="radio" name="gender" value="female" checked /><fmt:message bundle="${loc}" key="reg.female"/>
    	<input type="radio" name="gender" value="male" /><fmt:message bundle="${loc}" key="reg.male"/>
    	<br /><br />
    	<fmt:message bundle="${loc}" key="reg.country"/>
    	<select name="country">
        <option>Беларусь</option>
        <option>Росссийская Федерация</option>
        <option>Украина</option>
        <option>Германия</option>
        <option>Польша</option>
        <option>Чехия</option>
    	</select>
    <br><br>
	    <fmt:message bundle="${loc}" key="reg.password"/>
	    <input type="password" name="password" value="" pattern="[A-Za-z0-9]{3,}" title="Пароль должен содержать не менее 3 символов, включая большие и маленькие латинские буквы, а также цифры."/>
	    <br />
	    <br />
	    <fmt:message bundle="${loc}" key="reg.repeatpassword"/>
	    <input type="password" name="password2" value="" />
	    <br />
	    <br />
        <div class="pozition">
		<input type="submit" value="<fmt:message bundle="${loc}" key="reg.register"/>" class="buttonColor" />
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
	<a href="MainController?command=GO_TO_MAIN_PAGE"><fmt:message bundle="${loc}" key="main.backbutton" /></a>  <br />
	</div>
            
</body>
</html>