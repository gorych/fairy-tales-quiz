package org.gorych.alice.skill.fairytail.command

import org.gorych.alice.skill.core.api.RequestObject
import org.gorych.alice.skill.core.api.ResponseObject
import org.gorych.alice.skill.core.command.Command

private const val PARTING_INTENT_ID = "g911.parting"

class PartingCommand : Command {

    override fun name(): String = PartingCommand.name()

    override fun canHandle(requestObject: RequestObject): Boolean {
        return requestObject.containsIntent(PARTING_INTENT_ID)
    }

    override fun execute(requestObject: RequestObject): ResponseObject {
        requestObject.let {
            val responseText = when {
                it.containsToken("пока") -> "Пока. Заходи еще!"
                it.containsToken("прощай") -> "Прощай. Надеюсь, тебе понравилось!"
                else -> "До новых встреч!"
            }
            return ResponseObject.of(responseText, true)
        }
    }

    companion object {
        fun name(): String = PartingCommand::class.java.simpleName
    }
}