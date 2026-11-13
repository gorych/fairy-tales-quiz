package org.gorych.alice.skill.fairytail.command

import org.gorych.alice.skill.core.api.*
import org.gorych.alice.skill.core.command.Command
import org.gorych.alice.skill.core.quiz.ModeProfiles
import org.gorych.alice.skill.core.quiz.Quiz
import org.gorych.alice.skill.core.quiz.QuizSource


class PlayingAgreementCommand(private val quizzes: QuizSource) : Command {

    override fun name(): String = PlayingAgreementCommand.name()

    override fun needsGameMode(): Boolean = true

    override fun canHandle(requestObject: RequestObject): Boolean {
        return requestObject.containsIntent(AGREEMENT_INTENT_ID) && requestObject.containsCommand(name())
    }

    override fun execute(requestObject: RequestObject): ResponseObject {
        val quiz = quizzes.getQuiz(requestObject)

        return when {
            !requestObject.hasCurrentQuestion() -> firstQuestionResponse(quiz)
            else -> nextQuestionResponse(requestObject, quiz)
        }
    }

    private fun firstQuestionResponse(quiz: Quiz): ResponseObject {
        val questionNumber = 1
        val profile = ModeProfiles.of(quiz.gameMode)
        return ResponseObject.ofPrompt(
            prompt = quiz.prompt(questionNumber, profile.openingPhrase),
            buttons = profile.buttons,
            sessionState = SessionState(questionNumber, setOf(NextQuestionCommand.name())),
            endSession = false,
            appState = ApplicationState(quiz)
        )
    }

    private fun nextQuestionResponse(requestObject: RequestObject, quiz: Quiz): ResponseObject {
        val requestSessionState = requestObject.state?.session
        requireNotNull(requestSessionState) { "Session state must not be NULL" }

        val currentQuestionNumber = requestSessionState.currentQuestion
        requireNotNull(currentQuestionNumber) { "Current question number must not be NULL" }

        val nextQuestionNumber = currentQuestionNumber + 1
        return ResponseObject.ofPrompt(
            prompt = quiz.prompt(
                nextQuestionNumber,
                "${CONTINUE_PLAYING_OPENING_PHRASES.random()} Слушай следующий вопрос."
            ),
            buttons = ModeProfiles.of(quiz.gameMode).buttons,
            sessionState = requestSessionState.copy(
                currentQuestion = nextQuestionNumber,
                transitionCommands = setOf(NextQuestionCommand.name())
            ),
            endSession = false
        )
    }

    companion object {
        private const val AGREEMENT_INTENT_ID = "g911.agreement"

        private val CONTINUE_PLAYING_OPENING_PHRASES: Set<String> =
            setOf(
                "Отличный выбор. Играем дальше!",
                "Одобряю твой выбор!",
                "Хороший выбор. Продолжаем!",
                "Приятно слышать. Продолжаем!"
            )

        fun name(): String = PlayingAgreementCommand::class.java.simpleName
    }
}
