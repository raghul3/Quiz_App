package com.raghul.quizapp.Data

data class Question(
    val text: String,
    val options: List<String>,
    val correctAnswer: String
)