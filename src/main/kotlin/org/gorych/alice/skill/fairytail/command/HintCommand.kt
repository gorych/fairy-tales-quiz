package org.gorych.alice.skill.fairytail.command

import org.gorych.alice.skill.core.api.RequestObject
import org.gorych.alice.skill.core.api.ResponseObject
import org.gorych.alice.skill.core.api.SessionState
import org.gorych.alice.skill.core.command.RequestSessionStatedQuestionCommand
import org.gorych.alice.skill.fairytail.quiz.Quiz

private const val DOUBT_INTENT_ID = "g911.doubt"
const val HELP_INTENT_ID = "g911.help"

class HintCommand : RequestSessionStatedQuestionCommand() {

    override fun name() = HintCommand.name()

    override fun canHandle(requestObject: RequestObject): Boolean {
        val hasHelpOrDoubtIntent: Boolean =
            requestObject.containsIntent(HELP_INTENT_ID) || requestObject.containsIntent(DOUBT_INTENT_ID)
        return hasHelpOrDoubtIntent
                && requestObject.hasCurrentQuestion()
                && requestObject.containsNextQuestionCommand()
    }

    override fun execute(
        requestObject: RequestObject,
        requestSessionState: SessionState,
        currentQuestionNumber: Int
    ): ResponseObject {
        val currentHintNumber = requestSessionState.previousHintNumber + 1

        val hintedQuestions: MutableSet<Int> = requestSessionState.hintedQuestions.toMutableSet()
        hintedQuestions.add(currentQuestionNumber)

        return ResponseObject.of(
            text = "${BEFORE_HINT_PHRASES.random()} ${Quiz.hintTo(currentQuestionNumber, currentHintNumber)}",
            state = requestSessionState.copy(hintedQuestions = hintedQuestions, previousHintNumber = currentHintNumber),
            endSession = false
        )
    }

    companion object {
        private val BEFORE_HINT_PHRASES = setOf("Без проблем.", "Хорошо, слушай подсказку.")

        fun name(): String = HintCommand::class.java.simpleName
    }
}