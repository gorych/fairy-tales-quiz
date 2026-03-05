package org.gorych.alice.skill.core.api

import com.fasterxml.jackson.annotation.JsonProperty

private const val HTTP_VERSION = "1.0"

data class ResponseObject(
    val response: ResponseValue,
    val version: String = HTTP_VERSION,
    @JsonProperty("session_state") val sessionState: SessionState = SessionState()
) {
    constructor(response: ResponseValue, sessionState: SessionState) : this(
        response,
        HTTP_VERSION,
        sessionState
    )

    companion object {
        fun of(text: String, endSession: Boolean): ResponseObject {
            return ResponseObject(ResponseValue(text, endSession))
        }

        fun of(text: String, state: SessionState?, endSession: Boolean): ResponseObject {
            return ResponseObject(ResponseValue(text, endSession), state ?: SessionState())
        }

        fun of(text: String, buttons: List<Button>, state: SessionState?, endSession: Boolean): ResponseObject {
            return ResponseObject(ResponseValue(text, endSession, buttons), state ?: SessionState())
        }

        fun ofUnclearCommand(requestObject: RequestObject?): ResponseObject {
            val sessionState = requestObject?.state?.session ?: SessionState()
            return of("Я вас не поняла, повторите, пожалуйста.", sessionState, false)
        }

        fun ofTechnicalError(requestObject: RequestObject?): ResponseObject {
            val sessionState = requestObject?.state?.session ?: SessionState()
            return of("Извините, произошла техническая ошибка. Попробуйте еще раз.", sessionState, false)
        }
    }
}

data class ResponseValue(
    val text: String,
    @JsonProperty("end_session") val endSession: Boolean,
    val buttons: List<Button> = listOf(),
)

data class Button(val title: String, val hide: Boolean = true) {
    companion object {
        fun agreement() = Button("✅ Да, давай")
        fun disagreement() = Button("❌ Нет, не хочу")

        fun agreement_and_disagreement() = listOf(agreement(), disagreement())
    }
}