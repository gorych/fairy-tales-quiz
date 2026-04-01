package org.gorych.alice.skill.core.quiz

import org.gorych.alice.skill.core.api.ApplicationState
import org.gorych.alice.skill.core.api.RequestObject
import org.gorych.alice.skill.fairytail.quiz.Quiz1

class QuizHolder(quizzes: List<Quiz>) {

    private val quizzesMap: Map<String, Quiz> = quizzes.associateBy { it.name() }

    fun getQuiz(requestObject: RequestObject): Quiz {
        val applicationState: ApplicationState? = requestObject.state?.application

        val quizName = applicationState?.quizName

        if (requestObject.isNewSession()) {
            val usualQuizList: List<Quiz> = quizzesMap.values.filterNot { it.bonusQuiz }
            return selectQuizRandomly(usualQuizList, quizName)
        }

        val bonusQuiz = applicationState?.bonusQuiz ?: false
        if (bonusQuiz && quizName == null) {
            val bonusQuizList: List<Quiz> = quizzesMap.values.filter { it.bonusQuiz }
            return selectQuizRandomly(bonusQuizList, quizName)
        }

        val quiz = quizzesMap[quizName]
        if (quiz != null) {
            return quiz
        }

        return DEFAULT_QUIZ
    }

    private fun selectQuizRandomly(quizList: List<Quiz>, quizName: String?): Quiz {
        return when (quizName) {
            null -> quizList.random()
            else -> quizList
                .filterNot { it.name() == quizName }
                .random()
        }
    }

    companion object {
        val DEFAULT_QUIZ: Quiz = Quiz1()
    }
}