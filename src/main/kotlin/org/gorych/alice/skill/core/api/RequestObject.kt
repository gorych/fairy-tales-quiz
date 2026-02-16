package org.gorych.alice.skill.core.api

import com.fasterxml.jackson.annotation.JsonProperty

data class RequestObject(
    @JsonProperty(required = false) val request: Request? = null,
    @JsonProperty(required = false) val session: Session? = null,
    @JsonProperty(required = false) val state: State? = null,
) {
    companion object
}

data class Request(
    val command: String = "",
    @JsonProperty(required = false) val nlu: NLU? = null,
) {
    fun containsIntent(key: String): Boolean = nlu?.intents?.keys?.any { it == key } ?: false
    fun containsToken(key: String): Boolean = nlu?.tokens?.any { it == key } ?: false
}

data class NLU(val tokens: List<String> = listOf(), val intents: Map<String, Any> = mapOf())

data class Session(val new: Boolean)

data class State(val session: StateSession)
data class StateSession(val value: String = "")