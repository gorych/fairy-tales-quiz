package org.gorych.alice.skill.core.quiz.truth_or_lie

data class TruthStatement(
    val number: Int,
    val statement: String,
    val isTrue: Boolean,
    val explanation: String,
    val hints: List<String>,
)
