<%-- 
    Document   : login
    Created on : May 9, 2022, 3:00:19 PM
    Author     : CHUONG
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <h1>Login Page</h1>

        <form action="ProcessServlet" method="POST">
            <c:set var="errors" value="${requestScope.LOGINERROR}"/>
            UserID: <input type="text" name="txtUsername" value=""/><br/>
            <c:if test="${not empty errors.usernameError}">
                <font color="red">
                ${errors.usernameError}
                </font><br/>
            </c:if>
            Password: <input type="password" name="txtPassword" value=""/><br/>
            <c:if test="${not empty errors.passwordError}">
                <font color="red">
                ${errors.passwordError}
                </font><br/>
            </c:if>
            <input type="submit" value="Login" name="btAction"/>
            <input type="reset" value="Reset"/>
        </form><br/>
        <font color="red">
        ${requestScope.MESSAGE123}
        </font>
    </body>
</html>
