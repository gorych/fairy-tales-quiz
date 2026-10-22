package org.gorych.alice.skill.core.quiz

data class Achievement(
    val rightAnswersCount: Int,
    val text: String,
    val tts: String,
    val imageId: String
)
