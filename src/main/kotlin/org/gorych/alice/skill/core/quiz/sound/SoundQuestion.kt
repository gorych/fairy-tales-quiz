package org.gorych.alice.skill.core.quiz.sound

data class SoundQuestion(
    val number: Int,
    val soundId: String,
    val answers: List<String>,
    val hints: List<String>,
)
