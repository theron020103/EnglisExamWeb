<%-- 
    Document   : oneQuestion
    Created on : 22 thg 3, 2024, 08:48:18
    Author     : ACER
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:forEach items="${data}" var="u" varStatus="loop">
            <c:choose>
                <c:when test="${loop.index == 0 or u.answeredCorrect}">
                    <div class="question">
                        <p style="font-weight: bold; font-size: 22px">Question ${loop.index + 1}:</p>
                        <p>${u.getContent()}</p>
                        <c:forEach items="${u.getSelect()}" var="option" varStatus="innerLoop">
                            <p>
                                <input type="radio" name="${u.getId()}" value="${option}"/>
                                <label>${option}</label>
                            </p>
                        </c:forEach>
                    </div>
                </c:when>
            </c:choose>
        </c:forEach>

    </body>
</html>
