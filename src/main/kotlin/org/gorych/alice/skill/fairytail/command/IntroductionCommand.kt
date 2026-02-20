package org.gorych.alice.skill.fairytail.command

import org.gorych.alice.skill.core.api.RequestObject
import org.gorych.alice.skill.core.api.ResponseObject
import org.gorych.alice.skill.core.api.SessionState
import org.gorych.alice.skill.core.command.Command

class IntroductionCommand : Command {

    override fun name(): String = IntroductionCommand.name()

    override fun canHandle(requestObject: RequestObject): Boolean {
        return requestObject.isNewSession()
    }

    override fun execute(requestObject: RequestObject): ResponseObject {
        val responseText = "" +
                "Привет! " +
                "Я предлагаю проверить насколько хорошо ты знаешь сказки. " +
                "Я буду задавать вопросы, а тебе нужно отвечать на них. " +
                "Если будет нужна помощь, просто скажи: Алиса, помоги. " +
                "Поиграем?"

        val state = SessionState(
            setOf(PlayingAgreementCommand.name(), PlayingDisagreementCommand.name())
        )

        return ResponseObject.of(responseText, state, false)
    }

    companion object {
        fun name(): String = IntroductionCommand::class.java.simpleName
    }
}