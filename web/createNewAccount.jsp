<%-- 
    Document   : createNewAccount
    Created on : May 9, 2022, 3:06:33 PM
    Author     : CHUONG
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create User Page</title>
    </head>
    <body>
        <h1>Create A New User Page</h1>
        <h4>
            <font color="green">
            ${requestScope.MESSAGE}
            </font>
        </h4>
        <a href="adminPage.jsp">Back to Home page</a> 
        <form action="ProcessServlet" enctype="multipart/form-data" method="POST">
            <c:set var="errors" value="${requestScope.CREATEUSERERROR}"/>
            UserId:<input type="text" name="txtUsername" value="onemore" placeholder="Enter user User id"/><br/>
            <c:if test="${not empty errors.usernameError}">
                <font color="red">
                ${errors.usernameError}
                </font><br/>
            </c:if>
            Password: <input type="password" name="txtPassword" value="1" placeholder="Enter user password"/><br/>
            <c:if test="${not empty errors.passwordError}">
                <font color="red">
                ${errors.passwordError}
                </font><br/>
            </c:if>
            Confirm Password: <input type="password" name="txtConfirmPassword" value="1" placeholder="Confirm user password"/><br/>
            <c:if test="${not empty errors.cofirmPasswordError}">
                <font color="red">
                ${errors.cofirmPasswordError}
                </font><br/>
            </c:if>
            Name: <input type="text" name="txtName" value="test upload" placeholder="Enter user name"/><br/>
            <c:if test="${not empty errors.nameError}">
                <font color="red">
                ${errors.nameError}
                </font><br/>
            </c:if>
            Phone: <input type="text" name="txtPhone" value="0123456789" placeholder="Enter user phone"/><br/>
            <c:if test="${not empty errors.phoneError}">
                <font color="red">
                ${errors.phoneError}
                </font><br/>
            </c:if>
            Email: <input type="text" name="txtEmail" value="test@gmail.com" placeholder="Enter user email"/><br/>
            <c:if test="${not empty errors.emailError}">
                <font color="red">
                ${errors.emailError}
                </font><br/>
            </c:if>
            Photo: <input type="file" name="file"><br/>
            <c:if test="${not empty errors.photoError}">
                <font color="red">
                ${errors.photoError}
                </font><br/>
            </c:if>
            Role: <input type="text" name="txtRole" value="Sub"/><br/>
            <input type="text" name="txtStatus" value="Active" readonly="true" hidden="true"/><br/>
            <input type="submit" value="Create User" name="btAction" /><br/>
        </form>
    </body>
</html>
