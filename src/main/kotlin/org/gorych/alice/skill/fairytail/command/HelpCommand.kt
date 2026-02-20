package org.gorych.alice.skill.fairytail.command

import org.gorych.alice.skill.core.api.RequestObject
import org.gorych.alice.skill.core.api.ResponseObject
import org.gorych.alice.skill.core.api.SessionState
import org.gorych.alice.skill.core.command.Command
import org.gorych.alice.skill.fairytail.quiz.Quiz

private const val HELP_INTENT_ID = "g911.help"
private const val DOUBT_INTENT_ID = "g911.doubt"

class HelpCommand : Command {

    override fun name() = HelpCommand.name()

    override fun canHandle(requestObject: RequestObject): Boolean {
        val hasHelpOrDoubtIntent: Boolean =
            requestObject.containsIntent(HELP_INTENT_ID) || requestObject.containsIntent(DOUBT_INTENT_ID)
        return hasHelpOrDoubtIntent
                && requestObject.hasCurrentQuestion()
                && requestObject.containsNextQuestionCommand()
    }

    override fun execute(requestObject: RequestObject): ResponseObject {
        val requestSessionState: SessionState? = requestObject.state?.session

        val currentQuestion: Int? = requestSessionState?.currentQuestion
        currentQuestion?.let {
            when {
                (currentQuestion > 0) -> {
                    val firstLetterOfAnswerWord = Quiz.answerTo(currentQuestion)[0].first().uppercase()
                    return ResponseObject.of(
                        text = "${BEFORE_ANSWER_PHRASES.random()} Это слово начинается на букву '$firstLetterOfAnswerWord'",
                        state = requestSessionState,
                        endSession = false
                    )
                }

                else -> {
                    return ResponseObject.ofTechnicalError(requestObject)
                }
            }
        }

        return ResponseObject.ofUnclearCommand(requestObject)
    }

    companion object {
        val BEFORE_ANSWER_PHRASES = setOf("Без проблем.", "Хорошо, слушай подсказку.")

        fun name(): String = HelpCommand::class.java.simpleName
    }
}