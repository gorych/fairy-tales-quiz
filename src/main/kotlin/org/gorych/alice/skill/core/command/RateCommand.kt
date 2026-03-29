package org.gorych.alice.skill.core.command

import org.gorych.alice.skill.core.api.Button
import org.gorych.alice.skill.core.api.RequestObject
import org.gorych.alice.skill.core.api.ResponseObject
import org.gorych.alice.skill.core.quiz.Quiz

private const val RATE_INTENT_ID = "g911.rate"

class RateCommand : Command {

    override fun name(): String = RateCommand.name()

    override fun canHandle(requestObject: RequestObject): Boolean {
        return requestObject.containsIntent(RATE_INTENT_ID)
    }

    override fun execute(requestObject: RequestObject, quiz: Quiz): ResponseObject {
        requestObject.let {
            val responseText = "\uD83D\uDE4F Спасибо! С твоей помощью викторина будет только интереснее!"
            return ResponseObject.of(responseText, false, buttons = listOf(Button.goodbye()))
        }
    }

    companion object {
        fun name(): String = RateCommand::class.java.simpleName
    }
}