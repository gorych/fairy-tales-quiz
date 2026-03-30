package org.gorych.alice.skill.core.quiz

import org.gorych.alice.skill.core.api.ApplicationState
import org.gorych.alice.skill.core.api.RequestObject
import org.gorych.alice.skill.fairytail.quiz.Quiz1

class QuizHolder(quizzes: List<Quiz>) {

    private val quizzesMap: Map<String, Quiz> = quizzes.associateBy { it.name() }

    fun getQuiz(requestObject: RequestObject): Quiz {
        val applicationState: ApplicationState? = requestObject.state?.application

        val quizName = applicationState?.quizName
        val bonusQuiz = applicationState?.bonusQuiz

        if (requestObject.isNewSession()) {
            val filteredQuizList: List<Quiz> = quizzesMap.values
                .filter { it.bonusQuiz == bonusQuiz }
                .toList()
            return when (quizName) {
                null -> filteredQuizList.random()
                else -> filteredQuizList
                    .filterNot { it.name() == quizName }
                    .random()
            }
        }

        val quiz = quizzesMap[quizName]
        if (quiz != null) {
            return quiz
        }

        return DEFAULT_QUIZ
    }

    companion object {
        val DEFAULT_QUIZ: Quiz = Quiz1()
    }
}