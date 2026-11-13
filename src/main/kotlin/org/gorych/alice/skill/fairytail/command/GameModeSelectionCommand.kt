package org.gorych.alice.skill.fairytail.command

import org.gorych.alice.skill.core.api.ApplicationState
import org.gorych.alice.skill.core.api.RequestObject
import org.gorych.alice.skill.core.api.ResponseObject
import org.gorych.alice.skill.core.api.SessionState
import org.gorych.alice.skill.core.command.Command
import org.gorych.alice.skill.core.mode.GameMode
import org.gorych.alice.skill.core.mode.ModeProfiles
import org.gorych.alice.skill.core.quiz.QuizSource

class GameModeSelectionCommand(private val quizzes: QuizSource) : Command {

    override fun name(): String = GameModeSelectionCommand.name()

    override fun canHandle(requestObject: RequestObject): Boolean {
        val mode = requestedMode(requestObject) ?: return false
        return requestObject.containsCommand(name()) && quizzes.availableModes.contains(mode)
    }

    override fun execute(requestObject: RequestObject): ResponseObject {
        val mode = requireNotNull(requestedMode(requestObject)) { "Game mode must be resolved" }
        val profile = ModeProfiles.of(mode)
        val quiz = quizzes.newQuiz(mode)

        return ResponseObject.ofPrompt(
            prompt = quiz.prompt(1, profile.openingPhrase),
            buttons = profile.buttons,
            sessionState = SessionState(1, setOf(NextQuestionCommand.name())),
            endSession = false,
            appState = ApplicationState(quizName = quiz.name(), bonusQuiz = quiz.bonusQuiz, gameMode = mode),
        )
    }

    private fun requestedMode(requestObject: RequestObject): GameMode? =
        GameMode.entries.firstOrNull { requestObject.containsIntent(it.selectionIntentId) }

    companion object {
        fun name(): String = GameModeSelectionCommand::class.java.simpleName
    }
}
