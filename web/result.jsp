<%-- 
    Document   : home
    Created on : 11 thg 3, 2024, 23:52:12
    Author     : ACER
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>English</title>
    <link rel="stylesheet" href="css/csshome.css">
</head>
<body>
    <header>
        <div class="container">
            <a href="User?mod=1" class="btn-login">LOGOUT</a>
            <h1 class="large-heading">English quiz</h1><br>
            <nav>
                <ul>
                    <li><a href="home.jsp">HOME</a></li>
                    <li><a href="quiz">VIEW QUIZS</a></li>
                    <li><a href="takeQuiz.jsp">TAKE QUIZ</a></li>
                    <li><a href="result">VIEW RESULT</a></li>
                    <li><a href="create.jsp">CREATE QUIZ</a></li>
                </ul>
            </nav><br>
        </div>
    </header>
    
    <div class="listquiz-message">
        <p>Quiz: ${r.getId_quiz()}</p><p>${name}</p>
        <p>${r.getNumOfCorrect()}/${count} - ${r.getPoint()}</p>
    </div>
    
    <div class="back">
        <c:forEach items="${result}" var="u" varStatus="loop">
            <div class="question">
                <p style="font-weight: bold; font-size: 22px">Question ${loop.index + 1}:</p>
                <p>${u.getQ().getContent()}</p>
                    <c:forEach items="${u.getQ().getSelect()}" var="option" varStatus="optionLoop">
                        <p>
                            <c:choose>
                                <c:when test="${u.getOption() == option}">
                                    <input type="radio" name="${u.getQ().getId()}" value="${option}" checked="checked" disabled>
                                </c:when>
                                <c:otherwise>
                                    <input type="radio" name="${u.getQ().getId()}" value="${option}" disabled>
                                </c:otherwise>
                            </c:choose>
                            <label>${option}</label>
                        </p>
                    </c:forEach>
                        <p><p style="color: red">Correct Answer:</p> ${u.getAnswer()}</p><br>
            </div>
        </c:forEach>
    </div>
    
    <footer>
        <p>&copy; 2024 ENGLISH QUIZ</p>
        <a href="home.jsp" style="color: #B5F4FF">HOME</a>
        <a href="quiz" style="color: #B5F4FF">VIEW QUIZS</a>
        <a href="takeQuiz.jsp" style="color: #B5F4FF">TAKE QUIZ</a>
        <a href="result" style="color: #B5F4FF">VIEW RESULT</a>
        <a href="create.jsp" style="color: #B5F4FF">CREATE QUIZ</a>
    </footer>
</body>
</html>
