package org.gorych.alice.skill.core.quiz.truth_or_lie

import org.gorych.alice.skill.core.api.RequestObject
import org.gorych.alice.skill.core.mode.GameMode
import org.gorych.alice.skill.core.quiz.Prompt
import org.gorych.alice.skill.core.quiz.Quiz

abstract class TruthOrLieQuiz : Quiz() {

    abstract val items: List<TruthStatement>

    override val gameMode: GameMode = GameMode.TRUE_OR_FALSE

    override fun countOfQuestions(): Int = items.size

    override fun prompt(number: Int, phrase: String): Prompt {
        val statement = items[number - 1].statement
        return Prompt(text = "$phrase $statement", tts = "$phrase $statement")
    }

    override fun isCorrectAnswer(requestObject: RequestObject, number: Int): Boolean {
        val statement = items[number - 1]
        return when {
            requestObject.containsIntent(TRUTH_INTENT_ID) -> statement.isTrue
            requestObject.containsIntent(LIE_INTENT_ID) -> !statement.isTrue
            else -> false
        }
    }

    override fun hintTo(number: Int, hintNumber: Int): String {
        val statement = items[number - 1]
        return when {
            hintNumber <= statement.hints.size -> statement.hints[hintNumber - 1]
            statement.isTrue -> "Правильный ответ - правда."
            else -> "Правильный ответ - ложь."
        }
    }

    override fun correctAnswerNote(number: Int): String = items[number - 1].explanation

    override fun wrongAnswerNote(number: Int): String = items[number - 1].explanation

    companion object {
        private const val TRUTH_INTENT_ID = "g911.truth"
        private const val LIE_INTENT_ID = "g911.lie"
    }
}
