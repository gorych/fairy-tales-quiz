package org.gorych.alice.skill.fairytail.command

import org.gorych.alice.skill.core.api.RequestObject
import org.gorych.alice.skill.core.api.ResponseObject
import org.gorych.alice.skill.core.command.Command

private const val STOP_INTENT_ID = "g911.stop"

class StopCommand : Command {

    override fun name(): String = StopCommand.name()

    override fun canHandle(requestObject: RequestObject): Boolean {
        return requestObject.containsIntent(STOP_INTENT_ID)
    }

    override fun execute(requestObject: RequestObject): ResponseObject {
        requestObject.let {
            val responseText = "Поняла. Если станет скучно - заходи!"
            return ResponseObject.of(responseText, true)
        }
    }

    companion object {
        fun name(): String = StopCommand::class.java.simpleName
    }
}