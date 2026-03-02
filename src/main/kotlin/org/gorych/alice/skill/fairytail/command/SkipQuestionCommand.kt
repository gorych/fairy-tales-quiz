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
        return noQuestionsResponse(requestSessionState.rightAnswersCount)
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

    private fun noQuestionsResponse(rightAnswersCount: Int): ResponseObject {
        return ResponseObject.of(
            text = "Кажется, у меня больше не осталось вопросов. " +
                    "А это значит, что пора подводить итоги. " +
                    "Твой результат - ${rightAnswersCount} из ${Quiz.countOfQuestions()}. " +
                    "Хорошо это или плохо - судить тебе. Спасибо за игру!",
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