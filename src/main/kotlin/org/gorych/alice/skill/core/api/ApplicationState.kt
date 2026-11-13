package org.gorych.alice.skill.core.api

import org.gorych.alice.skill.core.mode.GameMode
import org.gorych.alice.skill.core.quiz.Quiz

data class ApplicationState(
    val quizName: String?,
    val bonusQuiz: Boolean = false,
    val gameMode: GameMode? = null,
) {

    constructor(quiz: Quiz) : this(
        quizName = quiz.name(),
        bonusQuiz = quiz.bonusQuiz,
        gameMode = quiz.gameMode,
    )

    companion object {
        val KEEP_AS_IS: ApplicationState? = null
    }
}
