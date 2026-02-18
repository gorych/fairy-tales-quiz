package org.gorych.alice.skill.fairytail.command

import org.gorych.alice.skill.core.api.RequestObject
import org.gorych.alice.skill.core.api.ResponseObject
import org.gorych.alice.skill.core.api.SessionState
import org.gorych.alice.skill.core.api.State
import org.gorych.alice.skill.core.command.Command
import org.gorych.alice.skill.fairytail.quiz.Quiz

class NextQuestionCommand : Command {

    override fun name() = NextQuestionCommand.name()

    override fun canHandle(requestObject: RequestObject): Boolean {
        return requestObject.hasCurrentQuestion() && requestObject.containsNextQuestionCommand()
    }

    override fun execute(requestObject: RequestObject): ResponseObject {
        val currentQuestion: Int? = requestObject.state?.session?.currentQuestion
        currentQuestion?.let {
            when {
                (currentQuestion > 0) -> {
                    return processUserAnswer(currentQuestion, requestObject)
                }

                else -> {
                    return ResponseObject.ofTechnicalError()
                }
            }
        }

        return ResponseObject.ofUnclearCommand()
    }

    private fun processUserAnswer(currentQuestion: Int, requestObject: RequestObject): ResponseObject {
        val rightAnswers: List<String> = Quiz.getAnswerFor(currentQuestion)
        val userAnswer: String = requestObject.command()

        if (rightAnswers.any { it == userAnswer }) {
            val nextQuestionNumber = currentQuestion + 1
            if (nextQuestionNumber <= Quiz.countOfQuestions()) {
                return nextQuestionResponse(nextQuestionNumber)
            }
            return endQuizResponse()
        }

        return wrongAnswerResponse(requestObject.state!!)
    }

    private fun nextQuestionResponse(nextQuestionNumber: Int) =
        ResponseObject.of(
            //@formatter:off
            text = "${Quiz.rightAnswerPhrases().random()} ${Quiz.nextQuestionPhrases().random()} ${Quiz.question(nextQuestionNumber)}",
            //@formatter:on
            state = SessionState(nextQuestionNumber, setOf(NextQuestionCommand.name())),
            endSession = false
        )

    private fun wrongAnswerResponse(state: State) =
        ResponseObject.of(
            text = Quiz.wrongAnswerPhrases().random(),
            state = state.session,
            endSession = false
        )

    private fun endQuizResponse() = ResponseObject.of(
        text = Quiz.winningPhrase(),
        endSession = true
    )

    companion object {
        fun name(): String = NextQuestionCommand::class.java.simpleName
    }
}