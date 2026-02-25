package org.gorych.alice.skill.core.command

import org.gorych.alice.skill.core.api.RequestObject
import org.gorych.alice.skill.core.api.ResponseObject

class PingCommand : Command {

    override fun name(): String = PingCommand.name()

    override fun canHandle(requestObject: RequestObject): Boolean {
        requestObject.request?.let {
            return it.command.isEmpty() && it.originalUtterance == "ping"
        }
        return false
    }

    override fun execute(requestObject: RequestObject): ResponseObject {
        requestObject.let {
            return ResponseObject.of(text = "OK", endSession = true)
        }
    }

    companion object {
        fun name(): String = PingCommand::class.java.simpleName
    }
}