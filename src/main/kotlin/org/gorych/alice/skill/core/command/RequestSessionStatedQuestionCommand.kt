package org.gorych.alice.skill.core.command

import org.gorych.alice.skill.core.api.RequestObject
import org.gorych.alice.skill.core.api.ResponseObject
import org.gorych.alice.skill.core.api.SessionState

abstract class RequestSessionStatedQuestionCommand : Command {

    override fun execute(requestObject: RequestObject): ResponseObject {
        val requestSessionState = requestObject.state?.session
        requireNotNull(requestSessionState) { "Session state must not be NULL" }

        val currentQuestionNumber = requestSessionState.currentQuestion
        requireNotNull(currentQuestionNumber) { "Current question number must not be NULL" }

        return execute(requestObject, requestSessionState, currentQuestionNumber)
    }

    abstract fun execute(
        requestObject: RequestObject,
        requestSessionState: SessionState,
        currentQuestionNumber: Int
    ): ResponseObject
}