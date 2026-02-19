package org.gorych.alice.skill.fairytail.command

import org.gorych.alice.skill.core.api.RequestObject
import org.gorych.alice.skill.core.api.ResponseObject
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
        val currentQuestion: Int? = requestObject.state?.session?.currentQuestion
        currentQuestion?.let {
            when {
                (currentQuestion > 0) -> {
                    return ResponseObject.of(
                        text = "${BEFORE_QUESTION_PHRASES.random()} ${Quiz.question(currentQuestion)}",
                        state = requestObject.state.session,
                        endSession = false
                    )
                }

                else -> {
                    return ResponseObject.ofTechnicalError()
                }
            }
        }

        return ResponseObject.ofUnclearCommand()
    }

    companion object {
        val BEFORE_QUESTION_PHRASES = setOf("Без проблем.", "Хорошо, только слушай внимательно.", "Повторяю.")

        fun name(): String = RepeatQuestionCommand::class.java.simpleName
    }
}