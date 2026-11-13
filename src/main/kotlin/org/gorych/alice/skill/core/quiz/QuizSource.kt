package org.gorych.alice.skill.core.quiz

import org.gorych.alice.skill.core.api.ApplicationState
import org.gorych.alice.skill.core.api.RequestObject
import org.gorych.alice.skill.core.api.Session
import org.gorych.alice.skill.core.api.SessionState
import org.gorych.alice.skill.core.api.State
import org.gorych.alice.skill.core.mode.GameMode

fun interface QuizSource {
    fun getQuiz(requestObject: RequestObject): Quiz

    val availableModes: Set<GameMode>
        get() = GameMode.entries.toSet()

    fun newQuiz(mode: GameMode): Quiz = getQuiz(
        RequestObject(
            session = Session(new = true),
            state = State(SessionState(), ApplicationState(quizName = null, gameMode = mode)),
        )
    )
}
