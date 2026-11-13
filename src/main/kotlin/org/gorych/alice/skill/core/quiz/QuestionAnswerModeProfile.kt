package org.gorych.alice.skill.core.quiz

import org.gorych.alice.skill.core.VICTORY_FANFARE
import org.gorych.alice.skill.core.api.Button

private const val EXCELLENT_SCORE_MIN_VALUE = 90
private const val GOOD_SCORE_MIN_VALUE = 80
private const val BAD_SCORE_MAX_VALUE = 30

private const val FIVE_RIGHT_ANSWERS_ACHIEVEMENT_TEXT =
    "Поздравляю! Первое достижение из пяти правильных ответов у тебя в кармане. Идём дальше?"
private const val FIVE_RIGHT_ANSWERS_ACHIEVEMENT_MESSAGE = "5\uFE0F⃣ $FIVE_RIGHT_ANSWERS_ACHIEVEMENT_TEXT"
private const val FIVE_RIGHT_ANSWERS_ACHIEVEMENT_TTS = VICTORY_FANFARE + FIVE_RIGHT_ANSWERS_ACHIEVEMENT_TEXT

private const val TEN_RIGHT_ANSWERS_ACHIEVEMENT_TEXT =
    "Десять правильных ответов - это серьезная заявка на победу. Перейдём к вопросам посложнее?"
private const val TEN_RIGHT_ANSWERS_ACHIEVEMENT_MESSAGE = "\uD83D\uDD1F $TEN_RIGHT_ANSWERS_ACHIEVEMENT_TEXT"
private const val TEN_RIGHT_ANSWERS_ACHIEVEMENT_TTS = VICTORY_FANFARE + TEN_RIGHT_ANSWERS_ACHIEVEMENT_TEXT

private const val TWENTY_RIGHT_ANSWERS_ACHIEVEMENT_TEXT =
    "Твои успехи впечатляют — уже 20 правильных ответов. Продолжим? Впереди самое интересное."
private const val TWENTY_RIGHT_ANSWERS_ACHIEVEMENT_MESSAGE =
    "2\uFE0F⃣0\uFE0F⃣ $TWENTY_RIGHT_ANSWERS_ACHIEVEMENT_TEXT"
private const val TWENTY_RIGHT_ANSWERS_ACHIEVEMENT_TTS = VICTORY_FANFARE + TWENTY_RIGHT_ANSWERS_ACHIEVEMENT_TEXT

internal val questionAnswerModeProfile = ModeProfile(
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
        Achievement(
            rightAnswersCount = 20,
            text = TWENTY_RIGHT_ANSWERS_ACHIEVEMENT_MESSAGE,
            tts = TWENTY_RIGHT_ANSWERS_ACHIEVEMENT_TTS,
            imageId = "1030494/68kybvayf4ptqww27auq"
        ),
    ),
    excellentScore = EXCELLENT_SCORE_MIN_VALUE,
    goodScore = GOOD_SCORE_MIN_VALUE,
    badScore = BAD_SCORE_MAX_VALUE,
    openingPhrase = "Отлично. Слушай первый вопрос.",
    offersBonusQuiz = true,
    buttons = Button.skip_repeat_hint(),
    rightAnswerPhrases = setOf("Верно!", "Правильно!", "Ты молодец!", "Совершенно верно! Так держать!"),
    nextQuestionPhrases = setOf(
        "Слушай следующий вопрос.",
        "Следующий вопрос звучит так.",
        "Приготовься, следующий вопрос будет сложнее."
    ),
    wrongAnswerPhrases = setOf("Неверно. Подумай ещё!", "Нет. Попытайся ещё раз!", "Неправильно. Попробуй другой вариант!"),
)
