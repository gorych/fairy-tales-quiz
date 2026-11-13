package org.gorych.alice.skill.core.mode

import org.gorych.alice.skill.core.api.Button
import org.gorych.alice.skill.core.quiz.Achievement

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
