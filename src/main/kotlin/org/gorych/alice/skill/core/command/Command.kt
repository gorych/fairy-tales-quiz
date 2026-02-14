package org.gorych.alice.skill.core.command

import org.gorych.alice.skill.core.api.RequestObject

interface Command {

    fun canHandle(requestObject: RequestObject): Boolean

    fun execute(requestObject: RequestObject): String

    fun isClosingPhrase(): Boolean = false
}