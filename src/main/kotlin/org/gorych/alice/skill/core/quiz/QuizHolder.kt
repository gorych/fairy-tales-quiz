package org.gorych.alice.skill.core.quiz

import org.gorych.alice.skill.core.api.ApplicationState
import org.gorych.alice.skill.core.api.RequestObject
import org.gorych.alice.skill.core.mode.GameMode
import org.gorych.alice.skill.fairytail.quiz.*

class QuizHolder : QuizSource {

    private val questionAnswerQuizzes: List<Quiz> by lazy {
        listOf(
            Quiz1(),
            Quiz2(),
            Quiz3(),
            Quiz4(),
        )
    }

    private val questionAnswerBonusQuizzes: List<Quiz> by lazy {
        listOf(
            BonusQuiz1(),
            BonusQuiz2(),
            BonusQuiz3(),
            BonusQuiz4(),
        )
    }

    private val soundQuizzes: List<Quiz> by lazy { listOf(SoundQuiz1()) }
    private val truthOrLieQuizzes: List<Quiz> by lazy { listOf(TruthOrLieQuiz1()) }

    private val usualByMode: Map<GameMode, List<Quiz>> by lazy {
        mapOf(
            GameMode.QUESTION_ANSWER to questionAnswerQuizzes,
            GameMode.GUESS_SOUND to soundQuizzes,
            GameMode.TRUE_OR_FALSE to truthOrLieQuizzes,
        )
    }

    private val bonusByMode: Map<GameMode, List<Quiz>> by lazy {
        mapOf(
            GameMode.QUESTION_ANSWER to questionAnswerBonusQuizzes,
            GameMode.GUESS_SOUND to emptyList(),
            GameMode.TRUE_OR_FALSE to emptyList(),
        )
    }

    private val byNameAndMode: Map<GameMode, Map<String, Quiz>> by lazy {
        usualByMode.keys.associateWith { mode ->
            (usualByMode.getValue(mode) + bonusByMode.getValue(mode)).associateBy { it.name() }
        }
    }

    override val availableModes: Set<GameMode> by lazy { usualByMode.filterValues { it.isNotEmpty() }.keys }

    override fun getQuiz(requestObject: RequestObject): Quiz {
        val mode: GameMode = requestObject.gameMode()
            ?: error("A game mode must be selected before a quiz can be resolved")
        val usual: List<Quiz> = usualByMode.getValue(mode)
        val bonus: List<Quiz> = bonusByMode.getValue(mode)

        val applicationState: ApplicationState? = requestObject.state?.application
        val quizName = applicationState?.quizName

        return when {
            requestObject.isNewSession() ->
                selectRandomly(usual, quizName)

            bonus.isNotEmpty() && applicationState?.bonusQuiz == true && quizName == null ->
                selectRandomly(bonus, quizName = null)

            else -> byNameAndMode.getValue(mode)[quizName] ?: fallbackOf(mode)
        }
    }

    private fun fallbackOf(mode: GameMode): Quiz {
        return usualByMode.getValue(mode).firstOrNull()
            ?: error("There are no quizzes for the game mode $mode")
    }

    private fun selectRandomly(quizList: List<Quiz>, quizName: String?): Quiz {
        return quizList.filterNot { it.name() == quizName }.randomOrNull()
            ?: quizList.firstOrNull()
            ?: error("There are no quizzes for the requested game mode")
    }
}
