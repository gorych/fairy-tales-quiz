package org.gorych.alice.skill.core.api

import com.fasterxml.jackson.annotation.JsonProperty

data class RequestObject(
    @JsonProperty(required = false) val request: Request? = null,
    @JsonProperty(required = false) val session: Session? = null,
    @JsonProperty(required = false) val state: State? = null,
    @JsonProperty(required = false) val intents: List<String> = listOf(),
    @JsonProperty(required = false) val nlu: NLU? = null,
) {
    companion object
}

data class Request(val command: String = "", val markup: RequestMarkup)
data class RequestMarkup(@JsonProperty("dangerous_context") val dangerousContext: Boolean = false)
data class NLU(val tokens: List<String> = listOf())

data class Session(val new: Boolean)

data class State(val session: StateSession)
data class StateSession(val value: String = "")