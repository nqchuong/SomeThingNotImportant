<%-- 
    Document   : historyPromotions
    Created on : May 23, 2022, 1:01:24 PM
    Author     : CHUONG
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>History Promotion Page</title>
    </head>
    <body>
        <font color="green">
        Welcome, ${sessionScope.USERINFOR.name}<br/>
        </font>
        <a href="subPage.jsp">Back to Home page</a> 

        <c:set var="result" value="${requestScope.HISTORYPROMO}"/>
        <c:if test="${not empty result}">
            <table border="1">
                <thead>
                    <tr>
                        <th>Username</th>
                        <th>Rank</th>
                        <th>Status</th>
                        <th>Promotion Code</th>
                        <th>Create Date</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${result}" var="dto" varStatus="counter">
                        <tr>
                            <td>${dto.username}</td>
                            <td>${dto.rank}</td>
                            <td>${dto.status}</td>
                            <td>${dto.id}</td>
                            <td>${dto.createDate}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if> 

    </body>
</html>
