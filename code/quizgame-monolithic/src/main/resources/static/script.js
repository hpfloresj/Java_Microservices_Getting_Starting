// Global parameter
var endpoint = "http://localhost:8082";
var timeLimit = 60 * 4;
var questionList;
var question = {};
var currentQuestion = 0;
var quizOver = false;
var iSelectedAnswer = {
    id: 0,
    user: {},
    responses: []
};
var timeCount = timeLimit;
var intId = -1;
var scorePass = 80;

function resetResponses() {
    var iSelectedAnswer = {
        id: 0,
        user: {},
        responses: []
    };
    return iSelectedAnswer;
}

function statistic(alias) {
    $.ajax({
        url: endpoint + "/attempt?alias=" + alias
    }).then(function(data) {
        console.log(data);
        $('#stats-body').empty();
        data.forEach(function(row) {
            $('#stats-body').append(
                '<tr>' +
                '<td>' + row.id + '</td>' +
                '<td>' + row.quizSize + '</td>' +
                '<td>' + row.valid + '</td>' +
                '<td>' + row.failed + '</td>' +
                '<td>' + ((row.score < scorePass) ? '<span class="score badge badge-danger">' + row.score + '</span>' : '<span class="score badge badge-success">' + row.score + '</span>') + '</td>' +
                '</tr>'
            );
        });
    });
}

function endGame() {
    $("#clock").removeClass("red-text");
    resetGame();
    displayScore();
}

function formatTime(toFormat) {
    if (toFormat < 10) {
        return "0" + toFormat;
    }
    return toFormat;
}

function timed() {
    var secs = Math.floor(timeCount / 4) % 60;
    var mins = Math.floor(timeCount / 240);
    $("#clock").text(mins + ":" + formatTime(secs));
    if (timeCount === 0) {
        endGame();
    } else if (timeCount <= 10 * 4) {
        $("#clock").addClass("red-text");
    }
    timeCount--;
}

function displayScore() {
    var alias = $('#username').val();
    if (alias == '') {
        alert('Name is required!');
        return false;
    }
    iSelectedAnswer.user = {
        id: null,
        alias: alias
    };

    console.log("Send the data using post");
    console.log(iSelectedAnswer);
    $.ajax({
        url: endpoint + '/attempt',
        type: 'POST',
        data: JSON.stringify(iSelectedAnswer),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function(data) {
            $('#quiz-message').show();
            if (data.score >= scorePass) {
                $("#quiz-message").addClass("green-text");
                $('#quiz-message').empty().append("&#128521; your score is: " + data.score);
            } else {
                $("#quiz-message").addClass("red-text");
                $('#quiz-message').empty().append("&#128543; your score is: " + data.score);
            }
            statistic($('#username').val());
        }
    });
}

function addResponse(question, choise) {
    var questionResponse = {
        question: {
            id: question
        },
        answer: {
            id: choise
        }
    };
    iSelectedAnswer.responses.push(questionResponse);
}

function resetGame() {
    clearInterval(intId);
    $("#clock").text("Quiz time completed!");
    $("#start-game").show();
    $("#next").hide();
    $("#question-card").hide();
    $("#quiz-message").hide();
    timeCount = timeLimit;
    questionList = [];
    currentQuestion = 0;
    quizOver = true;
}

function nextQuestion() {
    if (!quizOver) {
        var val = $("input[type='radio']:checked").val();
        if (val == undefined) {
            $("#quiz-message").text("Please select an answer");
            $("#quiz-message").show();
        } else {
            $("#quiz-message").hide();
            addResponse(questionList[currentQuestion].id, val);
            currentQuestion++; // Since we have already displayed the first question on DOM ready
            if (currentQuestion < questionList.length) {
                displayCurrentQuestion();
            } else {
                resetGame();
                displayScore();
                return false;
            }
        }
    }
}

function displayCurrentQuestion() {
    console.log("In display current Question");
    question = questionList[currentQuestion];
    var choiceList = $(document).find("#choiceList");
    var numChoices = question.answers.length;
    $("#question").text(question.prompt);
    $(choiceList).find("li").remove();
    if (question.description != null) {
        $("#question-body").text(question.description);
    } else {
        $("#question-body").text("");
    }
    var choice;
    for (i = 0; i < numChoices; i++) {
        choice = questionList[currentQuestion].answers[i];
        $('<li><input type="radio" class="puntero" value=' + choice.id + ' name="dynradio" />' + choice.displayText + '</li>').appendTo(choiceList);
    }
    $("#question-current").text((currentQuestion + 1) + " of " + questionList.length);
}

function startGame() {
    if ($('#username').val() == '') {
        alert('Name is required!');
        return false;
    }

    $.ajax({
        url: endpoint + "/quiz/random",
        dataType: "json",
        type: 'get',
        cache: false
    }).then(function(data) {
        iSelectedAnswer = resetResponses();
        questionList = JSON.parse(JSON.stringify(data));
        console.log(questionList);
        displayCurrentQuestion();
        $("#timer-card").show();
        $("#question-card").show();
        $("#start-game").hide();
        $("#next").show();
        $("#quiz-message").removeClass("green-text");
        $("#quiz-message").removeClass("red-text");
        $("#quiz-message").hide();
        quizOver = false;
        intId = setInterval(timed, 250);
        statistic($('#username').val());
    });
}

$(document).ready(function() {
    $("#timer-card").hide();
    $("#question-card").hide();
    $("#next").hide();
});