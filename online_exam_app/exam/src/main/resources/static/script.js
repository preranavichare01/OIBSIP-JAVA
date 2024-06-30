// Global variables
let currentQuestion = 0;
let score = 0;
let wrongAttempts = 0;
let rightAttempts = 0;
let timer;
let totalTime = 300; // 5 minutes in seconds

// Sample MCQ questions
const questions = [
    {
        question: "What is HTML?",
        options: ["Markup Language", "Programming Language", "Styling Language"],
        answer: "Markup Language"
    },
    {
        question: "What does CSS stand for?",
        options: ["Cascading Style Sheets", "Creative Style Sheets", "Computer Style Sheets"],
        answer: "Cascading Style Sheets"
    },
    {
        question: "Which HTML tag is used for creating a hyperlink?",
        options: ["<display>", "<align>", "<float>"],
        answer: "<display>"
    },
    {
        question: "Which tag is used to define a paragraph in HTML?",
        options: ["<p>", "<para>", "<paragraph>"],
        answer: "<p>"
    },
    {
        question: "Which symbol is used for single-line comments in JavaScript?",
        options: ["//", "--", "/*"],
        answer: "//"
    },
    // Add more questions here
];

// Function to start the timer
function startTimer() {
    timer = setInterval(() => {
        totalTime--;
        if (totalTime < 0) {
            clearInterval(timer);
            submitQuiz();
        }
        displayTimer(totalTime);
    }, 1000);
}

// Function to display the timer
function displayTimer(seconds) {
    const minutes = Math.floor(seconds / 60);
    const remainingSeconds = seconds % 60;
    const timerDisplay = `${minutes}:${remainingSeconds < 10 ? '0' : ''}${remainingSeconds}`;
    document.getElementById('timer').textContent = timerDisplay;
}

// Function to load question
function loadQuestion(index) {
    const question = questions[index];
    const questionElement = document.getElementById('question');
    const optionsElement = document.getElementById('options');
    
    questionElement.textContent = `${index + 1}. ${question.question}`;
    optionsElement.innerHTML = '';

    for (const option of question.options) {
        const radioBtn = document.createElement('input');
        radioBtn.type = 'radio';
        radioBtn.name = 'option';
        radioBtn.value = option;
        radioBtn.required = true;

        const label = document.createElement('label');
        label.textContent = option;

        const br = document.createElement('br');

        optionsElement.appendChild(radioBtn);
        optionsElement.appendChild(label);
        optionsElement.appendChild(br);
    }
}

// Function to validate and check answer
function checkAnswer() {
    const selectedOption = document.querySelector('input[name="option"]:checked');
    if (!selectedOption) {
        alert('Please select an option');
        return;
    }

    const userAnswer = selectedOption.value;
    const currentQuestionObj = questions[currentQuestion];

    if (userAnswer === currentQuestionObj.answer) {
        score += 1;
        rightAttempts += 1;
    } else {
        wrongAttempts += 1;
    }

    // Move to the next question or finish the quiz
    if (currentQuestion < questions.length - 1) {
        currentQuestion++;
        loadQuestion(currentQuestion);
    } else {
        submitQuiz();
    }
}

// Function to submit the quiz
function submitQuiz() {
    clearInterval(timer);

    // Calculate score
    const totalQuestions = questions.length;
    const percentageScore = (score / totalQuestions) * 100;
    const status = percentageScore >= 60 ? 'Pass' : 'Fail';

    // Prepare result data
    const resultData = {
        name: localStorage.getItem('username'),
        score: score,
        totalQuestions: totalQuestions,
        rightAttempts: rightAttempts,
        wrongAttempts: wrongAttempts,
        status: status
    };

    // Store result in local storage
    localStorage.setItem('resultData', JSON.stringify(resultData));

    // Redirect to result page
    window.location.href = 'result.html';
}

// Event listener for quiz form submission
document.getElementById('mcqForm').addEventListener('submit', function(event) {
    event.preventDefault();
    checkAnswer();
});

// Function to display username
function displayUsername() {
    const username = localStorage.getItem('username');
    if (username) {
        document.getElementById('username').textContent = username;
    }
}

// Function to start the quiz
function startQuiz() {
    displayUsername();
    loadQuestion(currentQuestion);
    startTimer();
}

// On page load
window.onload = startQuiz;
