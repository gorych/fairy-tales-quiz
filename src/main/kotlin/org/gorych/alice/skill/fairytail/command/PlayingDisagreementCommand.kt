package org.gorych.alice.skill.fairytail.command

import org.gorych.alice.skill.core.api.RequestObject
import org.gorych.alice.skill.core.api.ResponseObject
import org.gorych.alice.skill.core.command.Command
import org.gorych.alice.skill.core.quiz.Quiz

class PlayingDisagreementCommand : Command {

    override fun name() = PlayingDisagreementCommand.name()

    override fun canHandle(requestObject: RequestObject): Boolean {
        return requestObject.containsIntent(DISAGREEMENT_INTENT_ID) && requestObject.containsCommand(name())
    }

    override fun execute(requestObject: RequestObject, quiz: Quiz): ResponseObject {
        return ResponseObject.of(
            text = "Жаль! А так хотелось поиграть. Если станет скучно, ты знаешь как меня найти.",
            endSession = true
        )
    }

    companion object {
        private const val DISAGREEMENT_INTENT_ID = "g911.disagreement"

        fun name(): String = PlayingDisagreementCommand::class.java.simpleName
    }
}