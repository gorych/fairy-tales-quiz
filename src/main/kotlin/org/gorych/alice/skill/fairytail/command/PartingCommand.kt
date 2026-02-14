package org.gorych.alice.skill.fairytail.command

import org.gorych.alice.skill.core.api.RequestObject
import org.gorych.alice.skill.core.command.Command

private const val PARTING_INTENT_ID = "g911.parting"

class PartingCommand : Command {

    override fun canHandle(requestObject: RequestObject): Boolean {
        return requestObject.intents
            .map { it.lowercase() }
            .any { it == PARTING_INTENT_ID }
    }

    override fun execute(requestObject: RequestObject): String {
        requestObject.nlu?.let {
            return when {
                it.tokens.contains("пока") -> "Пока. Заходи еще!"
                it.tokens.contains("прощай") -> "Прощай. Надеюсь, тебе понравилось!"
                else -> "До новых встреч!"
            }
        }
        return "До новых встреч!"
    }

    override fun isClosingPhrase() = true
}