package org.gorych.alice.skill.core.command

import org.gorych.alice.skill.core.api.RequestObject
import org.gorych.alice.skill.core.api.ResponseObject

private const val GRATITUDE_INTENT_ID = "g911.gratitude"

class GratitudeCommand : Command {

    override fun name(): String = GratitudeCommand.name()

    override fun canHandle(requestObject: RequestObject): Boolean {
        return requestObject.containsIntent(GRATITUDE_INTENT_ID)
    }

    override fun execute(requestObject: RequestObject): ResponseObject {
        val responseText = GRATITUDE_PHRASES.random()
        return ResponseObject.of(responseText, requestObject.state?.session, false)
    }

    companion object {
        val GRATITUDE_PHRASES = setOf("И тебе, спасибо!", "Не за что!", "Как приятно это слышать!")

        fun name(): String = GratitudeCommand::class.java.simpleName
    }
}