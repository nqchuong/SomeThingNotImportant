<%-- 
    Document   : updateInfor
    Created on : May 27, 2022, 7:51:29 AM
    Author     : CHUONG
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Information Page</title>
    </head>
    <body>
        <h1>Create A New User Page</h1>
        <h4>
            <font color="green">
            ${requestScope.MESSAGE}
            </font>
        </h4>
        <a href="subPage.jsp">Back to Home page</a> 
        <form action="ProcessServlet" enctype="multipart/form-data" method="POST">
            <c:set var="errors" value="${requestScope.CREATEUSERERROR}"/>
            Name: <input type="text" name="txtName" value="${sessionScope.USERINFOR.name}" placeholder="Enter user name"/><br/>
            <c:if test="${not empty errors.nameError}">
                <font color="red">
                ${errors.nameError}
                </font><br/>
            </c:if>
            Phone: <input type="text" name="txtPhone" value="${sessionScope.USERINFOR.phone}" placeholder="Enter user phone"/><br/>
            <c:if test="${not empty errors.phoneError}">
                <font color="red">
                ${errors.phoneError}
                </font><br/>
            </c:if>
            Email: <input type="text" name="txtEmail" value="${sessionScope.USERINFOR.email}" placeholder="Enter user email"/><br/>
            <c:if test="${not empty errors.emailError}">
                <font color="red">
                ${errors.emailError}
                </font><br/>
            </c:if>
            Photo: <img style="height: 50px;width: 50px" src="${pageContext.request.contextPath}/images/${sessionScope.USERINFOR.photo}"/><br/>
            <input type="file" name="file"><br/>
            <c:if test="${not empty errors.photoError}">
                <font color="red">
                ${errors.photoError}
                </font><br/>
            </c:if>
            <input type="text" name="txtStatus" value="Active" readonly="true" hidden="true"/><br/>
            <input type="submit" value="Update" name="btAction" /><br/>
        </form>
    </body>

</html>
