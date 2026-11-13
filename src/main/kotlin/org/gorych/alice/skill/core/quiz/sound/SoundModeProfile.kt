package org.gorych.alice.skill.core.quiz.sound

import org.gorych.alice.skill.core.api.Button
import org.gorych.alice.skill.core.mode.ModeProfile

internal val soundModeProfile = ModeProfile(
    achievements = emptyList(),
    excellentScore = 90,
    goodScore = 80,
    badScore = 30,
    openingPhrase = "Отлично. Слушай первый звук.",
    offersBonusQuiz = false,
    buttons = Button.skip_repeat_hint(),
    rightAnswerPhrases = setOf("Верно!", "Правильно!", "Точно!"),
    nextQuestionPhrases = setOf(
        "Слушай следующий звук.",
        "А вот ещё один звук.",
        "Следующий звук звучит так.",
    ),
    wrongAnswerPhrases = setOf("Неверно.", "Не узнал?", "Нет."),
)
