package org.gorych.alice.skill.core.quiz

import org.gorych.alice.skill.core.api.RequestObject

fun interface QuizSource {
    fun getQuiz(requestObject: RequestObject): Quiz
}
