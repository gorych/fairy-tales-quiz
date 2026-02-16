package org.gorych.alice.skill.fairytail.command

import org.gorych.alice.skill.core.api.RequestObject
import org.gorych.alice.skill.core.command.Command

private const val GREETING_INTENT_ID = "g911.greeting"

class GreetingCommand : Command {

    override fun canHandle(requestObject: RequestObject): Boolean {
        return requestObject.request?.containsIntent(GREETING_INTENT_ID) ?: false
    }

    override fun execute(requestObject: RequestObject): String {
        requestObject.request?.let {
            return when {
                it.containsToken("привет") -> "И тебе, привет!"
                it.containsToken("здравствуй") -> "И тебе, здравствуй!"
                it.containsToken("здравствуйте") -> "И вам, здравствуйте!"
                else -> "И вам доброго времени суток!"
            }
        }
        return "И вам доброго времени суток!"
    }
}