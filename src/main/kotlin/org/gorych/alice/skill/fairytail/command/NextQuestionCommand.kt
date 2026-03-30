package org.gorych.alice.skill.fairytail.command

import org.gorych.alice.skill.core.VICTORY_FANFARE
import org.gorych.alice.skill.core.api.Button
import org.gorych.alice.skill.core.api.RequestObject
import org.gorych.alice.skill.core.api.ResponseObject
import org.gorych.alice.skill.core.api.SessionState
import org.gorych.alice.skill.core.command.PartingCommand
import org.gorych.alice.skill.core.command.RateCommand
import org.gorych.alice.skill.core.command.RequestSessionStatedQuestionCommand
import org.gorych.alice.skill.core.quiz.Quiz

class NextQuestionCommand : RequestSessionStatedQuestionCommand() {

    override fun name() = NextQuestionCommand.name()

    override fun canHandle(requestObject: RequestObject): Boolean {
        return requestObject.hasCurrentQuestion() && requestObject.containsNextQuestionCommand()
    }

    override fun execute(
        requestObject: RequestObject,
        requestSessionState: SessionState,
        currentQuestionNumber: Int,
        quiz: Quiz
    ): ResponseObject {
        log("execute: question number: $currentQuestionNumber")
        val rightAnswers: List<String> = quiz.answerTo(currentQuestionNumber)

        if (rightAnswers.any { it == requestObject.command() }) {
            val rightAnswersCount: Int = getRightAnswersCount(requestSessionState, currentQuestionNumber)

            val nextQuestionNumber = currentQuestionNumber + 1
            if (nextQuestionNumber <= quiz.countOfQuestions()) {
                if (rightAnswersCount > requestSessionState.rightAnswersCount) {
                    val achievementResponse: ResponseObject? =
                        processAchievements(rightAnswersCount, currentQuestionNumber, requestSessionState)
                    if (achievementResponse != null) {
                        return achievementResponse
                    }
                }

                return nextQuestionResponse(nextQuestionNumber, rightAnswersCount, requestSessionState, quiz)
            }
            return endQuizResponse(rightAnswersCount, quiz)
        }

        return wrongAnswerResponse(requestSessionState)
    }

    private fun processAchievements(
        rightAnswersCount: Int, currentQuestionNumber: Int, requestSessionState: SessionState
    ): ResponseObject? {
        return Achievement.entries
            .filter { it.rightAnswersCount == rightAnswersCount }
            .map {
                ResponseObject.of(
                    text = it.responseText,
                    tts = it.responseTts,
                    state = SessionState(
                        currentQuestionNumber,
                        rightAnswersCount,
                        requestSessionState.hintedQuestions,
                        previousHintNumber = 0,
                        setOf(PlayingAgreementCommand.name(), PlayingDisagreementCommand.name())
                    ),
                    endSession = false,
                    buttons = Button.agreement_and_disagreement()
                )
            }
            .firstOrNull()
    }

    private fun getRightAnswersCount(sessionState: SessionState, currentQuestion: Int): Int {
        val rightAnswersCount: Int = sessionState.rightAnswersCount

        val wasUsedHint = sessionState.hintedQuestions.contains(currentQuestion)
        return when {
            wasUsedHint -> rightAnswersCount
            else -> rightAnswersCount + 1
        }
    }

    private fun nextQuestionResponse(
        nextQuestionNumber: Int,
        rightAnswersCount: Int,
        sessionState: SessionState,
        quiz: Quiz
    ) =
        ResponseObject.of(
            //@formatter:off
            text = "${RIGHT_ANSWER_PHRASES.random()} ${NEXT_QUESTION_PHRASES.random()} ${quiz.question(nextQuestionNumber)}",
            //@formatter:on
            state = SessionState(
                nextQuestionNumber,
                rightAnswersCount,
                sessionState.hintedQuestions,
                previousHintNumber = 0,
                setOf(NextQuestionCommand.name())
            ),
            endSession = false,
            buttons = Button.skip_repeat_hint()
        )

    private fun wrongAnswerResponse(sessionState: SessionState): ResponseObject {
        log("execute: wrong answer")
        return ResponseObject.of(
            text = WRONG_ANSWER_PHRASES.random(),
            state = sessionState,
            endSession = false,
            buttons = Button.skip_repeat_hint()
        )
    }

    private fun endQuizResponse(rightAnswersCount: Int, quiz: Quiz): ResponseObject {
        val excellentScoreValue = 95
        val countOfAllQuestions = quiz.countOfQuestions()
        val score = rightAnswersCount.toDouble() / countOfAllQuestions * 100

        val scorePhrase = when {
            //score >= excellentScoreValue -> WINNING_PHRASE_EXCELLENT_RESULT_TEMPLATE
            score >= 85 -> WINNING_PHRASE_GOOD_RESULT_TEMPLATE
            score < 30 -> WINNING_PHRASE_BAD_RESULT_TEMPLATE
            else -> WINNING_PHRASE_NORMAL_RESULT_TEMPLATE
        }.format(rightAnswersCount, countOfAllQuestions)

        val buttons: MutableList<Button> = mutableListOf(Button.rate(), Button.goodbye())
        /*if (score >= excellentScoreValue) {
            buttons.addFirst(Button.agreement())
        }*/

        log("execute: end of quiz. ${quiz.name()}, score: $score")

        return ResponseObject.of(
            text = WINNING_PHRASE_TEXT_TEMPLATE.format(scorePhrase),
            tts = WINNING_PHRASE_TTS_TEMPLATE.format(VICTORY_FANFARE, scorePhrase),
            endSession = false,
            state = SessionState(setOf(RateCommand.name(), PartingCommand.name())),
            buttons = buttons
        )
    }

