package org.gorych.alice.skill.fairytail.command

import org.gorych.alice.skill.core.api.RequestObject
import org.gorych.alice.skill.core.command.Command

private const val GREETING_INTENT_ID = "g911.greeting"

class GreetingCommand : Command {

    override fun canHandle(requestObject: RequestObject): Boolean {
        return requestObject.intents
            .map { it.lowercase() }
            .any { it == GREETING_INTENT_ID }
    }

    override fun execute(requestObject: RequestObject): String {
        requestObject.nlu?.let {
            return when {
                it.tokens.contains("привет") -> "И тебе, привет!"
                it.tokens.contains("здравствуй") -> "И тебе, здравствуй!"
                it.tokens.contains("здравствуйте") -> "И вам, здравствуйте!"
                else -> "И вам доброго времени суток!"
            }
        }
        return "И вам доброго времени суток!"
    }
}