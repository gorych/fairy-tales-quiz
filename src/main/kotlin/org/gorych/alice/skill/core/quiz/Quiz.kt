package org.gorych.alice.skill.core.quiz

abstract class Quiz(val bonusQuiz: Boolean = false) {

    val usualQuiz = !bonusQuiz
    open val items: List<QuizItem> = listOf()

    fun name(): String = javaClass.simpleName

    fun question(number: Int): String {
        require(number > 0) { "Question number must be greater than zero" }
        return items[number - 1].question
    }

    fun answerTo(questionNumber: Int): List<String> {
        require(questionNumber > 0) { "Question number must be greater than zero" }
        return items[questionNumber - 1].answers
    }

    fun hintTo(questionNumber: Int, hintNumber: Int): String {
        require(questionNumber > 0) { "Question number must be greater than zero" }
        require(hintNumber > 0) { "Hint number must be greater than zero" }

        val quizItem = items[questionNumber - 1]
        val hints = quizItem.hints

        return when {
            hints.isEmpty() -> "Это слово начинается на букву '${answerTo(questionNumber)[0].first().uppercase()}'"
            (hintNumber <= hints.size) -> hints[hintNumber - 1]
            else -> "Правильный ответ - ${quizItem.answers[0]}."
        }
    }

    fun countOfQuestions(): Int = items.size
}