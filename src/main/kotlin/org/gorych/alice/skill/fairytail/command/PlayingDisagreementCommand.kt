package org.gorych.alice.skill.fairytail.command

import org.gorych.alice.skill.core.api.RequestObject
import org.gorych.alice.skill.core.api.ResponseObject
import org.gorych.alice.skill.core.command.Command

private const val DISAGREEMENT_INTENT_ID = "g911.disagreement"

class PlayingDisagreementCommand : Command {

    override fun name() = PlayingDisagreementCommand.name()

    override fun canHandle(requestObject: RequestObject): Boolean {
        return requestObject.containsIntent(DISAGREEMENT_INTENT_ID)
    }

    override fun execute(requestObject: RequestObject): ResponseObject {
        if (requestObject.containsPlayingDisagreementCommand() && !requestObject.hasCurrentQuestion()) {
            return ResponseObject.of(
                text = "Жаль! А так хотелось поиграть. Если станет скучно, ты знаешь как меня найти",
                endSession = true
            )
        }

        return ResponseObject.ofUnclearCommand(requestObject)
    }

    companion object {
        fun name(): String = PlayingDisagreementCommand::class.java.simpleName
    }
}