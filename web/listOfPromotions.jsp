<%-- 
    Document   : listOfPromotions
    Created on : May 24, 2022, 8:25:39 AM
    Author     : CHUONG
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List of Promotions Page</title>
    </head>
    <body>
        <font color="red">
        ${requestScope.MESSAGE}<br/>
        </font>

        <a href="adminPage.jsp">Click here to Continue Add User</a>
        <h3>List User Sub</h3>
        <font color="red">
        ${requestScope.RANKERROR}<br/>
        </font>
        <c:set var="listPromo" value="${sessionScope.LISTUSERPROMO}"/>
        <c:if test="${not empty listPromo}">
            <form action="ProcessServlet">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No</th>
                            <th>User ID</th>
                            <th>Rank</th>
                            <th>Status</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>

                        <c:forEach items="${listPromo}" var="dto" varStatus="counter">
                            <tr>
                                <td>${counter.count}</td>
                                <td>${dto.username}
                                    <input type="text" name="txtUsername${counter.count}" value="${dto.username}" hidden="true" />
                                </td>
                                <td>
                                    <input type="text" name="txtRank" value="5"/>
                                </td>
                                <td>${dto.status}</td>
                                <td>
                                    <input type="submit" value="Remove" name="btAction" onclick="return confirm('Remove ${dto.username} from list')"/>
                                    <input type="text" name="cid" value="${dto.username}" hidden="true"/>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>                                   
                <input type="submit" value="Add" name="btAction" />
            </form>
        </c:if>
    </body>
</html>
