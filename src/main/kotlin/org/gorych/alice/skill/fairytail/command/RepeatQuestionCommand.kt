package org.gorych.alice.skill.fairytail.command

import org.gorych.alice.skill.core.api.RequestObject
import org.gorych.alice.skill.core.api.ResponseObject
import org.gorych.alice.skill.core.api.SessionState
import org.gorych.alice.skill.core.command.Command
import org.gorych.alice.skill.fairytail.quiz.Quiz

private const val REPEAT_INTENT_ID = "g911.repeat"

class RepeatQuestionCommand : Command {

    override fun name() = RepeatQuestionCommand.name()

    override fun canHandle(requestObject: RequestObject): Boolean {
        return requestObject.containsIntent(REPEAT_INTENT_ID)
                && requestObject.hasCurrentQuestion()
                && requestObject.containsNextQuestionCommand()
    }

    override fun execute(requestObject: RequestObject): ResponseObject {
        val requestSessionState: SessionState? = requestObject.state?.session

        val currentQuestion: Int? = requestSessionState?.currentQuestion
        currentQuestion?.let {
            when {
                (currentQuestion > 0) -> {
                    return ResponseObject.of(
                        text = "${BEFORE_QUESTION_PHRASES.random()} ${Quiz.question(currentQuestion)}",
                        state = requestSessionState,
                        endSession = false
                    )
                }

                else -> {
                    return ResponseObject.ofTechnicalError(requestObject)
                }
            }
        }

        return ResponseObject.ofUnclearCommand(requestObject)
    }

    companion object {
        val BEFORE_QUESTION_PHRASES = setOf("Без проблем.", "Хорошо, только слушай внимательно.", "Повторяю.")

        fun name(): String = RepeatQuestionCommand::class.java.simpleName
    }
}