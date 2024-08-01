<%-- 
    Document   : signup
    Created on : 11 thg 3, 2024, 23:51:35
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
  <title>Sign up</title>
  <!---Custom CSS File--->
  <link rel="stylesheet" href="css/style.css">
</head>
    <body>
        <div class="container">
        <input type="checkbox" id="check">    
        <div class="registration form">
            <header>Signup</header>
            <form action="User" method="post">
                <input type="text" placeholder="Enter your name" name="name" value="${name}">
                <input type="text" placeholder="Create your account" name="account" value="${account}">
                <input type="password" placeholder="Create a password" name="pass" value="${pass}">
                <input type="password" placeholder="Confirm your password" name="pass2" value="${pass2}">          
                <br><p style="color: red">${err}</p>
                <input type="submit" name="signup" class="button" value="SIGN UP">
            </form>
            <div class="signup">
                <span class="signup">Already have an account?
                     <a href="login.jsp">Login</a>
                </span>    
            </div>
        </div>
        </div>
    </body>
</html>
