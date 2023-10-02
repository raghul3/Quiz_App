package com.raghul.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import com.raghul.quizapp.Data.Question

class MainActivity : AppCompatActivity() {

    private lateinit var questionTextView: TextView
    private lateinit var optionsRadioGroup: RadioGroup
    private lateinit var submitButton: Button

    private val questions = mutableListOf(
        Question("What is the capital of India?", listOf("Berlin", "Madrid", "Delhi", "Rome"), "Delhi"),
        Question("Which planet is known as the Red Planet?", listOf("Mars", "Venus", "Jupiter", "Saturn"), "Mars"),
        Question("What artist has the most streams on Spotify?", listOf("Drake","Justin Bieber","Travis Scott","Taylor Swift"),"Drake"),
        Question("Are you a human?", listOf("Yes","No"),"Yes"),
        Question("How many World Cup did Brazil win?", listOf("2","7","3","5"),"5"),
        Question("How many continents are in the world?", listOf("None","2","7","5"),"7"),
        Question("Name the largest mammal?", listOf("Elephant","Tiger","Blue Whale","Seal"),"Blue Whale"),
        Question("Name the largest planet of our Solar System?", listOf("Pluto","Jupiter","Mars","Sun"),"Jupiter"),
        Question("What type of gas is absorbed by plants?", listOf("Oxygen","Co2","None","Helium"),"Co2"),
        Question(" Name the longest river on the Earth?", listOf("Ganga","Kauvery","Nile","Yamuna"),"Nile")

        // Add more questions as needed
    )

    private var currentQuestionIndex = 0
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.my_toolbar))


        questionTextView = findViewById(R.id.questionTextView)
        optionsRadioGroup = findViewById(R.id.optionsRadioGroup)
        submitButton = findViewById(R.id.submitButton)

        displayQuestion()

        submitButton.setOnClickListener {
            checkAnswer()
        }
    }

    private fun displayQuestion() {
        val currentQuestion = questions[currentQuestionIndex]

        questionTextView.text = currentQuestion.text

        optionsRadioGroup.removeAllViews()
        for (option in currentQuestion.options) {
            val radioButton = RadioButton(this)
            radioButton.text = option
            optionsRadioGroup.addView(radioButton)
        }
    }

    private fun checkAnswer() {
        val checkedRadioButtonId = optionsRadioGroup.checkedRadioButtonId

        if (checkedRadioButtonId != -1) {
            val checkedRadioButton = findViewById<RadioButton>(checkedRadioButtonId)
            val userAnswer = checkedRadioButton.text.toString()

            val currentQuestion = questions[currentQuestionIndex]

            if (userAnswer == currentQuestion.correctAnswer) {
                score++
            }

            currentQuestionIndex++

            if (currentQuestionIndex < questions.size) {
                displayQuestion()
            } else {
                // Display final score or navigate to a new activity
                // For simplicity, let's just display the score in a toast message
                Toast.makeText(this, "Your score: $score/${questions.size}", Toast.LENGTH_SHORT).show()
            }
        } else {
            // No option selected, show a message or handle it as needed
            Toast.makeText(this, "Please select an option", Toast.LENGTH_SHORT).show()
        }
    }
}