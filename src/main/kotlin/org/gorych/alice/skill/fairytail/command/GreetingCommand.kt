package org.gorych.alice.skill.fairytail.command

import org.gorych.alice.skill.core.api.RequestObject
import org.gorych.alice.skill.core.api.ResponseObject
import org.gorych.alice.skill.core.command.Command

private const val GREETING_INTENT_ID = "g911.greeting"

class GreetingCommand : Command {

    override fun name(): String = GreetingCommand.name()

    override fun canHandle(requestObject: RequestObject): Boolean {
        return requestObject.containsIntent(GREETING_INTENT_ID)
    }

    override fun execute(requestObject: RequestObject): ResponseObject {
        requestObject.let {
            val responseText = when {
                it.containsToken("привет") -> "И тебе, привет!"
                it.containsToken("здравствуй") -> "И тебе, здравствуй!"
                it.containsToken("здравствуйте") -> "И вам, здравствуйте!"
                else -> "И вам доброго времени суток!"
            }
            return ResponseObject.of(responseText, false)
        }
    }

    companion object {
        fun name(): String = GreetingCommand::class.java.simpleName
    }
}