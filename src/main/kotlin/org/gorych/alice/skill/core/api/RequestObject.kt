package org.gorych.alice.skill.core.api

import com.fasterxml.jackson.annotation.JsonProperty
import org.gorych.alice.skill.fairytail.command.AgreementCommand
import org.gorych.alice.skill.fairytail.command.DisagreementCommand
import org.gorych.alice.skill.fairytail.command.NextQuestionCommand

data class RequestObject(
    @JsonProperty(required = false) val request: Request? = null,
    @JsonProperty(required = false) val session: Session? = null,
    @JsonProperty(required = false) val state: State? = null,
) {
    fun isNewSession() = session?.new ?: false

    fun containsIntent(key: String): Boolean = request?.nlu?.intents?.keys?.any { it == key } ?: false

    fun command(): String = request?.command ?: ""

    fun tokens(): List<String> = request?.nlu?.tokens ?: listOf()
    fun containsToken(key: String): Boolean = request?.nlu?.tokens?.any { it == key } ?: false

    fun hasCurrentQuestion(): Boolean = state?.session?.currentQuestion != null

    fun containsAgreementCommand(): Boolean = state?.containsTransitionCommand(AgreementCommand.name()) ?: false
    fun containsDisagreementCommand(): Boolean = state?.containsTransitionCommand(DisagreementCommand.name()) ?: false
    fun containsNextQuestionCommand(): Boolean = state?.containsTransitionCommand(NextQuestionCommand.name()) ?: false

    companion object
}

data class Request(
    val command: String = "",
    @JsonProperty(required = false) val nlu: NLU? = null,
)

data class NLU(val tokens: List<String> = listOf(), val intents: Map<String, Any> = mapOf())

data class Session(val new: Boolean)

data class State(val session: SessionState) {
    fun containsTransitionCommand(commandName: String): Boolean = session.containsTransitionCommand(commandName)
}