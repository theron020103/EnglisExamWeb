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
                        <li><a href="create.jsp">CREATE QƯIZ</a></li>
                    </ul>
                </nav><br>
            </div>
        </header>

        <div class="back">
            <form id="quizForm" action="question" method="get">
                <c:forEach items="${data}" var="u" varStatus="loop">
                    <div class="question">
                        <p style="font-weight: bold; font-size: 22px">Question ${loop.index + 1}:</p>
                        <p>${u.getContent()}</p>
                        <c:forEach items="${u.getSelect()}" var="option" varStatus="loop">
                            <p>
                                <input type="radio" name="${u.getId()}" value="${option}"/>
                                <label>${option}</label>
                            </p>
                        </c:forEach>

                    </div>
                </c:forEach>
                <input type="hidden" name="code" value="${code}">
                <input type="hidden" name="data" value="${data}">
                <input type="submit" name="submitQuiz" value="SUBMIT" class="btn-submit">
            </form>
        </div>

        <footer>
            <div id="countdown" style="font-size: 30px"></div>
            <a href="home.jsp" style="color: #B5F4FF">HOME</a>
            <a href="quiz" style="color: #B5F4FF">VIEW QUIZS</a>
            <a href="takeQuiz.jsp" style="color: #B5F4FF">TAKE QUIZ</a>
            <a href="result" style="color: #B5F4FF">VIEW RESULT</a>
            <a href="create.jsp" style="color: #B5F4FF">CREATE QUIZ</a>
        </footer>

        <script>
            // Đặt thời gian kết thúc đếm ngược (trong ví dụ này, 10 phút)
            var countdownTime = new Date();
            countdownTime.setMinutes(countdownTime.getMinutes() + 10);

            // Cập nhật đồng hồ đếm ngược mỗi giây
            var countdown = setInterval(function () {
                var now = new Date().getTime();
                var distance = countdownTime - now;

                var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
                var seconds = Math.floor((distance % (1000 * 60)) / 1000);

                // Hiển thị thời gian đếm ngược trong thẻ div có id là "countdown"
                document.getElementById("countdown").innerHTML = minutes + "m " + seconds + "s ";

                // Kiểm tra xem đã đến thời gian kết thúc chưa
                if (distance < 0) {
                    clearInterval(countdown); // Dừng đồng hồ đếm ngược
                    document.getElementById("countdown").innerHTML = "EXPIRED";
                    document.getElementById("quizForm").submit(); // Gửi biểu mẫu tự động khi hết giờ
                }
            }, 1000);

            var quizForm = document.getElementById('quizForm');

            // Lưu trữ URL hiện tại
            var currentURL = window.location.href;

            // Lắng nghe sự kiện click cho tất cả các liên kết
            var links = document.querySelectorAll('a');
            links.forEach(function (link) {
                link.addEventListener('click', function (event) {
                    // Kiểm tra xem URL của liên kết được bấm có khác với URL hiện tại không
                    if (link.href !== currentURL) {
                        // Nếu khác nhau, hiển thị cảnh báo
                        event.preventDefault();
                        var confirmation = confirm("Nếu bạn chuyển sang cửa sổ khác, bài thi sẽ tự động submit.");
                        if (confirmation) {
                            // Tự động submit biểu mẫu
                            quizForm.submit();
                        }
                    }
                });
            });


        </script>
    </body>
</html>
