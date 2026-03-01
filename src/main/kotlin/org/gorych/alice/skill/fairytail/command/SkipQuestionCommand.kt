package org.gorych.alice.skill.fairytail.command

import org.gorych.alice.skill.core.api.RequestObject
import org.gorych.alice.skill.core.api.ResponseObject
import org.gorych.alice.skill.core.api.SessionState
import org.gorych.alice.skill.core.command.RequestSessionStatedQuestionCommand
import org.gorych.alice.skill.fairytail.quiz.Quiz

private const val SKIP_INTENT_ID = "g911.skip"

class SkipQuestionCommand : RequestSessionStatedQuestionCommand() {

    override fun name() = SkipQuestionCommand.name()

    override fun canHandle(requestObject: RequestObject): Boolean {
        return requestObject.containsIntent(SKIP_INTENT_ID)
                && requestObject.hasCurrentQuestion()
                && requestObject.containsNextQuestionCommand()
    }

    override fun execute(
        requestObject: RequestObject,
        requestSessionState: SessionState,
        currentQuestionNumber: Int
    ): ResponseObject {
        val nextQuestionNumber = currentQuestionNumber + 1
        if (nextQuestionNumber <= Quiz.countOfQuestions()) {
            return nextQuestionResponse(nextQuestionNumber, requestSessionState)
        }
        return noQuestionsResponse(currentQuestionNumber)
    }

    private fun nextQuestionResponse(nextQuestionNumber: Int, sessionState: SessionState) =
        ResponseObject.of(
            text = "${BEFORE_QUESTION_PHRASES.random()} ${Quiz.question(nextQuestionNumber)}",
            state = sessionState.copy(
                currentQuestion = nextQuestionNumber,
                transitionCommands = setOf(NextQuestionCommand.name()),
                previousHintNumber = 0
            ),
            endSession = false
        )

    private fun noQuestionsResponse(currentQuestionNumber: Int): ResponseObject {
        val rightAnswer = Quiz.answerTo(currentQuestionNumber)[0]
        //TODO send result score
        return ResponseObject.of(
            text = "Кажется, у меня больше не осталось вопросов. " +
                    "Если интересно, то правильный ответ - '$rightAnswer'. Спасибо за игру!",
            endSession = true
        )
    }

    companion object {
        private val BEFORE_QUESTION_PHRASES =
            setOf(
                "Хорошо, слушай следующий вопрос.",
                "Так и быть, слушай следующий вопрос.",
                "Ладно, но постарайся больше не пропускать."
            )

        fun name(): String = SkipQuestionCommand::class.java.simpleName
    }
}