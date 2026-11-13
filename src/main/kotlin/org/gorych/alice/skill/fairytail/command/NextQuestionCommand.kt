package org.gorych.alice.skill.fairytail.command

import org.gorych.alice.skill.core.VICTORY_FANFARE
import org.gorych.alice.skill.core.api.*
import org.gorych.alice.skill.core.command.PartingCommand
import org.gorych.alice.skill.core.command.RequestSessionStatedQuestionCommand
import org.gorych.alice.skill.core.quiz.Achievement
import org.gorych.alice.skill.core.quiz.ModeProfile
import org.gorych.alice.skill.core.quiz.ModeProfiles
import org.gorych.alice.skill.core.quiz.Quiz
import org.gorych.alice.skill.core.quiz.QuizSource

class NextQuestionCommand(quizzes: QuizSource) : RequestSessionStatedQuestionCommand(quizzes) {

    override fun name() = NextQuestionCommand.name()

    override fun canHandle(requestObject: RequestObject): Boolean {
        return requestObject.hasCurrentQuestion() && requestObject.containsNextQuestionCommand()
    }

    override fun execute(
        requestObject: RequestObject, requestSessionState: SessionState, currentQuestionNumber: Int, quiz: Quiz
    ): ResponseObject {
        log("execute: question number: $currentQuestionNumber")

        if (quiz.isCorrectAnswer(requestObject, currentQuestionNumber)) {
            val profile = ModeProfiles.of(quiz.gameMode)
            val rightAnswersCount: Int = getRightAnswersCount(requestSessionState, currentQuestionNumber)

            val nextQuestionNumber = currentQuestionNumber + 1
            if (nextQuestionNumber <= quiz.countOfQuestions()) {
                if (rightAnswersCount > requestSessionState.rightAnswersCount) {
                    val achievementResponse: ResponseObject? =
                        processAchievements(profile, rightAnswersCount, currentQuestionNumber, requestSessionState)
                    if (achievementResponse != null) {
                        return achievementResponse
                    }
                }

                return nextQuestionResponse(
                    profile,
                    nextQuestionNumber,
                    rightAnswersCount,
                    requestSessionState,
                    quiz,
                    quiz.correctAnswerNote(currentQuestionNumber)
                )
            }
            return endQuizResponse(profile, rightAnswersCount, quiz)
        }

        return wrongAnswerResponse(
            ModeProfiles.of(quiz.gameMode),
            requestSessionState,
            quiz.wrongAnswerNote(currentQuestionNumber)
        )
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
        profile: ModeProfile,
        nextQuestionNumber: Int,
        rightAnswersCount: Int,
        sessionState: SessionState,
        quiz: Quiz,
        note: String
    ): ResponseObject {
        val phrase = listOf(
            profile.rightAnswerPhrases.random(),
            note,
            profile.nextQuestionPhrases.random(),
        ).filter { it.isNotEmpty() }.joinToString(" ")

        val responseSessionState = SessionState(
            nextQuestionNumber,
            rightAnswersCount,
            sessionState.hintedQuestions,
            previousHintNumber = 0,
            setOf(NextQuestionCommand.name())
        )

        return ResponseObject.ofPrompt(
            prompt = quiz.prompt(nextQuestionNumber, phrase),
            buttons = profile.buttons,
            sessionState = responseSessionState,
            endSession = false
        )
    }

    private fun wrongAnswerResponse(profile: ModeProfile, sessionState: SessionState, note: String): ResponseObject {
        log("execute: wrong answer")
        val text = listOf(profile.wrongAnswerPhrases.random(), note)
            .filter { it.isNotEmpty() }
            .joinToString(" ")

        return ResponseObject.of(
            text = text,
            endSession = false,
            buttons = profile.buttons,
            sessionState = sessionState
        )
    }

    //region End of quiz logic
    private fun endQuizResponse(profile: ModeProfile, rightAnswersCount: Int, quiz: Quiz): ResponseObject {
        val countOfAllQuestions = quiz.countOfQuestions()

        val score = rightAnswersCount.toDouble() / countOfAllQuestions * 100
        val scorePhrase = getScorePhrase(profile, quiz, score, rightAnswersCount, countOfAllQuestions)

        log("execute: end of quiz. ${quiz.name()}, score: $score")

        val responseParams: ResponseParams = when {
            quiz.usualQuiz && profile.offersBonusQuiz && score >= profile.excellentScore ->
                bonusQuizProposalParams(quiz)

            quiz.bonusQuiz || (quiz.usualQuiz && score >= profile.goodScore) -> askToRateParams()
            else -> endOfQuizParams()
        }

        return ResponseObject.of(
            text = WINNING_PHRASE_TEXT_TEMPLATE.format(scorePhrase),
            tts = WINNING_PHRASE_TTS_TEMPLATE.format(VICTORY_FANFARE, scorePhrase),
            endSession = false,
            sessionState = SessionState(responseParams.transitionCommands),
            buttons = responseParams.buttons,
            appState = responseParams.appState
        )
    }

