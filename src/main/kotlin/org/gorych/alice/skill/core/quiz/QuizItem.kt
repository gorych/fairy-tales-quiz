package org.gorych.alice.skill.core.quiz

data class QuizItem(
    val number: Int,
    val question: String,
    val answers: List<String>,
    val hints: List<String>
)
