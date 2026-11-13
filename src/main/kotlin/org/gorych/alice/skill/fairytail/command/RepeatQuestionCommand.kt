package org.gorych.alice.skill.fairytail.command

import org.gorych.alice.skill.core.api.RequestObject
import org.gorych.alice.skill.core.api.ResponseObject
import org.gorych.alice.skill.core.api.SessionState
import org.gorych.alice.skill.core.command.RequestSessionStatedQuestionCommand
import org.gorych.alice.skill.core.quiz.ModeProfiles
import org.gorych.alice.skill.core.quiz.Quiz
import org.gorych.alice.skill.core.quiz.QuizSource

class RepeatQuestionCommand(quizzes: QuizSource) : RequestSessionStatedQuestionCommand(quizzes) {

    override fun name() = RepeatQuestionCommand.name()

    override fun canHandle(requestObject: RequestObject): Boolean {
        return requestObject.containsIntent(REPEAT_INTENT_ID)
                && requestObject.hasCurrentQuestion()
                && requestObject.containsNextQuestionCommand()
    }

    override fun execute(
        requestObject: RequestObject, requestSessionState: SessionState, currentQuestionNumber: Int, quiz: Quiz
    ): ResponseObject {
        log("execute: question number: $currentQuestionNumber")

        return ResponseObject.ofPrompt(
            prompt = quiz.prompt(currentQuestionNumber, BEFORE_QUESTION_PHRASES.random()),
            buttons = ModeProfiles.of(quiz.gameMode).buttons,
            sessionState = requestSessionState,
            endSession = false
        )
    }

    companion object {
        private const val REPEAT_INTENT_ID = "g911.repeat"

        val BEFORE_QUESTION_PHRASES = setOf("Без проблем.", "Хорошо, только слушай внимательно.", "Повторяю.")

        fun name(): String = RepeatQuestionCommand::class.java.simpleName
    }
}
