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
    
    <div class="back1">
    <div class="listquiz-message">
    LIST QUIZS
    </div>
    
    <table class="center">
        <colgroup>
            <col style="width: 50px"> <!-- Cột 1 -->
            <col style="width: 50px"> <!-- Cột 2 -->
            <col> <!-- Cột 3 -->
            <col style="width: 100px"> <!-- Cột 4 -->
        </colgroup>
        <tr>
            <td>No</td>
            <td>Code</td>
            <td>Name</td>
            <td></td>
        </tr>
        <c:forEach items="${data}" var="u" varStatus="loop">
            <tr>
                <td>${loop.index + 1}</td>
                <td>${u.getCode()}</td>
                <td>${u.getName()}</td>
                <td><a href="quiz?mod=1&id=${u.getCode()}" style="color: blue">TAKE QUIZ</a></td>
            </tr>
        </c:forEach>
    </table><br><br><br><br><br>
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
