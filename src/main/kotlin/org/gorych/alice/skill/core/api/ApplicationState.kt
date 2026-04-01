package org.gorych.alice.skill.core.api

import org.gorych.alice.skill.core.quiz.Quiz

data class ApplicationState(
    val quizName: String?,
    val bonusQuiz: Boolean = false,
) {

    constructor(quiz: Quiz) : this(quiz.name(), bonusQuiz = quiz.bonusQuiz)
}