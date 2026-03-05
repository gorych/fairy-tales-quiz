package org.gorych.alice.skill.core.command

import org.gorych.alice.skill.core.api.Button
import org.gorych.alice.skill.core.api.RequestObject
import org.gorych.alice.skill.core.api.ResponseObject

private const val WHAT_CAN_YOU_DO_YANDEX_INTENT_ID = "YANDEX.WHAT_CAN_YOU_DO"

class WhatCanYouDoCommand : Command {

    override fun name(): String = WhatCanYouDoCommand.name()

    override fun canHandle(requestObject: RequestObject): Boolean {
        return requestObject.containsIntent(WHAT_CAN_YOU_DO_YANDEX_INTENT_ID)
    }

    override fun execute(requestObject: RequestObject): ResponseObject {
        requestObject.let {
            var responseText = "" +
                    "Я прочитала много известных сказок и могу проверить как хорошо их знаешь ты. " +
                    "Игра происходит в режиме 'вопрос-ответ'. Я спрашиваю, а ты - отвечаешь. " +
                    "Если ты не знаешь ответа на вопрос, просто скажи 'Алиса, помоги' или 'Алиса, следующий вопрос'. " +
                    "Чтобы послушать вопрос еще раз - скажи 'Алиса, повтори вопрос'. " +
                    "Для выхода - скажи 'стоп' или 'хватит'."

            val buttons: MutableList<Button> = mutableListOf()
            if (requestObject.containsPlayingAgreementCommand()) {
                responseText += " Сыграем?"
                buttons.addAll(Button.agreement_and_disagreement())
            }
            return ResponseObject.of(responseText, buttons, requestObject.state?.session, false)
        }
    }

    companion object {
        fun name(): String = WhatCanYouDoCommand::class.java.simpleName
    }
}