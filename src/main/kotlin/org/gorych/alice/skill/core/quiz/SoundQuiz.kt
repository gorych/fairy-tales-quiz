package org.gorych.alice.skill.core.quiz

import org.gorych.alice.skill.core.api.RequestObject
import org.gorych.alice.skill.core.mode.GameMode
import org.gorych.alice.skill.core.soundTag

abstract class SoundQuiz : Quiz() {

    abstract val items: List<SoundQuestion>

    override val gameMode: GameMode = GameMode.GUESS_SOUND

    override fun countOfQuestions(): Int = items.size

    override fun prompt(number: Int, phrase: String): Prompt {
        val sound = soundTag(items[number - 1].soundId)
        return Prompt(text = "$phrase $QUESTION_TEXT", tts = "$phrase $sound $QUESTION_TEXT")
    }

    override fun isCorrectAnswer(requestObject: RequestObject, number: Int): Boolean {
        return items[number - 1].answers.any { it == requestObject.command() }
    }

    override fun hintTo(number: Int, hintNumber: Int): String {
        val question = items[number - 1]
        return when {
            hintNumber <= question.hints.size -> question.hints[hintNumber - 1]
            else -> "Правильный ответ - ${question.answers[0]}."
        }
    }

    companion object {
        private const val QUESTION_TEXT = "Угадай сказку по звукам."
    }
}
