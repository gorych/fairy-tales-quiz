package org.gorych.alice.skill.fairytail.command

import org.gorych.alice.skill.core.api.Button
import org.gorych.alice.skill.core.api.RequestObject
import org.gorych.alice.skill.core.api.ResponseObject
import org.gorych.alice.skill.core.command.Command
import org.gorych.alice.skill.core.command.PartingCommand
import org.gorych.alice.skill.core.quiz.Quiz

class IDoNotKnowHowToRateCommand : Command {

    override fun name(): String = IDoNotKnowHowToRateCommand.name()

    override fun canHandle(requestObject: RequestObject): Boolean {
        return requestObject.containsIntent(I_DO_NOT_KNOW_HOW_TO_RATE_INTENT_ID) && requestObject.containsCommand(name())
    }

    override fun execute(requestObject: RequestObject, quiz: Quiz): ResponseObject {
        return ResponseObject.of(
            text = RESPONSE_MESSAGE,
            tts = RESPONSE_TTS,
            endSession = false,
            buttons = listOf(Button.rate(), Button.goodbye()),
            transitionCommands = setOf(RateCommand.name(), PartingCommand.name())
        )
    }

    companion object {
        private const val I_DO_NOT_KNOW_HOW_TO_RATE_INTENT_ID = "g911.i_do_not_know_how_to_rate"

        private const val UNDERSTANDING = "Поняла. "
        private const val LIST_OF_ACTIONS = "Вот список основных действий: "
        private const val FIRST_ITEM_TEXT = "Зайди в каталог навыков в Яндекс Диалогах с любого устройства. "
        private const val SECOND_ITEM_TEXT = "В поисковой строке введи 'Викторина по сказкам'. "
        private const val THIRD_ITEM_TEXT = "Оставь свою оценку в разделе 'Отзывы'. "
        private const val PARTING = "Пока!"

        private const val RESPONSE_MESSAGE = UNDERSTANDING +
                "$LIST_OF_ACTIONS\n" +
                "1 - $FIRST_ITEM_TEXT\n" +
                "2 - $SECOND_ITEM_TEXT\n" +
                "3 - $THIRD_ITEM_TEXT\n" +
                PARTING
        private const val RESPONSE_TTS = "$UNDERSTANDING sil<[500]> " +
                "$LIST_OF_ACTIONS sil<[500]> " +
                "1 sil<[200]> - $FIRST_ITEM_TEXT " +
                "2 sil<[200]> - $SECOND_ITEM_TEXT " +
                "3 sil<[200]> - $THIRD_ITEM_TEXT " +
                "sil<[300]> $PARTING"

        fun name(): String = IDoNotKnowHowToRateCommand::class.java.simpleName
    }
}