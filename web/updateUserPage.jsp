<%-- 
    Document   : updateUserPage
    Created on : May 23, 2022, 9:44:12 AM
    Author     : CHUONG
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update User Page</title>
    </head>
    <body>
        <h1>Update A User Page</h1>
        <h4>
            <font color="green">
            ${requestScope.MESSAGE}
            </font>
        </h4>
        <a href="adminPage.jsp">Back to Home page</a> 
        <h3>Update Information</h3>
        <form action="ProcessServlet" enctype="multipart/form-data" method="POST">
            Name: <input type="text" name="txtName" value="${sessionScope.USERINFOR.name}" placeholder="Enter your name"/><br/>
            Phone: <input type="text" name="txtPhone" value="${sessionScope.USERINFOR.phone}" placeholder="Enter your phone"/><br/>
            Email: <input type="text" name="txtEmail" value="${sessionScope.USERINFOR.email}" placeholder="Enter your email"/><br/>
            Photo: <input type="file" name="file"><br/>
            <input type="text" name="txtStatus" value="Active" readonly="true" hidden="true"/><br/>
            <input type="submit" value="Create User" name="btAction" /><br/>
        </form>
        <h3>Update Password</h3>    
        <form action="ProcessServlet">
            Old Password:<input type="password" name="txtOldPassword" value="1" placeholder="Enter your old password"/><br/>
            New Password:<input type="password" name="txtNewPassword" value="1" placeholder="Enter your new password"/><br/>
            Confirm Password:<input type="password" name="txtConfirmPassword" value="1" placeholder="Enter confirm password"/><br/>
        </form>
    </body>
</html>
