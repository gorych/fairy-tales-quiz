package org.gorych.alice.skill.fairytail.command

import org.gorych.alice.skill.core.api.Button
import org.gorych.alice.skill.core.api.RequestObject
import org.gorych.alice.skill.core.api.ResponseObject
import org.gorych.alice.skill.core.command.Command
import org.gorych.alice.skill.core.command.PartingCommand
import org.gorych.alice.skill.core.command.RateCommand
import org.gorych.alice.skill.core.quiz.Quiz

private const val DISAGREEMENT_INTENT_ID = "g911.disagreement"

class PlayingDisagreementCommand : Command {

    override fun name() = PlayingDisagreementCommand.name()

    override fun canHandle(requestObject: RequestObject): Boolean {
        return requestObject.containsIntent(DISAGREEMENT_INTENT_ID)
    }

    override fun execute(requestObject: RequestObject, quiz: Quiz): ResponseObject {
        if (requestObject.containsPlayingDisagreementCommand()) {
            return when {
                quiz.bonusQuiz -> rateActionsResponse()
                else -> regretResponse()
            }

        }

        return ResponseObject.ofUnclearCommand(requestObject)
    }

    private fun rateActionsResponse() = ResponseObject.of(
        text = "Поняла. Вот список основных дествий: \n" +
                "1 - Зайди в каталог навыков в Яндекс Диалогах с любого устройства. \n" +
                "2 - В поисковой строке введи 'Викторина по сказкам'. \n" +
                "3 - Оставь свою оценку в секции 'Отзывы'. \n" +
                "Пока!",
        endSession = false,
        buttons = listOf(Button.rate(), Button.goodbye()),
        transitionCommands = setOf(RateCommand.name(), PartingCommand.name())
    )

    private fun regretResponse() = ResponseObject.of(
        text = "Жаль! А так хотелось поиграть. Если станет скучно, ты знаешь как меня найти.",
        endSession = true
    )

    companion object {
        fun name(): String = PlayingDisagreementCommand::class.java.simpleName
    }
}