package org.gorych.alice.skill.fairytail.command

import org.gorych.alice.skill.core.api.*
import org.gorych.alice.skill.core.command.Command
import org.gorych.alice.skill.core.quiz.Quiz


class PlayingAgreementCommand : Command {

    override fun name(): String = PlayingAgreementCommand.name()

    override fun canHandle(requestObject: RequestObject): Boolean {
        return requestObject.containsIntent(AGREEMENT_INTENT_ID)
    }

    override fun execute(requestObject: RequestObject, quiz: Quiz): ResponseObject {
        if (!requestObject.containsPlayingAgreementCommand()) {
            return ResponseObject.ofUnclearCommand(requestObject)
        }

        return when {
            !requestObject.hasCurrentQuestion() -> firstQuestionResponse(quiz)
            else -> nextQuestionResponse(requestObject, quiz)
        }
    }

    private fun firstQuestionResponse(quiz: Quiz): ResponseObject {
        val questionNumber = 1
        return ResponseObject.of(
            text = "Отлично. Слушай первый вопрос. ${quiz.question(questionNumber)}",
            buttons = Button.skip_repeat_hint(),
            sessionState = SessionState(questionNumber, setOf(NextQuestionCommand.name())),
            endSession = false,
            appState = ApplicationState(quiz),
        )
    }

    private fun nextQuestionResponse(requestObject: RequestObject, quiz: Quiz): ResponseObject {
        val requestSessionState = requestObject.state?.session
        requireNotNull(requestSessionState) { "Session state must not be NULL" }

        val currentQuestionNumber = requestSessionState.currentQuestion
        requireNotNull(currentQuestionNumber) { "Current question number must not be NULL" }

        val nextQuestionNumber = currentQuestionNumber + 1
        return ResponseObject.of(
            //@formatter:off
            text = "${CONTINUE_PLAYING_OPENING_PHRASES.random()} Слушай следующий вопрос. ${quiz.question(nextQuestionNumber)}",
            //@formatter:on
            state = requestSessionState.copy(
                currentQuestion = nextQuestionNumber,
                transitionCommands = setOf(NextQuestionCommand.name())
            ),
            endSession = false,
            buttons = Button.skip_repeat_hint()
        )
    }

    companion object {
        private const val AGREEMENT_INTENT_ID = "g911.agreement"

        private val CONTINUE_PLAYING_OPENING_PHRASES: Set<String> =
            setOf(
                "Отличный выбор. Играем дальше!",
                "Одобряю твой выбор!",
                "Хороший выбор. Продолжаем!",
                "Приятно слышать. Продолжаем!"
            )

        fun name(): String = PlayingAgreementCommand::class.java.simpleName
    }
}
