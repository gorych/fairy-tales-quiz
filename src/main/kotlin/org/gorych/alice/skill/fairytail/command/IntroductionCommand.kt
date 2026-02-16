package org.gorych.alice.skill.fairytail.command

import org.gorych.alice.skill.core.api.RequestObject
import org.gorych.alice.skill.core.command.Command

class IntroductionCommand : Command {

    override fun canHandle(requestObject: RequestObject): Boolean {
        return requestObject.session?.new ?: false
    }

    override fun execute(requestObject: RequestObject): String {
        //TODO to be updated before moderation
        return "Привет! Я предлагаю проверить насколько хорошо ты знаешь сказки. Поиграем?"
    }
}