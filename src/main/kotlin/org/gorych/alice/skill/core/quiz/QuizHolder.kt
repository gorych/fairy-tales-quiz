package org.gorych.alice.skill.core.quiz

import org.gorych.alice.skill.core.api.ApplicationState
import org.gorych.alice.skill.core.api.RequestObject
import org.gorych.alice.skill.fairytail.quiz.*

class QuizHolder {

    private val allQuizzes: List<Quiz> by lazy {
        listOf(
            Quiz1(),
            Quiz2(),
            Quiz3(),

            BonusQuiz1(),
            BonusQuiz2(),
            BonusQuiz3(),
        )
    }

    private val partitionedQuizLists by lazy { allQuizzes.partition { it.bonusQuiz } }

    private val bonusQuizList get() = partitionedQuizLists.first
    private val usualQuizList get() = partitionedQuizLists.second

    private val allQuizzesMap: Map<String, Quiz> by lazy {
        allQuizzes.associateBy { it.name() }
    }

    fun getQuiz(requestObject: RequestObject): Quiz {
        val applicationState: ApplicationState? = requestObject.state?.application

        val quizName = applicationState?.quizName

        return when {
            requestObject.isNewSession() ->
                selectQuizRandomly(usualQuizList, quizName)

            applicationState?.bonusQuiz == true && quizName == null ->
                selectQuizRandomly(bonusQuizList, quizName = null)

            else -> allQuizzesMap[quizName] ?: DEFAULT_QUIZ
        }
    }

    private fun selectQuizRandomly(quizList: List<Quiz>, quizName: String?): Quiz {
        return when (quizName) {
            null -> quizList.randomOrNull()
            else -> quizList
                .filterNot { it.name() == quizName }
                .randomOrNull()
        } ?: DEFAULT_QUIZ
    }

    companion object {
        val DEFAULT_QUIZ: Quiz = Quiz1()
    }
}