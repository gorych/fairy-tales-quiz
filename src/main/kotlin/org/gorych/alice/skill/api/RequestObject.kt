package org.gorych.alice.skill.api

import com.fasterxml.jackson.annotation.JsonProperty

data class RequestObject(
    @JsonProperty(required = false) val request: Request? = null,
    @JsonProperty(required = false) val session: Session? = null,
    @JsonProperty(required = false) val state: State? = null
)

data class Request(val command: String = "", val markup: RequestMarkup)
data class RequestMarkup(@JsonProperty("dangerous_context") val dangerousContext: Boolean = false)

data class Session(val new: Boolean)

data class State(val session: StateSession)
data class StateSession(val value: String = "")