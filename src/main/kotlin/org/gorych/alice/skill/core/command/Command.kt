package org.gorych.alice.skill.core.command

import org.gorych.alice.skill.core.api.RequestObject
import org.gorych.alice.skill.core.api.ResponseObject

interface Command {

    fun name(): String

    fun canHandle(requestObject: RequestObject): Boolean

    fun execute(requestObject: RequestObject): ResponseObject
}