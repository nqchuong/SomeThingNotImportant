<%-- 
    Document   : subPage
    Created on : May 11, 2022, 10:41:35 AM
    Author     : CHUONG
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sub Page</title>
    </head>
    <body>
        <font color="green">
        Welcome, ${sessionScope.USERINFOR.name}<br/>
        </font>
        <form action="ProcessServlet">
            <input type="submit" value="Logout" name="btAction" />
        </form>
        <form action="ProcessServlet">
            <input type="submit" value="View History Promotion" name="btAction" />
        </form>
        <div>
            User Id: ${sessionScope.USERINFOR.username}<br/>
            <img style="height: 150px;width: 100px" src="${pageContext.request.contextPath}/images/${sessionScope.USERINFOR.photo}"/><br/>

            Name: ${sessionScope.USERINFOR.name}<br/>
            Email:${sessionScope.USERINFOR.email}<br/>
            Phone:${sessionScope.USERINFOR.phone}<br/>
            Role: ${sessionScope.USERINFOR.role}<br/>
            <a href="updateInfor.jsp">Update your information</a><br/>
            <a href="changePassword.jsp">Change your password</a><br/>
        </div>      
    </body>
</html>
