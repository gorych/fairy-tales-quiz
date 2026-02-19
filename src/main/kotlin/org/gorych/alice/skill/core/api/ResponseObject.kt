package org.gorych.alice.skill.core.api

import com.fasterxml.jackson.annotation.JsonProperty

private const val HTTP_VERSION = "1.0"

data class ResponseObject(
    val response: ResponseValue,
    val version: String = HTTP_VERSION,
    val buttons: List<Button> = listOf(),
    @JsonProperty("session_state") val sessionState: SessionState = SessionState()
) {
    constructor(response: ResponseValue, sessionState: SessionState) : this(
        response,
        HTTP_VERSION,
        listOf(),
        sessionState
    )

    companion object {
        fun of(text: String, endSession: Boolean): ResponseObject {
            return ResponseObject(ResponseValue(text, endSession))
        }

        fun of(text: String, state: SessionState?, endSession: Boolean): ResponseObject {
            return ResponseObject(ResponseValue(text, endSession), state ?: SessionState())
        }

        fun ofUnclearCommand(sessionState: SessionState?) =
            of("Я вас не поняла, повторите, пожалуйста.", sessionState ?: SessionState(), false)

        fun ofUnclearCommand() = ofUnclearCommand(SessionState())

        fun ofTechnicalError(sessionState: SessionState?) =
            of("Извините, произошла техническая ошибка. Попробуйте еще раз.", sessionState ?: SessionState(), false)

        fun ofTechnicalError() = ofTechnicalError(SessionState())
    }
}

data class ResponseValue(
    val text: String,
    @JsonProperty("end_session") val endSession: Boolean,
)

data class Button(val title: String, val hide: Boolean = true)