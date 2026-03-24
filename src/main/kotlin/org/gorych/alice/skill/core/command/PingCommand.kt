package org.gorych.alice.skill.core.command

import org.gorych.alice.skill.core.api.RequestObject
import org.gorych.alice.skill.core.api.ResponseObject
import org.gorych.alice.skill.core.quiz.Quiz

class PingCommand : Command {

    override fun name(): String = PingCommand.name()

    override fun canHandle(requestObject: RequestObject): Boolean {
        requestObject.request?.let {
            return it.command.isEmpty() && it.originalUtterance == "ping"
        }
        return false
    }

    override fun execute(requestObject: RequestObject, quiz: Quiz): ResponseObject {
        log("execute: start")
        return ResponseObject.of(text = "OK", endSession = true)
    }

    companion object {
        fun name(): String = PingCommand::class.java.simpleName
    }
}