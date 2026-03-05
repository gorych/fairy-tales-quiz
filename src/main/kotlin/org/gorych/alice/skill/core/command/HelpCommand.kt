package org.gorych.alice.skill.core.command

import org.gorych.alice.skill.core.api.Button
import org.gorych.alice.skill.core.api.RequestObject
import org.gorych.alice.skill.core.api.ResponseObject
import org.gorych.alice.skill.fairytail.command.HELP_INTENT_ID

private const val HELP_YANDEX_INTENT_ID = "YANDEX.HELP"

class HelpCommand : Command {

    override fun name(): String = HelpCommand.name()

    override fun canHandle(requestObject: RequestObject): Boolean {
        return requestObject.containsIntent(HELP_YANDEX_INTENT_ID)
                && !requestObject.containsIntent(HELP_INTENT_ID)
    }

    override fun execute(requestObject: RequestObject): ResponseObject {
        var responseText = "" +
                "Хорошо! Слушай список основных команд. " +
                "Чтобы послушать вопрос еще раз - скажи 'Алиса, повтори вопрос'. " +
                "Если ты не знаешь ответа на вопрос, используй команду  'Алиса, помоги'. " +
                "Для перехода к следующему вопросу - скажи 'Алиса, следующий вопрос'. " +
                "Для выхода - скажи 'стоп' или 'хватит'."

        val buttons: MutableList<Button> = mutableListOf()
        if (requestObject.containsPlayingAgreementCommand()) {
            responseText += " Ну что, поиграем?"
            buttons.addAll(Button.agreement_and_disagreement())
        }
        requestObject.let {
            return ResponseObject.of(responseText, buttons, requestObject.state?.session, false)
        }
    }

    companion object {
        fun name(): String = HelpCommand::class.java.simpleName
    }
}