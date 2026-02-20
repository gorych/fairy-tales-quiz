package org.gorych.alice.skill.fairytail.command

import org.gorych.alice.skill.core.api.RequestObject
import org.gorych.alice.skill.core.api.ResponseObject
import org.gorych.alice.skill.core.api.SessionState
import org.gorych.alice.skill.core.command.Command
import org.gorych.alice.skill.fairytail.quiz.Quiz

private const val SKIP_INTENT_ID = "g911.skip"

class SkipQuestionCommand : Command {

    override fun name() = SkipQuestionCommand.name()

    override fun canHandle(requestObject: RequestObject): Boolean {
        return requestObject.containsIntent(SKIP_INTENT_ID)
                && requestObject.hasCurrentQuestion()
                && requestObject.containsNextQuestionCommand()
    }

    override fun execute(requestObject: RequestObject): ResponseObject {
        val currentQuestionNumber: Int? = requestObject.state?.session?.currentQuestion
        currentQuestionNumber?.let {
            when {
                (currentQuestionNumber > 0) -> {
                    val nextQuestionNumber = currentQuestionNumber + 1
                    if (nextQuestionNumber <= Quiz.countOfQuestions()) {
                        return nextQuestionResponse(nextQuestionNumber)
                    } else {
                        return noQuestionsResponse(currentQuestionNumber)
                    }
                }

                else -> {
                    return ResponseObject.ofTechnicalError(requestObject)
                }
            }
        }

        return ResponseObject.ofUnclearCommand(requestObject)
    }

    private fun nextQuestionResponse(nextQuestionNumber: Int): ResponseObject {
        return ResponseObject.of(
            text = "${BEFORE_QUESTION_PHRASES.random()} ${Quiz.question(nextQuestionNumber)}",
            state = SessionState(nextQuestionNumber, setOf(NextQuestionCommand.name())),
            endSession = false
        )
    }

    private fun noQuestionsResponse(currentQuestionNumber: Int): ResponseObject {
        val rightAnswer = Quiz.answerTo(currentQuestionNumber)[0]
        return ResponseObject.of(
            text = "Кажется, у меня больше не осталось вопросов. " +
                    "Если интересно, то правильный ответ - '$rightAnswer'. Спасибо за игру!",
            endSession = true
        )
    }

    companion object {
        val BEFORE_QUESTION_PHRASES =
            setOf(
                "Хорошо, слушай следующий вопрос.",
                "Так и быть, слушай следующий вопрос.",
                "Ладно, но постарайся больше не пропускать."
            )

        fun name(): String = SkipQuestionCommand::class.java.simpleName
    }
}