<%-- 
    Document   : changePassword
    Created on : May 27, 2022, 10:24:38 AM
    Author     : CHUONG
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h3>Update Password</h3>
        <font color="green">
        ${requestScope.PSWUPDATEMESSAGE}<br/>
        </font>
        <a href="subPage.jsp">Back to Home page</a> 
        <form action="ProcessServlet">
            <c:set var="pswErr" value="${requestScope.PSWUPDATEERROR}"/>
            Old Password:<input type="password" name="txtOldPassword" value="" placeholder="Enter your old password"/><br/>
            <c:if test="${not empty pswErr.pswOldErr}">
                <font color="red">
                ${pswErr.pswOldErr}<br/>
                </font>
            </c:if>
            New Password:<input type="password" name="txtNewPassword" value="" placeholder="Enter your new password"/><br/>
            <c:if test="${not empty pswErr.pswNewErr}">
                <font color="red">
                ${pswErr.pswNewErr}<br/>
                </font>
            </c:if>
            Confirm Password:<input type="password" name="txtConfirmPassword" value="" placeholder="Enter confirm password"/><br/>
            <c:if test="${not empty pswErr.pswConfirmErr}">
                <font color="red">
                ${pswErr.pswConfirmErr}<br/>
                </font>
            </c:if>
            <input type="submit" value="Change Password" name="btAction"/><br/>
        </form>
    </body>
</html>