    enum class Achievement(val rightAnswersCount: Int, val responseText: String, val responseTts: String) {
        FIVE_RIGHT_ANSWERS(
            rightAnswersCount = 5,
            responseText = FIVE_RIGHT_ANSWERS_ACHIEVEMENT_MESSAGE,
            responseTts = FIVE_RIGHT_ANSWERS_ACHIEVEMENT_TTS
        ),
        TEN_RIGHT_ANSWERS(
            rightAnswersCount = 10,
            responseText = TEN_RIGHT_ANSWERS_ACHIEVEMENT_MESSAGE,
            responseTts = TEN_RIGHT_ANSWERS_ACHIEVEMENT_TTS
        ),
        TWENTY_RIGHT_ANSWERS(
            rightAnswersCount = 20,
            responseText = TWENTY_RIGHT_ANSWERS_ACHIEVEMENT_MESSAGE,
            responseTts = TWENTY_RIGHT_ANSWERS_ACHIEVEMENT_TTS
        ),
    }

    companion object {
        private const val WINNING_PHRASE_CONGRATULATION_AND_SCORE_TEMPLATE = "" +
                "Поздравляю, это был последний вопрос. " +
                "%s"

        private const val WINNING_PHRASE_TEXT_TEMPLATE = "" +
                "Верно! " + WINNING_PHRASE_CONGRATULATION_AND_SCORE_TEMPLATE

        private const val WINNING_PHRASE_TTS_TEMPLATE = "" +
                "Верно! %s " + WINNING_PHRASE_CONGRATULATION_AND_SCORE_TEMPLATE

        private const val WINNING_PHRASE_EXCELLENT_RESULT_TEMPLATE = "" +
                "Твой результат выше всяких похвал. %d из %d! " +
                "Для таких как ты у меня есть вопросы со 'звёздочкой'. " +
                "Рискнешь попробовать?"

        private const val ASK_TO_RATE_PHRASE = "" +
                "Кстати, буду рада, если найдешь минутку, чтобы оставить оценку в каталоге."

        private const val WINNING_PHRASE_GOOD_RESULT_TEMPLATE = "" +
                "Твой результат впечатляет. %d из %d возможных! " +
                "Ты настоящий мастер сказок, но не расслабляйся. " +
                "К следующей встрече я приготовлю что-нибудь посложнее. " +
                "$ASK_TO_RATE_PHRASE " +
                "Пока!"

        private const val WINNING_PHRASE_BAD_RESULT_TEMPLATE = "" +
                "Твой результат - %d из %d. Маловато для победы, но смелость засчитана. " +
                "Если захочешь поиграть ещё - ты знаешь где меня искать. Пока!"

        private const val WINNING_PHRASE_NORMAL_RESULT_TEMPLATE = "" +
                "%d из %d неплохой результат, но ты явно способен на большее! " +
                "Буду рада новой встрече, если решишь снова испытать себя. Пока!"

        private val RIGHT_ANSWER_PHRASES: Set<String> =
            setOf("Верно!", "Правильно!", "Ты молодец!", "Совершенно верно! Так держать!")

        private val NEXT_QUESTION_PHRASES: Set<String> = setOf(
            "Слушай следующий вопрос.",
            "Следующий вопрос звучит так.",
            "Приготовься, следующий вопрос будет сложнее."
        )

        private val WRONG_ANSWER_PHRASES: Set<String> =
            setOf("Неверно. Подумай ещё!", "Нет. Попытайся ещё раз!", "Неправильно. Попробуй другой вариант!")

        private const val FIVE_RIGHT_ANSWERS_ACHIEVEMENT_TEXT =
            "Поздравляю! Первое достижение из пяти правильных ответов у тебя в кармане. Идём дальше?"
        private const val FIVE_RIGHT_ANSWERS_ACHIEVEMENT_MESSAGE = "5\uFE0F⃣ $FIVE_RIGHT_ANSWERS_ACHIEVEMENT_TEXT"
        private const val FIVE_RIGHT_ANSWERS_ACHIEVEMENT_TTS = VICTORY_FANFARE + FIVE_RIGHT_ANSWERS_ACHIEVEMENT_TEXT

        private const val TEN_RIGHT_ANSWERS_ACHIEVEMENT_TEXT =
            "Десять правильных ответов - это серьезная заявка на победу. Перейдём к вопросам посложнее?"
        private const val TEN_RIGHT_ANSWERS_ACHIEVEMENT_MESSAGE = "\uD83D\uDD1F $TEN_RIGHT_ANSWERS_ACHIEVEMENT_TEXT"
        private const val TEN_RIGHT_ANSWERS_ACHIEVEMENT_TTS = VICTORY_FANFARE + TEN_RIGHT_ANSWERS_ACHIEVEMENT_TEXT

        private const val TWENTY_RIGHT_ANSWERS_ACHIEVEMENT_TEXT =
            "Твои успехи впечатляют — уже 20 правильных ответов. Продолжим? Впереди самое интересное."
        private const val TWENTY_RIGHT_ANSWERS_ACHIEVEMENT_MESSAGE =
            "2\uFE0F⃣0\uFE0F⃣ $TWENTY_RIGHT_ANSWERS_ACHIEVEMENT_TEXT"
        private const val TWENTY_RIGHT_ANSWERS_ACHIEVEMENT_TTS = VICTORY_FANFARE + TWENTY_RIGHT_ANSWERS_ACHIEVEMENT_TEXT

        fun name(): String = NextQuestionCommand::class.java.simpleName
    }
}