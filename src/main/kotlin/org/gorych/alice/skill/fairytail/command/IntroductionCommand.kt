package org.gorych.alice.skill.fairytail.command

import org.gorych.alice.skill.core.COIN
import org.gorych.alice.skill.core.api.Button
import org.gorych.alice.skill.core.api.RequestObject
import org.gorych.alice.skill.core.api.ResponseObject
import org.gorych.alice.skill.core.api.SessionState
import org.gorych.alice.skill.core.command.Command
import org.gorych.alice.skill.core.quiz.Quiz

class IntroductionCommand : Command {

    override fun name(): String = IntroductionCommand.name()

    override fun canHandle(requestObject: RequestObject): Boolean {
        return requestObject.isNewSession()
    }

    override fun execute(requestObject: RequestObject, quiz: Quiz): ResponseObject {
        val responseText = "" +
                "Рада слышать тебя снова! Ты в гостях у 'Викторины по сказкам'. " +
                "Я подготовила для тебя каверзные вопросы, а от тебя жду верных ответов. " +
                "Если будет нужна помощь, просто скажи: Алиса, помоги. " +
                "Поиграем?"

        val state = SessionState(
            setOf(PlayingAgreementCommand.name(), PlayingDisagreementCommand.name())
        )

        return ResponseObject.of(
            text = responseText,
            tts = COIN + responseText,
            buttons = Button.agreement_disagreement_whatCanYouDo(),
            state = state,
            endSession = false
        )
    }

    companion object {
        fun name(): String = IntroductionCommand::class.java.simpleName
    }
}