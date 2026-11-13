package org.gorych.alice.skill.fairytail.command

import org.gorych.alice.skill.core.api.RequestObject
import org.gorych.alice.skill.core.api.ResponseObject
import org.gorych.alice.skill.core.command.Command
import org.gorych.alice.skill.core.quiz.QuizSource

private const val MODES_HELP_INTENT_ID = "g911.modes_help"

class ModesHelpCommand(private val quizzes: QuizSource) : Command {

    override fun name(): String = ModesHelpCommand.name()

    override fun canHandle(requestObject: RequestObject): Boolean {
        return requestObject.containsIntent(MODES_HELP_INTENT_ID)
    }

    override fun execute(requestObject: RequestObject): ResponseObject {
        return GameModeMenu.of(MODES_DESCRIPTION + CHOOSE_MODE_SUFFIX, quizzes.availableModes, requestObject)
    }

    companion object {
        const val MODES_DESCRIPTION = "" +
                "«Вопрос-ответ» — я задаю вопросы по сказкам, а ты отвечаешь. " +
                "В этом режиме можно попросить подсказку, повторить вопрос или пропустить его.\n" +
                "«Угадай по звуку» — я включаю аудиодорожку, а ты угадываешь сказку по услышанным звукам.\n" +
                "«Правда или ложь» — я зачитываю утверждение об известных сказках, " +
                "а ты говоришь, правда это или ложь."

        private const val CHOOSE_MODE_SUFFIX = "\nКакой режим выберешь?"

        fun name(): String = ModesHelpCommand::class.java.simpleName
    }
}
