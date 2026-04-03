package org.gorych.alice.skill.fairytail.command

import org.gorych.alice.skill.core.api.Button
import org.gorych.alice.skill.core.api.RequestObject
import org.gorych.alice.skill.core.api.ResponseObject
import org.gorych.alice.skill.core.command.Command
import org.gorych.alice.skill.core.command.PartingCommand
import org.gorych.alice.skill.core.quiz.Quiz

class IKnowHowToRateCommand : Command {

    override fun name(): String = IKnowHowToRateCommand.name()

    override fun canHandle(requestObject: RequestObject): Boolean {
        return requestObject.containsIntent(I_KNOW_HOW_TO_RATE_INTENT_ID)
    }

    override fun execute(requestObject: RequestObject, quiz: Quiz): ResponseObject {
        if (!requestObject.containsCommand(name())) {
            return ResponseObject.ofUnclearCommand(requestObject)
        }

        return ResponseObject.of(
            text = "Отлично! С удовольствием ознакомлюсь с твоим мнением. Пока!",
            endSession = false,
            buttons = listOf(Button.rate(), Button.goodbye()),
            transitionCommands = setOf(RateCommand.name(), PartingCommand.name())
        )
    }

    companion object {
        private const val I_KNOW_HOW_TO_RATE_INTENT_ID = "g911.i_know_how_to_rate"

        fun name(): String = IKnowHowToRateCommand::class.java.simpleName
    }
}