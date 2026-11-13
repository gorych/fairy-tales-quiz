package org.gorych.alice.skill.core.mode

enum class GameMode(val title: String, val selectionIntentId: String) {
    QUESTION_ANSWER("Вопрос-ответ", "g911.mode_quiz"),
    GUESS_SOUND("Угадай по звуку", "g911.mode_sound"),
    TRUE_OR_FALSE("Правда или ложь", "g911.mode_truth_or_lie"),
}
