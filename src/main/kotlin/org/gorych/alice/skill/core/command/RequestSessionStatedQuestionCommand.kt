package org.gorych.alice.skill.core.command

import org.gorych.alice.skill.core.api.RequestObject
import org.gorych.alice.skill.core.api.ResponseObject
import org.gorych.alice.skill.core.api.SessionState
import org.gorych.alice.skill.core.quiz.Quiz

abstract class RequestSessionStatedQuestionCommand : Command {

    override fun execute(requestObject: RequestObject, quiz: Quiz): ResponseObject {
        val requestSessionState = requestObject.state?.session
        requireNotNull(requestSessionState) { "Session state must not be NULL" }

        val currentQuestionNumber = requestSessionState.currentQuestion
        requireNotNull(currentQuestionNumber) { "Current question number must not be NULL" }

        return execute(requestObject, requestSessionState, currentQuestionNumber, quiz)
    }

    abstract fun execute(
        requestObject: RequestObject, requestSessionState: SessionState, currentQuestionNumber: Int, quiz: Quiz
    ): ResponseObject
}