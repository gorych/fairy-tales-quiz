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
            text = "Поняла. Вот список основных дествий: \n" +
                    "1 - Зайди в каталог навыков в Яндекс Диалогах с любого устройства. \n" +
                    "2 - В поисковой строке введи 'Викторина по сказкам'. \n" +
                    "3 - Оставь свою оценку в секции 'Отзывы'. \n" +
                    "Пока!",
            endSession = false,
            buttons = listOf(Button.rate(), Button.goodbye()),
            transitionCommands = setOf(RateCommand.name(), PartingCommand.name())
        )
    }

    companion object {
        private const val I_DO_NOT_KNOW_HOW_TO_RATE_INTENT_ID = "g911.i_do_not_know_how_to_rate"

        fun name(): String = IDoNotKnowHowToRateCommand::class.java.simpleName
    }
}