<%-- 
    Document   : home
    Created on : 11 thg 3, 2024, 23:52:12
    Author     : ACER
--%>

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
    
    <div class="back">
        <form action="quiz" method="post">
            <div class="listquiz-message">
                <p>CREATE NEW QUIZ</p>
                <p>Quiz: ${code}</p><br>
                 ${name}<br>
            </div>
            <div id="question">
                <div class="question">
                    <p style="font-weight: bold; font-size: 22px">Question 1:</p><br>
                    <input type="text" name="question1" value="${question[1]}" placeholder="Enter question" class="textLeft"><br>
                    <input type="text" name="answer1" value="${answer[1]}" placeholder="Enter answer" class="textLeft"><br>
                    <input type="text" name="option1_1" value="${option[1][1]}" placeholder="Enter option 1" class="textLeft"><br>
                    <input type="text" name="option1_2" value="${option[1][2]}" placeholder="Enter option 2" class="textLeft"><br>
                    <div id="1"></div>
                    <input type="button" onclick="addOption('1')" value="MORE OPTION" class="btn-submitLeft">
                </div><br>
            </div>
            <input type="button" onclick="addQuestion()" value="MORE QUESTION" class="btn-submit"><br>
            <input type="hidden" name="name" value="${name}">
            <input type="hidden" name="code" value="${code}">
            <input type="submit" name="submit" value="CREATE QUIZ" class="btn-submit">
        </form>
    </div>

    <footer>
        <p>&copy; 2024 ENGLISH QUIZ</p>
        <a href="home.jsp" style="color: #B5F4FF">HOME</a>
        <a href="quiz" style="color: #B5F4FF">VIEW QUIZS</a>
        <a href="takeQuiz.jsp" style="color: #B5F4FF">TAKE QUIZ</a>
        <a href="result" style="color: #B5F4FF">VIEW RESULT</a>
        <a href="create.jsp" style="color: #B5F4FF">CREATE QUIZ</a>
    </footer>
    
    <script>
    function addQuestion() { 
    var questionContainers = document.querySelectorAll('.question');

    var questionContainer = document.createElement("div");
    questionContainer.className = "question";

    var questionIndex = questionContainers.length + 1;

    var questionTitle = document.createElement("p");
    questionTitle.style.fontWeight = "bold";
    questionTitle.style.fontSize = "22px";
    questionTitle.innerHTML = "Question " + questionIndex + ":";
    questionContainer.appendChild(questionTitle);

    var questionInput = document.createElement("input");
    questionInput.type = "text";
    questionInput.name = "question" + questionIndex;
    questionInput.value = "${question[questionIndex]}";
    questionInput.placeholder = "Enter question";
    questionInput.className = "textLeft";
    questionContainer.appendChild(questionInput);

    var answerInput = document.createElement("input");
    answerInput.type = "text";
    answerInput.name = "answer" + questionIndex;
    answerInput.value = "${answer[questionIndex]}";
    answerInput.placeholder = "Enter answer";
    answerInput.className = "textLeft";
    questionContainer.appendChild(document.createElement("br"));
    questionContainer.appendChild(answerInput);

    var option1Input = document.createElement("input");
    option1Input.type = "text";
    option1Input.name = "option" + questionIndex + "_1";
    option1Input.value = "${option[questionIndex][1]}";
    option1Input.placeholder = "Enter option 1";
    option1Input.className = "textLeft";
    questionContainer.appendChild(document.createElement("br"));
    questionContainer.appendChild(option1Input);

    var option2Input = document.createElement("input");
    option2Input.type = "text";
    option2Input.name = "option" + questionIndex + "_2";
    option2Input.value = "${option[questionIndex][2]}";
    option2Input.placeholder = "Enter option 2";
    option2Input.className = "textLeft";
    questionContainer.appendChild(document.createElement("br"));
    questionContainer.appendChild(option2Input);

    var additionalOptionsContainer = document.createElement("div");
    additionalOptionsContainer.id = questionIndex;
    questionContainer.appendChild(additionalOptionsContainer);

    var moreOptionButton = document.createElement("input");
    moreOptionButton.type = "button";
    moreOptionButton.value = "MORE OPTION";
    moreOptionButton.className = "btn-submitLeft";
    moreOptionButton.onclick = function() {
        addOption(additionalOptionsContainer.id);
    };

    var deleteQuestionButton = document.createElement("input");
    deleteQuestionButton.type = "button";
    deleteQuestionButton.value = "DELETE QUESTION";
    deleteQuestionButton.className = "btn-delete";
    deleteQuestionButton.onclick = function() {
        questionContainer.parentNode.removeChild(questionContainer);
        updateQuestionIndexes();
    };

    questionContainer.appendChild(document.createElement("br"));
    questionContainer.appendChild(moreOptionButton);
    questionContainer.appendChild(deleteQuestionButton);

    document.getElementById('question').appendChild(questionContainer);
}

