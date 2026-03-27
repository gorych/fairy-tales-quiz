package org.gorych.alice.skill.core.quiz

import org.gorych.alice.skill.core.api.RequestObject
import org.gorych.alice.skill.fairytail.quiz.Quiz1

class QuizHolder(quizzes: List<Quiz>) {

    private val quizzesMap: Map<String, Quiz> = quizzes.associateBy { it.name() }

    fun getQuiz(requestObject: RequestObject): Quiz {
        val quizName = requestObject.state?.application?.quizName
        if (requestObject.isNewSession()) {
            val quizList = quizzesMap.values
            return when (quizName) {
                null -> quizList.random()
                else -> quizList
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