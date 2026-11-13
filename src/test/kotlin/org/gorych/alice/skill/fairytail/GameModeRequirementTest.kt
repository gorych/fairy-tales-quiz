package org.gorych.alice.skill.fairytail

import org.gorych.alice.skill.core.command.*
import org.gorych.alice.skill.core.quiz.QuizSource
import org.gorych.alice.skill.fairytail.command.*
import org.gorych.alice.skill.fairytail.quiz.Quiz1
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class GameModeRequirementTest {

    private val quizzes = QuizSource { Quiz1() }

    @Test
    fun `WHEN gameplay command is asked THEN it should require a selected mode`() {
        val commands = listOf(
            PlayingAgreementCommand(quizzes),
            PlayingDisagreementCommand(),
            HintCommand(quizzes),
            RepeatQuestionCommand(quizzes),
            SkipQuestionCommand(quizzes),
            NextQuestionCommand(quizzes),
        )

        commands.forEach { assertTrue(it.needsGameMode(), it.name()) }
    }

    @Test
    fun `WHEN non gameplay command is asked THEN it should not require a selected mode`() {
        val commands = listOf(
            IntroductionCommand(quizzes),
            ModesHelpCommand(quizzes),
            ChangeGameModeCommand(quizzes),
            GameModeSelectionCommand(quizzes),
            GreetingCommand(),
            HelpCommand(),
            WhatCanYouDoCommand(),
            StopCommand(),
            RateCommand(),
            PingCommand(),
            PartingCommand(),
            GratitudeCommand(),
            IKnowHowToRateCommand(),
            IDoNotKnowHowToRateCommand(),
        )

        commands.forEach { assertFalse(it.needsGameMode(), it.name()) }
    }
}
