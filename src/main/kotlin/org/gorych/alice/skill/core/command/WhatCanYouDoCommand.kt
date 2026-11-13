package org.gorych.alice.skill.core.command

import org.gorych.alice.skill.core.api.Button
import org.gorych.alice.skill.core.api.RequestObject
import org.gorych.alice.skill.core.api.ResponseObject
import org.gorych.alice.skill.fairytail.command.ModesHelpCommand

private const val WHAT_CAN_YOU_DO_YANDEX_INTENT_ID = "YANDEX.WHAT_CAN_YOU_DO"
private const val WHAT_CAN_YOU_DO_INTENT_ID = "g911.what_can_you_do"

private const val SKILL_DESCRIPTION = "" +
        "Я прочитала много известных сказок и могу проверить, как хорошо их знаешь ты. " +
        "У меня три режима игры:\n"

private const val CONTROLS_DESCRIPTION = "" +
        "Если не знаешь ответа — скажи «Алиса, помоги» или «Алиса, следующий вопрос». " +
        "Чтобы послушать вопрос ещё раз — скажи «Алиса, повтори вопрос». " +
        "Чтобы сменить режим — скажи «Алиса, другой режим». " +
        "Для выхода — скажи «стоп» или «хватит»."

class WhatCanYouDoCommand : Command {

    override fun name(): String = WhatCanYouDoCommand.name()

    override fun canHandle(requestObject: RequestObject): Boolean {
        return requestObject.containsIntent(WHAT_CAN_YOU_DO_YANDEX_INTENT_ID)
                || requestObject.containsIntent(WHAT_CAN_YOU_DO_INTENT_ID)
    }

    override fun execute(requestObject: RequestObject): ResponseObject {
        val responseText = SKILL_DESCRIPTION +
                ModesHelpCommand.MODES_DESCRIPTION + "\n" +
                CONTROLS_DESCRIPTION

        val buttons: MutableList<Button> = mutableListOf()
        if (requestObject.containsPlayingAgreementCommand()) {
            buttons.addAll(Button.agreement_and_disagreement())
        } else {
            buttons.addAll(Button.skip_repeat_hint_stop())
        }

        return ResponseObject.of(
            text = responseText,
            endSession = false,
            buttons = buttons,
            sessionState = requestObject.state?.session
        )
    }

    companion object {
        fun name(): String = WhatCanYouDoCommand::class.java.simpleName
    }
}
