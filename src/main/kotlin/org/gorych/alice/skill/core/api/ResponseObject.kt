package org.gorych.alice.skill.core.api

import com.fasterxml.jackson.annotation.JsonProperty

data class ResponseValue(
    val text: String,
    @JsonProperty("end_session") val endSession: Boolean,
)

data class Button(val title: String, val hide: Boolean = true)

data class ResponseObject(
    val response: ResponseValue,
    val version: String = "1.0",
    val buttons: List<Button> = listOf()
) {
    companion object {
        fun of(text: String, endSession: Boolean): ResponseObject {
            return ResponseObject(ResponseValue(text, endSession))
        }
    }
}