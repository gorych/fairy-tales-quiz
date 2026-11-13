package org.gorych.alice.skill.core.quiz.truth_or_lie

import org.gorych.alice.skill.core.VICTORY_FANFARE
import org.gorych.alice.skill.core.api.Button
import org.gorych.alice.skill.core.mode.ModeProfile
import org.gorych.alice.skill.core.quiz.Achievement

private const val EXCELLENT_SCORE_MIN_VALUE = 90
private const val GOOD_SCORE_MIN_VALUE = 80
private const val BAD_SCORE_MAX_VALUE = 30

private const val FIVE_RIGHT_ANSWERS_ACHIEVEMENT_TEXT =
    "Пять верных ответов! Ты отличаешь правду от вымысла. Продолжим?"
private const val FIVE_RIGHT_ANSWERS_ACHIEVEMENT_MESSAGE = "5\uFE0F⃣ $FIVE_RIGHT_ANSWERS_ACHIEVEMENT_TEXT"
private const val FIVE_RIGHT_ANSWERS_ACHIEVEMENT_TTS = VICTORY_FANFARE + FIVE_RIGHT_ANSWERS_ACHIEVEMENT_TEXT

private const val TEN_RIGHT_ANSWERS_ACHIEVEMENT_TEXT =
    "Десять верных ответов - отличное чутьё на сказки. Идём дальше?"
private const val TEN_RIGHT_ANSWERS_ACHIEVEMENT_MESSAGE = "\uD83D\uDD1F $TEN_RIGHT_ANSWERS_ACHIEVEMENT_TEXT"
private const val TEN_RIGHT_ANSWERS_ACHIEVEMENT_TTS = VICTORY_FANFARE + TEN_RIGHT_ANSWERS_ACHIEVEMENT_TEXT

internal val truthOrLieModeProfile = ModeProfile(
    achievements = listOf(
        Achievement(
            rightAnswersCount = 5,
            text = FIVE_RIGHT_ANSWERS_ACHIEVEMENT_MESSAGE,
            tts = FIVE_RIGHT_ANSWERS_ACHIEVEMENT_TTS,
            imageId = "1540737/fouu4y25mjlerkqbj001"
        ),
        Achievement(
            rightAnswersCount = 10,
            text = TEN_RIGHT_ANSWERS_ACHIEVEMENT_MESSAGE,
            tts = TEN_RIGHT_ANSWERS_ACHIEVEMENT_TTS,
            imageId = "1533899/q2r93hurlg0sobsr1jqf"
        ),
    ),
    excellentScore = EXCELLENT_SCORE_MIN_VALUE,
    goodScore = GOOD_SCORE_MIN_VALUE,
    badScore = BAD_SCORE_MAX_VALUE,
    openingPhrase = "Отлично. Слушай первое утверждение.",
    offersBonusQuiz = false,
    buttons = listOf(Button.truth(), Button.lie()) + Button.skip_repeat_hint(),
    rightAnswerPhrases = setOf("Верно!", "Правильно!", "Точно!"),
    nextQuestionPhrases = setOf(
        "Слушай следующее утверждение.",
        "А вот ещё одно утверждение.",
        "Следующее утверждение звучит так.",
    ),
    wrongAnswerPhrases = setOf("Неверно.", "Нет-нет.", "Не так."),
)
