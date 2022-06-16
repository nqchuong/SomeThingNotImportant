<%-- 
    Document   : adminPage
    Created on : May 11, 2022, 10:41:24 AM
    Author     : CHUONG
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
    </head>
    <body>
        
        <font color="green">
        Welcome, ${sessionScope.USERINFOR.name}<br/>
        </font>
        <form action="ProcessServlet">
            <input type="submit" value="Logout" name="btAction" />
        </form>
        <a href="createNewAccount.jsp">Click here to Sign Up</a><br/>
        <a href="listOfPromotions.jsp">Click here to View List User Add to Promotions</a><br/>

        <form action="ProcessServlet">
            <input type="text" name="txtSearchUser" value="" />
            <input type="submit" value="Search" name="btAction"/>
        </form>
        <font color="red">
        ${requestScope.MESSAGE}<br/>
        </font>
        <font color="red">
        ${requestScope.LISTPROMOMESSAGE}<br/>
        </font>
        <c:set var="resultPaging" value="${requestScope.SEARCHUSERPAGING}"/>
        <c:if test="${not empty resultPaging}">
            <h3>List All User</h3>
            <table border="1">
                <thead>
                    <tr>
                        <th>No</th>
                        <th>Name</th>
                        <th>User ID</th>
                        <th>Email</th>
                        <th>Phone</th>
                        <th>Photo</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${resultPaging}" var="dto" varStatus="counter">
                        <tr>
                            <td>${counter.count}</td>
                            <td>${dto.name}</td>
                            <td>${dto.username}</td>
                            <td>${dto.email}</td>
                            <td>${dto.phone}</td>
                            <td><img style="height: 80px;width: 80px" src="${pageContext.request.contextPath}/images/${dto.photo}"/></td>
                            <td>
                                <form action="ProcessServlet">
                                    <input type="submit" value="Delete" name="btAction" onclick="return confirm('Delete ${dto.username}')"/>
                                    <input type="submit" value="Add to Promotion" name="btAction" onclick="return confirm('Add ${dto.username} to Promotion')"/>
                                    <input type="text" name="cid" value="${dto.username}" hidden="true"/>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>   
            <nav aria-label="...">
                <ul class="pagination pagination-lg">
                    <c:forEach begin="1" end="${requestScope.NUMBERPAGE}" var="i">
                        <a class="page-link" href="ProcessServlet?txtSearchUser=${requestScope.SearchUser}&btAction=Search&index=${i}">${i}</a>
                    </c:forEach>
                </ul>
            </nav>
        </c:if> 

    </body>
    <style>
        a:visited{
            color: blue;
        }
        a:active {
            color: red;
        }
    </style>
</html>
