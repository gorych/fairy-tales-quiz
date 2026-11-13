package org.gorych.alice.skill.core.quiz

import org.gorych.alice.skill.core.api.RequestObject
import org.gorych.alice.skill.core.mode.GameMode

enum class Verdict { CORRECT, WRONG, UNCLEAR }

abstract class Quiz(val bonusQuiz: Boolean = false) {

    val usualQuiz = !bonusQuiz

    abstract val gameMode: GameMode

    fun name(): String = javaClass.simpleName

    abstract fun countOfQuestions(): Int

    abstract fun prompt(number: Int, phrase: String): Prompt

    abstract fun evaluate(requestObject: RequestObject, number: Int): Verdict

    abstract fun hintTo(number: Int, hintNumber: Int): String
}
