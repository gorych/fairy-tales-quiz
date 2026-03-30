package org.gorych.alice.skill.core.command

import org.gorych.alice.skill.core.api.RequestObject
import org.gorych.alice.skill.core.api.ResponseObject
import org.gorych.alice.skill.core.quiz.Quiz

private const val PARTING_INTENT_ID = "g911.parting"

class PartingCommand : Command {

    override fun name(): String = PartingCommand.name()

    override fun canHandle(requestObject: RequestObject): Boolean {
        return requestObject.containsIntent(PARTING_INTENT_ID)
    }

    override fun execute(requestObject: RequestObject, quiz: Quiz): ResponseObject {
        requestObject.let {
            val responseText = when {
                it.containsToken("пока") -> "Заходи еще!"
                it.containsToken("прощай") -> "Надеюсь, тебе понравилось!"
                else -> "До новых встреч!"
            }
            return ResponseObject.of(responseText, true)
        }
    }

    companion object {
        fun name(): String = PartingCommand::class.java.simpleName
    }
}