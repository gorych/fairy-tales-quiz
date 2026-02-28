package org.gorych.alice.skill.fairytail.command

import org.gorych.alice.skill.core.api.RequestObject
import org.gorych.alice.skill.core.api.ResponseObject
import org.gorych.alice.skill.core.api.SessionState
import org.gorych.alice.skill.core.command.RequestSessionStatedQuestionCommand
import org.gorych.alice.skill.fairytail.quiz.Quiz

private const val REPEAT_INTENT_ID = "g911.repeat"

class RepeatQuestionCommand : RequestSessionStatedQuestionCommand() {

    override fun name() = RepeatQuestionCommand.name()

    override fun canHandle(requestObject: RequestObject): Boolean {
        return requestObject.containsIntent(REPEAT_INTENT_ID)
                && requestObject.hasCurrentQuestion()
                && requestObject.containsNextQuestionCommand()
    }

    override fun execute(
        requestObject: RequestObject,
        requestSessionState: SessionState,
        currentQuestionNumber: Int
    ) = ResponseObject.of(
        text = "${BEFORE_QUESTION_PHRASES.random()} ${Quiz.question(currentQuestionNumber)}",
        state = requestSessionState,
        endSession = false
    )

    companion object {
        val BEFORE_QUESTION_PHRASES = setOf("Без проблем.", "Хорошо, только слушай внимательно.", "Повторяю.")

        fun name(): String = RepeatQuestionCommand::class.java.simpleName
    }
}