package org.gorych.alice.skill.fairytail.command

import org.gorych.alice.skill.core.api.RequestObject
import org.gorych.alice.skill.core.api.ResponseObject
import org.gorych.alice.skill.core.api.SessionState
import org.gorych.alice.skill.core.command.RequestSessionStatedQuestionCommand
import org.gorych.alice.skill.fairytail.quiz.Quiz

class NextQuestionCommand : RequestSessionStatedQuestionCommand() {

    override fun name() = NextQuestionCommand.name()

    override fun canHandle(requestObject: RequestObject): Boolean {
        return requestObject.hasCurrentQuestion() && requestObject.containsNextQuestionCommand()
    }

    override fun execute(
        requestObject: RequestObject,
        requestSessionState: SessionState,
        currentQuestionNumber: Int
    ): ResponseObject {
        val rightAnswers: List<String> = Quiz.answerTo(currentQuestionNumber)

        if (rightAnswers.any { it == requestObject.command() }) {
            val rightAnswersCount: Int = getRightAnswersCount(requestSessionState, currentQuestionNumber)

            val nextQuestionNumber = currentQuestionNumber + 1
            if (nextQuestionNumber <= Quiz.countOfQuestions()) {
                return nextQuestionResponse(nextQuestionNumber, rightAnswersCount, requestSessionState)
            }
            return endQuizResponse(rightAnswersCount)
        }

        return wrongAnswerResponse(requestSessionState)
    }

    private fun getRightAnswersCount(sessionState: SessionState, currentQuestion: Int): Int {
        var rightAnswersCount: Int = sessionState.rightAnswersCount

        val wasUsedHint = sessionState.hintedQuestions.contains(currentQuestion)
        return when {
            wasUsedHint -> {
                rightAnswersCount
            }

            else -> {
                rightAnswersCount++
                rightAnswersCount
            }
        }
    }

    private fun nextQuestionResponse(nextQuestionNumber: Int, rightAnswersCount: Int, sessionState: SessionState) =
        ResponseObject.of(
            //@formatter:off
            text = "${RIGHT_ANSWER_PHRASES.random()} ${NEXT_QUESTION_PHRASES.random()} ${Quiz.question(nextQuestionNumber)}",
            //@formatter:on
            state = SessionState(
                nextQuestionNumber,
                rightAnswersCount,
                sessionState.hintedQuestions,
                previousHintNumber = 0,
                setOf(NextQuestionCommand.name())
            ),
            endSession = false
        )

    private fun wrongAnswerResponse(sessionState: SessionState) =
        ResponseObject.of(
            text = WRONG_ANSWER_PHRASES.random(),
            state = sessionState,
            endSession = false
        )

    private fun endQuizResponse(rightAnswersCount: Int): ResponseObject {
        val countOfAllQuestions = Quiz.countOfQuestions()
        val score = rightAnswersCount.toDouble() / countOfAllQuestions * 100

        val scorePhrase = when {
            score >= 85 -> WINNING_PHRASE_GOOD_RESULT_TEMPLATE
            score < 30 -> WINNING_PHRASE_BAD_RESULT_TEMPLATE
            else -> WINNING_PHRASE_NORMAL_RESULT_TEMPLATE
        }.format(rightAnswersCount, countOfAllQuestions)

        return ResponseObject.of(
            text = WINNING_PHRASE_TEMPLATE.format(scorePhrase),
            endSession = true
        )
    }

    companion object {
        private const val WINNING_PHRASE_TEMPLATE = "" +
                "Верно! " +
                "Поздравляю, это был последний вопрос. " +
                "%s " +
                "Пока!"

        private const val WINNING_PHRASE_GOOD_RESULT_TEMPLATE = "" +
                "Твой результат впечатляет. %d из %d возможных! " +
                "Ты настоящий мастер сказок, но не расслабляйся. " +
                "К следующей встрече я приготовлю что-нибудь посложнее."

        private const val WINNING_PHRASE_BAD_RESULT_TEMPLATE = "" +
                "Твой результат - %d из %d. Маловато для победы, но смелость засчитана. " +
                "Если захочешь поиграть ещё - ты знаешь где меня искать."

        private const val WINNING_PHRASE_NORMAL_RESULT_TEMPLATE = "" +
                "%d из %d неплохой результат, но ты явно способен на большее! " +
                "Буду рада новой встрече, если решишь снова испытать себя!"

        private val RIGHT_ANSWER_PHRASES: Set<String> =
            setOf("Верно!", "Правильно!", "Ты молодец!", "Совершенно верно! Так держать!")

        private val NEXT_QUESTION_PHRASES: Set<String> = setOf(
            "Слушай следующий вопрос.",
            "Следующий вопрос звучит так.",
            "Приготовься, следующий вопрос будет сложнее."
        )

        private val WRONG_ANSWER_PHRASES: Set<String> =
            setOf("Неверно. Подумай ещё!", "Нет. Попытайся ещё раз!", "Неправильно. Попробуй другой вариант!")

        fun name(): String = NextQuestionCommand::class.java.simpleName
    }
}