function updateQuestionIndexes() {
    var questionContainers = document.querySelectorAll('.question');

    for (var i = 0; i < questionContainers.length; i++) {
        var questionTitle = questionContainers[i].querySelector('p');
        var questionIndex = i + 1;
        questionTitle.innerHTML = "Question " + questionIndex + ":";

        var questionInputs = questionContainers[i].querySelectorAll('input[name^="question"]');
        for (var j = 0; j < questionInputs.length; j++) {
            questionInputs[j].name = "question" + questionIndex;
            questionInputs[j].value = "${question[questionIndex]}";
        }

        var answerInputs = questionContainers[i].querySelectorAll('input[name^="answer"]');
        for (var j = 0; j < answerInputs.length; j++) {
            answerInputs[j].name = "answer" + questionIndex;
            answerInputs[j].value = "${answer[questionIndex]}";
        }
        
        var optionInputs = questionContainers[i].querySelectorAll('input[name^="option"]');
        for (var j = 0; j < answerInputs.length; j++) {
            var x=j+1;
            optionInputs[j].name = "option" + questionIndex + "_" + x; ;
            optionInputs[j].value = "${option[questionIndex][x]}}" ;
        }

        var additionalOptionsContainer = questionContainers[i].querySelector('div[id^="additionalOptions"]');
        additionalOptionsContainer.id = "additionalOptions" + questionIndex;
    }
}



function updateOptionIndexes(containerId) {
    var additionalOptionsContainer = document.getElementById(containerId);
    var optionInputs = additionalOptionsContainer.querySelectorAll('input[type="text"][name^="option"]');
    
    for (var i = 0; i < optionInputs.length; i++) {
        var optionIndex = i + 3;
        optionInputs[i].name = "option" + containerId + "_" + optionIndex;
        optionInputs[i].valưe = "${option[containerId][optionIndex]}";
        optionInputs[i].setAttribute("placeholder", "Enter option " + optionIndex);
    }
}

function addOption(containerId) {
    var additionalOptionsContainer = document.getElementById(containerId);
    var optionInputs = additionalOptionsContainer.querySelectorAll('input[type="text"][name^="option"]');
    var optionNumber = optionInputs.length + 3;
    var newOptionInput = document.createElement("input");
    newOptionInput.type = "text";
    newOptionInput.name = "option" + containerId + "_" + optionNumber;
    newOptionInput.value = "${option[containerId][optionNumber]}";
    newOptionInput.placeholder = "Enter option " + optionNumber;
    newOptionInput.className = "textLeft";

    // Create delete button
    var deleteButton = document.createElement("input");
    deleteButton.type = "button";
    deleteButton.value = "Delete";
    deleteButton.onclick = function() {
        additionalOptionsContainer.removeChild(newOptionInput);
        additionalOptionsContainer.removeChild(deleteButton);
        updateOptionIndexes(containerId); // Cập nhật lại index sau khi xóa option
    };

    additionalOptionsContainer.appendChild(newOptionInput);
    additionalOptionsContainer.appendChild(deleteButton);
}


</script>

</body>
</html>
