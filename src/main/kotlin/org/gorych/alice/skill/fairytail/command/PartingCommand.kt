package org.gorych.alice.skill.fairytail.command

import org.gorych.alice.skill.core.api.RequestObject
import org.gorych.alice.skill.core.command.Command

private const val PARTING_INTENT_ID = "g911.parting"

class PartingCommand : Command {

    override fun canHandle(requestObject: RequestObject): Boolean {
        return requestObject.request?.containsIntent(PARTING_INTENT_ID) ?: false
    }

    override fun execute(requestObject: RequestObject): String {
        requestObject.request?.let {
            return when {
                it.containsToken("пока") -> "Пока. Заходи еще!"
                it.containsToken("прощай") -> "Прощай. Надеюсь, тебе понравилось!"
                else -> "До новых встреч!"
            }
        }
        return "До новых встреч!"
    }

    override fun isClosingPhrase() = true
}