package org.gorych.alice.skill.fairytail.command

import org.gorych.alice.skill.core.api.RequestObject
import org.gorych.alice.skill.core.api.ResponseObject
import org.gorych.alice.skill.core.api.SessionState
import org.gorych.alice.skill.core.command.Command
import org.gorych.alice.skill.fairytail.quiz.Quiz

private const val AGREEMENT_INTENT_ID = "g911.agreement"

class PlayingAgreementCommand : Command {

    override fun name(): String = PlayingAgreementCommand.name()

    override fun canHandle(requestObject: RequestObject): Boolean {
        return requestObject.containsIntent(AGREEMENT_INTENT_ID)
    }

    override fun execute(requestObject: RequestObject): ResponseObject {
        if (requestObject.containsPlayingAgreementCommand() && !requestObject.hasCurrentQuestion()) {
            val questionNumber = 1
            return ResponseObject.of(
                text = "Отлично. Cлушай первый вопрос. ${Quiz.question(questionNumber)}",
                state = SessionState(questionNumber, setOf(NextQuestionCommand.name())),
                endSession = false
            )
        }

        return ResponseObject.ofUnclearCommand()
    }

    companion object {
        fun name(): String = PlayingAgreementCommand::class.java.simpleName
    }
}