    private fun getScorePhrase(
        profile: ModeProfile, quiz: Quiz, score: Double, rightAnswersCount: Int, countOfAllQuestions: Int
    ): String {
        return when {
            quiz.bonusQuiz && (score >= profile.excellentScore) -> BONUS_QUIZ_WINNING_PHRASE_EXCELLENT_RESULT_TEMPLATE
            quiz.bonusQuiz && (score < profile.excellentScore) -> BONUS_QUIZ_WINNING_PHRASE_RESULT_TEMPLATE
            score >= profile.excellentScore -> WINNING_PHRASE_EXCELLENT_RESULT_TEMPLATE
            score >= profile.goodScore -> WINNING_PHRASE_GOOD_RESULT_TEMPLATE
            score < profile.badScore -> WINNING_PHRASE_BAD_RESULT_TEMPLATE
            else -> WINNING_PHRASE_NORMAL_RESULT_TEMPLATE
        }.format(rightAnswersCount, countOfAllQuestions)
    }

    private fun bonusQuizProposalParams(quiz: Quiz) = ResponseParams(
        buttons = listOf(Button.agreement(), Button.disagreement()),
        transitionCommands = setOf(PlayingAgreementCommand.name(), PlayingDisagreementCommand.name()),
        appState = ApplicationState(quizName = null, bonusQuiz = true, gameMode = quiz.gameMode)
    )

    private fun askToRateParams() = ResponseParams(
        buttons = listOf(Button.iKnow(), Button.iDontKnow()),
        transitionCommands = setOf(IKnowHowToRateCommand.name(), IDoNotKnowHowToRateCommand.name()),
        appState = ApplicationState.KEEP_AS_IS
    )

    private fun endOfQuizParams() = ResponseParams(
        buttons = listOf(Button.rate(), Button.goodbye()),
        transitionCommands = setOf(RateCommand.name(), PartingCommand.name()),
        appState = ApplicationState.KEEP_AS_IS
    )

    data class ResponseParams(
        val buttons: List<Button>, val transitionCommands: Set<String>, val appState: ApplicationState?
    )
    //endregion End of quiz

    //region Achievements logic
    private fun processAchievements(
        profile: ModeProfile,
        rightAnswersCount: Int,
        currentQuestionNumber: Int,
        requestSessionState: SessionState
    ): ResponseObject? {
        return profile.achievements
            .filter { it.rightAnswersCount == rightAnswersCount }
            .map { achievement -> achievementResponse(achievement, currentQuestionNumber, rightAnswersCount, requestSessionState) }
            .firstOrNull()
    }

    private fun achievementResponse(
        achievement: Achievement,
        currentQuestionNumber: Int,
        rightAnswersCount: Int,
        requestSessionState: SessionState
    ) = ResponseObject.ofAchievement(
        achievement = achievement,
        buttons = Button.agreement_and_disagreement(),
        sessionState = SessionState(
            currentQuestionNumber,
            rightAnswersCount,
            requestSessionState.hintedQuestions,
            previousHintNumber = 0,
            setOf(PlayingAgreementCommand.name(), PlayingDisagreementCommand.name())
        ),
        endSession = false
    )

    //endregion Achievements logic

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
                "Кстати, буду рада если найдешь минутку, чтобы оставить оценку в каталоге навыков. Знаешь как это сделать?"

        private const val BONUS_QUIZ_WINNING_PHRASE_EXCELLENT_RESULT_TEMPLATE = "" +
                "%d из %d возможных! А это значит, что Титул 'Магистра Сказочных Наук' теперь официально твой. " +
                ASK_TO_RATE_PHRASE

        private const val BONUS_QUIZ_WINNING_PHRASE_RESULT_TEMPLATE = "" +
                "Твой результат %d из %d возможных! " +
                "Это достойно уважения, но кажется, я ещё смогу тебя удивить в следующий раз. " +
                ASK_TO_RATE_PHRASE

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

        fun name(): String = NextQuestionCommand::class.java.simpleName
    }
}
