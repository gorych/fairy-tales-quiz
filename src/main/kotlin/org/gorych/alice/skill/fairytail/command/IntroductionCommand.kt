package org.gorych.alice.skill.fairytail.command

import org.gorych.alice.skill.core.COIN
import org.gorych.alice.skill.core.api.*
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

        return ResponseObject.of(
            text = responseText,
            tts = "sil <[1000]> $COIN sil <[500]> $responseText",
            buttons = Button.agreement_disagreement_whatCanYouDo(),
            sessionState = SessionState(
                setOf(PlayingAgreementCommand.name(), PlayingDisagreementCommand.name())
            ),
            appState = ApplicationState(quiz),
            endSession = false,
        )
    }

    companion object {
        fun name(): String = IntroductionCommand::class.java.simpleName
    }
}