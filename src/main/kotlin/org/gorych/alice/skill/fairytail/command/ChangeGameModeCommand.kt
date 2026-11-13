package org.gorych.alice.skill.fairytail.command

import org.gorych.alice.skill.core.api.RequestObject
import org.gorych.alice.skill.core.api.ResponseObject
import org.gorych.alice.skill.core.command.Command
import org.gorych.alice.skill.core.quiz.QuizSource

private const val ANOTHER_MODE_INTENT_ID = "g911.another_mode"
private const val CHOOSE_MODE_PHRASE = "Хорошо, сменим режим. Какой выберешь?"

class ChangeGameModeCommand(private val quizzes: QuizSource) : Command {

    override fun name(): String = ChangeGameModeCommand.name()

    override fun canHandle(requestObject: RequestObject): Boolean {
        return requestObject.containsIntent(ANOTHER_MODE_INTENT_ID)
    }

    override fun execute(requestObject: RequestObject): ResponseObject {
        return GameModeMenu.of(CHOOSE_MODE_PHRASE, quizzes.availableModes, requestObject)
    }

    companion object {
        fun name(): String = ChangeGameModeCommand::class.java.simpleName
    }
}
