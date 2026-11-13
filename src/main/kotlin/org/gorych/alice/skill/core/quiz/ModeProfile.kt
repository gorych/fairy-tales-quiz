package org.gorych.alice.skill.core.quiz

import org.gorych.alice.skill.core.api.Button

data class ModeProfile(
    val achievements: List<Achievement>,
    val excellentScore: Int,
    val goodScore: Int,
    val badScore: Int,
    val openingPhrase: String,
    val offersBonusQuiz: Boolean,
    val buttons: List<Button>,
    val rightAnswerPhrases: Set<String>,
    val nextQuestionPhrases: Set<String>,
    val wrongAnswerPhrases: Set<String>,
)
