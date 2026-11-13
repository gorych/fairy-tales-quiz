package org.gorych.alice.skill.core.quiz.question_answer

import org.gorych.alice.skill.core.api.BigImageCard
import org.gorych.alice.skill.core.api.RequestObject
import org.gorych.alice.skill.core.mode.GameMode
import org.gorych.alice.skill.core.quiz.Prompt
import org.gorych.alice.skill.core.quiz.Quiz
import org.gorych.alice.skill.core.quiz.QuizItem

abstract class QuestionAnswerQuiz(bonusQuiz: Boolean = false) : Quiz(bonusQuiz) {

    abstract val items: List<QuizItem>

    override val gameMode: GameMode = GameMode.QUESTION_ANSWER

    override fun countOfQuestions(): Int = items.size

    override fun prompt(number: Int, phrase: String): Prompt {
        val question = question(number)
        val card = imageIdTo(number)?.let {
            BigImageCard(imageId = it, title = phrase, description = "$number. $question")
        }

        return Prompt(
            text = "$phrase $question",
            tts = "$phrase $question",
            card = card,
        )
    }

    override fun isCorrectAnswer(requestObject: RequestObject, number: Int): Boolean {
        return answerTo(number).any { it == requestObject.command() }
    }

    override fun hintTo(number: Int, hintNumber: Int): String {
        require(number > 0) { "Question number must be greater than zero" }
        require(hintNumber > 0) { "Hint number must be greater than zero" }

        val quizItem = items[number - 1]
        val hints = quizItem.hints

        return when {
            hints.isEmpty() -> "Это слово начинается на букву '${answerTo(number)[0].first().uppercase()}'"
            (hintNumber <= hints.size) -> hints[hintNumber - 1]
            else -> "Правильный ответ - ${quizItem.answers[0]}."
        }
    }

    fun question(number: Int): String {
        require(number > 0) { "Question number must be greater than zero" }
        return items[number - 1].question
    }

    fun answerTo(questionNumber: Int): List<String> {
        require(questionNumber > 0) { "Question number must be greater than zero" }
        return items[questionNumber - 1].answers
    }

    fun imageIdTo(questionNumber: Int): String? {
        require(questionNumber > 0) { "Question number must be greater than zero" }
        return items[questionNumber - 1].imageId
    }
}
