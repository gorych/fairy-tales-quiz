package org.gorych.alice.skill.fairytail.command

import org.gorych.alice.skill.core.VICTORY_FANFARE
import org.gorych.alice.skill.core.api.*
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
                    sessionState = SessionState(
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
        val countOfAllQuestions = quiz.countOfQuestions()

        val score = rightAnswersCount.toDouble() / countOfAllQuestions * 100
        val responseParams: ResponseParams = getEndQuizResponseParams(quiz, score)

        val scorePhrase = when {
            quiz.bonusQuiz && (score >= EXCELLENT_SCORE_VALUE) -> BONUS_QUIZ_WINNING_PHRASE_EXCELLENT_RESULT_TEMPLATE
            quiz.bonusQuiz && (score < EXCELLENT_SCORE_VALUE) -> BONUS_QUIZ_WINNING_PHRASE_RESULT_TEMPLATE
            score >= EXCELLENT_SCORE_VALUE -> WINNING_PHRASE_EXCELLENT_RESULT_TEMPLATE
            score >= 85 -> WINNING_PHRASE_GOOD_RESULT_TEMPLATE
            score < 30 -> WINNING_PHRASE_BAD_RESULT_TEMPLATE
            else -> WINNING_PHRASE_NORMAL_RESULT_TEMPLATE
        }.format(rightAnswersCount, countOfAllQuestions)

        log("execute: end of quiz. ${quiz.name()}, score: $score")

        return ResponseObject.of(
            text = WINNING_PHRASE_TEXT_TEMPLATE.format(scorePhrase),
            tts = WINNING_PHRASE_TTS_TEMPLATE.format(VICTORY_FANFARE, scorePhrase),
            endSession = false,
            sessionState = SessionState(responseParams.transitionCommands),
            buttons = responseParams.buttons,
            appState = responseParams.appState
        )
    }

    private fun getEndQuizResponseParams(quiz: Quiz, score: Double): ResponseParams {
        if (score >= EXCELLENT_SCORE_VALUE && !quiz.bonusQuiz) {
            return ResponseParams(
                buttons = listOf(Button.iKnow(), Button.iDontKnow()),
                transitionCommands = setOf(PlayingAgreementCommand.name(), PlayingDisagreementCommand.name()),
                appState = ApplicationState(quizName = null, bonusQuiz = true)
            )
        }

        return ResponseParams(
            buttons = listOf(Button.rate(), Button.goodbye()),
            transitionCommands = setOf(RateCommand.name(), PartingCommand.name()),
            appState = ApplicationState.KEEP_AS_IS
        )
    }

    data class ResponseParams(
        val buttons: List<Button>,
        val transitionCommands: Set<String>,
        val appState: ApplicationState?
    )

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
        private const val EXCELLENT_SCORE_VALUE = 95

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

        private const val BONUS_QUIZ_WINNING_PHRASE_EXCELLENT_RESULT_TEMPLATE = "" +
                "%d из %d возможных! А, это значит, что Титул 'Магистра Сказочных Наук' теперь официально твой. " +
                "Буду вдвойне потрясена, если оставишь свой отзыв в каталоге навыков. " +
                "Знаешь как это сделать?"

        private const val BONUS_QUIZ_WINNING_PHRASE_RESULT_TEMPLATE = "" +
                "Твой результат %d из %d возможных! " +
                "Это достойно уважения, но кажется, я ещё смогу тебя удивить в следующий раз. " +
                "Кстати, буду благодарна, если оставишь свой отзыв в каталоге навыков. " +
                "Знаешь как это сделать?"

        private const val ASK_TO_RATE_PHRASE = "" +
                "Кстати, буду рада если найдешь минутку, чтобы оставить оценку в каталоге навыков. Знаешь как это сделать?"

        private const val WINNING_PHRASE_GOOD_RESULT_TEMPLATE = "" +
                "Твой результат впечатляет. %d из %d возможных! " +
                "Ты настоящий мастер сказок, но не расслабляйся. " +
                "К следующей встрече я приготовлю что-нибудь посложнее. " +
                ASK_TO_RATE_PHRASE

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