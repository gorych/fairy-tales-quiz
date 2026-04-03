package org.gorych.alice.skill.core.api

import com.fasterxml.jackson.annotation.JsonProperty

private const val HTTP_VERSION = "1.0"

data class ResponseObject(
    val response: ResponseValue,
    val version: String = HTTP_VERSION,
    @JsonProperty("session_state") val sessionState: SessionState = SessionState(),
    @JsonProperty("application_state") val applicationState: ApplicationState? = null
) {
    constructor(response: ResponseValue, sessionState: SessionState) : this(
        response,
        HTTP_VERSION,
        sessionState,
        null
    )

    companion object {
        fun of(text: String, endSession: Boolean): ResponseObject {
            return ResponseObject(ResponseValue(text, endSession))
        }

        fun of(text: String, tts: String, endSession: Boolean): ResponseObject {
            return ResponseObject(ResponseValue(text, endSession, listOf(), tts))
        }

        fun of(text: String, endSession: Boolean, button: Button) =
            ResponseObject(ResponseValue(text, endSession, listOf(button)))

        fun of(text: String, endSession: Boolean, buttons: List<Button>) =
            ResponseObject(ResponseValue(text, endSession, buttons))

        fun of(text: String, endSession: Boolean, buttons: List<Button>, transitionCommands: Set<String>) =
            ResponseObject(ResponseValue(text, endSession, buttons), SessionState(transitionCommands))

        fun of(text: String, tts: String, endSession: Boolean, buttons: List<Button>, transitionCommands: Set<String>) =
            ResponseObject(ResponseValue(text, endSession, buttons, tts), SessionState(transitionCommands))

        fun of(text: String, tts: String, endSession: Boolean, buttons: List<Button>) =
            ResponseObject(ResponseValue(text, endSession, buttons, tts))

        fun of(text: String, state: SessionState?, endSession: Boolean): ResponseObject {
            return ResponseObject(ResponseValue(text, endSession), state ?: SessionState())
        }

        fun of(text: String, buttons: List<Button>, state: SessionState?, endSession: Boolean): ResponseObject {
            return ResponseObject(ResponseValue(text, endSession, buttons), state ?: SessionState())
        }

        fun of(
            text: String,
            tts: String,
            buttons: List<Button>,
            sessionState: SessionState?,
            endSession: Boolean
        ): ResponseObject {
            return ResponseObject(ResponseValue(text, endSession, buttons, tts), sessionState ?: SessionState())
        }

        fun of(
            text: String,
            tts: String,
            buttons: List<Button>,
            sessionState: SessionState?,
            appState: ApplicationState?,
            endSession: Boolean
        ): ResponseObject {
            return ResponseObject(
                response = ResponseValue(text, endSession, buttons, tts),
                version = HTTP_VERSION,
                sessionState = sessionState ?: SessionState(),
                applicationState = appState
            )
        }

        fun of(
            text: String,
            buttons: List<Button>,
            sessionState: SessionState?,
            appState: ApplicationState?,
            endSession: Boolean
        ): ResponseObject {
            return ResponseObject(
                response = ResponseValue(text, endSession, buttons),
                version = HTTP_VERSION,
                sessionState = sessionState ?: SessionState(),
                applicationState = appState
            )
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
    val tts: String? = text,
)

data class Button(val title: String, val hide: Boolean = true, val payload: String = "{}", val url: String? = null) {
    companion object {
        fun agreement() = Button("✅ Да, давай")
        fun disagreement() = Button("❌ Нет, не хочу")

        fun iKnow() = Button("\uD83D\uDFE2 Да, знаю")
        fun iDontKnow() = Button("\uD83D\uDFE5 Нет, не знаю")

        fun skipQuestion() = Button("➡\uFE0F Следующий вопрос")
        fun repeatQuestion() = Button("\uD83D\uDD01 Повтори вопрос")

        fun hint() = Button("❓Дай подсказку")
        fun stop() = Button("❌ Выйти")
        fun whatCanYouDo() = Button("ℹ\uFE0F Что ты умеешь?")

        fun agreement_and_disagreement() = listOf(agreement(), disagreement())
        fun agreement_disagreement_whatCanYouDo() = listOf(agreement(), whatCanYouDo(), disagreement())

        fun skip_repeat_hint() = listOf(skipQuestion(), repeatQuestion(), hint())
        fun skip_repeat_hint_stop() = listOf(skipQuestion(), repeatQuestion(), hint(), stop())

        fun goodbye() = Button("\uD83D\uDC4B Пока!")
        fun rate() = Button(title = "⭐ Оценить", url = "https://alice.ya.ru/s/8070abca-f39f-40ec-bc59-377070bed8b8")
    }
}