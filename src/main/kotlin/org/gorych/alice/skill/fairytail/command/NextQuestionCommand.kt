package org.gorych.alice.skill.fairytail.command

import org.gorych.alice.skill.core.api.RequestObject
import org.gorych.alice.skill.core.api.ResponseObject
import org.gorych.alice.skill.core.api.SessionState
import org.gorych.alice.skill.core.api.State
import org.gorych.alice.skill.core.command.Command
import org.gorych.alice.skill.fairytail.quiz.Quiz

class NextQuestionCommand : Command {

    override fun name() = NextQuestionCommand.name()

    override fun canHandle(requestObject: RequestObject): Boolean {
        return requestObject.hasCurrentQuestion() && requestObject.containsNextQuestionCommand()
    }

    override fun execute(requestObject: RequestObject): ResponseObject {
        val requestSessionState: SessionState? = requestObject.state?.session

        val currentQuestion: Int? = requestSessionState?.currentQuestion
        currentQuestion?.let {
            when {
                (currentQuestion > 0) -> {
                    return processUserAnswer(currentQuestion, requestObject)
                }

                else -> {
                    return ResponseObject.ofTechnicalError(requestSessionState)
                }
            }
        }

        return ResponseObject.ofUnclearCommand(requestSessionState)
    }

    private fun processUserAnswer(currentQuestion: Int, requestObject: RequestObject): ResponseObject {
        val rightAnswers: List<String> = Quiz.getAnswerFor(currentQuestion)
        val userAnswer: String = requestObject.command()

        if (rightAnswers.any { it == userAnswer }) {
            val nextQuestionNumber = currentQuestion + 1
            if (nextQuestionNumber <= Quiz.countOfQuestions()) {
                return nextQuestionResponse(nextQuestionNumber)
            }
            return endQuizResponse()
        }

        return wrongAnswerResponse(requestObject.state!!)
    }

    private fun nextQuestionResponse(nextQuestionNumber: Int) =
        ResponseObject.of(
            //@formatter:off
            text = "${RIGHT_ANSWER_PHRASES.random()} ${NEXT_QUESTION_PHRASES.random()} ${Quiz.question(nextQuestionNumber)}",
            //@formatter:on
            state = SessionState(nextQuestionNumber, setOf(NextQuestionCommand.name())),
            endSession = false
        )

    private fun wrongAnswerResponse(state: State) =
        ResponseObject.of(
            text = WRONG_ANSWER_PHRASES.random(),
            state = state.session,
            endSession = false
        )

    private fun endQuizResponse() = ResponseObject.of(
        text = WINNING_PHRASE,
        endSession = true
    )

    companion object {
        const val WINNING_PHRASE = "" +
                "Да, это правильный ответ! " +
                "Поздравляю, это был последний вопрос. " +
                "Если захочешь поиграть ещё - ты знаешь где меня искать. " +
                "Пока!"

        val RIGHT_ANSWER_PHRASES: Set<String> =
            setOf("Верно!", "Правильно!", "Ты молодец!", "Совершенно верно! Так держать!")

        val NEXT_QUESTION_PHRASES: Set<String> = setOf(
            "Слушай следующий вопрос.",
            "Следующий вопрос звучит так.",
            "Приготовься, следующий вопрос будет сложнее."
        )

        val WRONG_ANSWER_PHRASES: Set<String> =
            setOf("Неверно. Подумай ещё!", "Нет. Попытайся ещё раз!", "Неправильно. Попробуй другой вариант!")

        fun name(): String = NextQuestionCommand::class.java.simpleName
    }
}