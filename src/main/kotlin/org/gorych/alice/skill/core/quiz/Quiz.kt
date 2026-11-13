package org.gorych.alice.skill.core.quiz

import org.gorych.alice.skill.core.api.RequestObject
import org.gorych.alice.skill.core.mode.GameMode

abstract class Quiz(val bonusQuiz: Boolean = false) {

    val usualQuiz = !bonusQuiz

    abstract val gameMode: GameMode

    fun name(): String = javaClass.simpleName

    abstract fun countOfQuestions(): Int

    abstract fun prompt(number: Int, phrase: String): Prompt

    abstract fun isCorrectAnswer(requestObject: RequestObject, number: Int): Boolean

    abstract fun hintTo(number: Int, hintNumber: Int): String

    open fun correctAnswerNote(number: Int): String = ""

    open fun wrongAnswerNote(number: Int): String = ""
}
