package org.gorych.alice.skill.core.api

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import org.gorych.alice.skill.core.quiz.Achievement
import org.gorych.alice.skill.core.quiz.Quiz

private const val HTTP_VERSION = "1.0"

data class ResponseObject(
    val response: ResponseValue,
    val version: String = HTTP_VERSION,
    @JsonProperty("session_state") val sessionState: SessionState = SessionState(),
    @JsonProperty("application_state") val applicationState: ApplicationState? = null
) {
    constructor(response: ResponseValue, sessionState: SessionState) : this(
        response, HTTP_VERSION, sessionState, null
    )

    companion object {
        fun of(
            text: String,
            endSession: Boolean,
            tts: String = text,
            buttons: List<Button> = listOf(),
            sessionState: SessionState? = null,
            appState: ApplicationState? = null,
        ): ResponseObject {
            return response(
                text = text,
                tts = tts,
                buttons = buttons,
                sessionState = sessionState,
                appState = appState,
                endSession = endSession,
                card = null
            )
        }

        fun ofQuestion(
            quiz: Quiz,
            questionNumber: Int,
            phrase: String,
            buttons: List<Button>,
            sessionState: SessionState?,
            endSession: Boolean,
            appState: ApplicationState? = null
        ): ResponseObject {
            val question = quiz.question(questionNumber)
            val card = quiz.imageIdTo(questionNumber)?.let {
                BigImageCard(imageId = it, title = phrase, description = "$questionNumber. $question")
            }

            return response(
                text = "$phrase $question",
                tts = "$phrase $question",
                buttons = buttons,
                sessionState = sessionState,
                appState = appState,
                endSession = endSession,
                card = card
            )
        }

        fun ofAchievement(
            achievement: Achievement,
            buttons: List<Button>,
            sessionState: SessionState?,
            endSession: Boolean
        ): ResponseObject {
            return response(
                text = achievement.text,
                tts = achievement.tts,
                buttons = buttons,
                sessionState = sessionState,
                appState = null,
                endSession = endSession,
                card = BigImageCard(imageId = achievement.imageId, description = achievement.text)
            )
        }

        fun ofUnclearCommand(requestObject: RequestObject?): ResponseObject {
            val sessionState = requestObject?.state?.session ?: SessionState()
            return of(
                text = "Я вас не поняла, повторите, пожалуйста.",
                endSession = false,
                sessionState = sessionState
            )
        }

        fun ofTechnicalError(requestObject: RequestObject?): ResponseObject {
            val sessionState = requestObject?.state?.session ?: SessionState()
            return of(
                text = "Извините, произошла техническая ошибка. Попробуйте еще раз.",
                endSession = false,
                sessionState = sessionState
            )
        }

        private fun response(
            text: String,
            tts: String?,
            buttons: List<Button>,
            sessionState: SessionState?,
            appState: ApplicationState?,
            endSession: Boolean,
            card: BigImageCard?
        ): ResponseObject {
            return ResponseObject(
                response = ResponseValue(text, endSession, buttons, tts, card),
                version = HTTP_VERSION,
                sessionState = sessionState ?: SessionState(),
                applicationState = appState
            )
        }
    }
}

data class ResponseValue(
    val text: String,
    @JsonProperty("end_session") val endSession: Boolean,
    val buttons: List<Button> = listOf(),
    val tts: String? = text,
    @JsonInclude(JsonInclude.Include.NON_NULL) val card: BigImageCard? = null,
)

data class BigImageCard(
    @JsonProperty("image_id") val imageId: String,
    val title: String? = null,
    val description: String? = null,
    val type: String = BIG_IMAGE_TYPE,
) {
    companion object {
        const val BIG_IMAGE_TYPE = "BigImage"
    }
}

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
