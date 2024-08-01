<%-- 
    Document   : login
    Created on : 11 thg 3, 2024, 23:51:10
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- Coding By CodingNepal - codingnepalweb.com -->
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>Login</title>
  <!---Custom CSS File--->
  <link rel="stylesheet" href="css/style.css">
</head>
<body>
  <div class="container">
    <input type="checkbox" id="check">
    <div class="login form">
      <header>Login</header>
      <b style="color: blue">${suces}</b>
      <form action="User" method="get">
        <input type="text" placeholder="Enter your account" name="account" value="${account}">
        <input type="password" placeholder="Enter your password" name="pass" value="${pass}">
        <br><p style="color: red">${err}</p>
        <a href="#">Forgot password?</a>
        <input type="hidden" name="log" value="${log}">
        <input type="submit" name="login" class="button" value="LOGIN">
      </form>
      <div class="signup">
        <span class="signup">Don't have an account?
         <a href="signup.jsp">Sign up</a>
        </span>
      </div>
    </div>
  </div>
</body>
</html>