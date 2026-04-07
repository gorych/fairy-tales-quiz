package org.gorych.alice.skill.fairytail.quiz

import org.gorych.alice.skill.core.quiz.Quiz
import org.gorych.alice.skill.core.quiz.QuizItem
import org.gorych.alice.skill.core.quiz.QuizItem.Level.HARD

class BonusQuiz2 : Quiz(bonusQuiz = true) {

    override val items: List<QuizItem>
        get() = listOf(
            QuizItem(
                number = 1,
                level = HARD,
                question = "Какое было имя у девочки из сказки 'Снежная Королева'?",
                answers = listOf("Герда", "герда"),
                hints = listOf(
                    "Она спасла Кая.",
                    "Это слово начинается на букву 'Г'.",
                    "Последний слог этого слова - 'ДА'."
                )
            ),
            QuizItem(
                number = 2,
                level = HARD,
                question = "Как звали друга Чебурашки?",
                answers = listOf("крокодил Гена", "гена", "Гена", "крокодил гена"),
                hints = listOf(
                    "Это крокодил.",
                    "Его имя начинается на букву 'Г'.",
                )
            ),
            QuizItem(
                number = 3,
                level = HARD,
                question = "Какой персонаж имел голову-луковицу?",
                answers = listOf("Чиполлино", "чипалино", "чиполино", "чиполлино"),
                hints = listOf(
                    "Начальный слог этого слова - 'ЧИ'.",
                    "Второй слог этого слова - 'ПОЛ'.",
                    "Третий слог этого слова - 'ЛИ'.",
                    "Последний слог этого слова - 'НО'."
                )
            ),
            QuizItem(
                number = 4,
                level = HARD,
                question = "Какое время года наступило в сказке 'Зимовье зверей'?",
                answers = listOf("зима", "лютая зима"),
                hints = listOf(
                    "Это пора года после осени.",
                    "В эту пору выпадает снег.",
                )
            ),
            QuizItem(
                number = 5,
                level = HARD,
                question = "Что осталось от жар-птицы в руках у Ивана-царевича?",
                answers = listOf("перо", "перышко"),
                hints = listOf(
                    "Начальный слог этого слова - 'ПЕ'.",
                    "Последний слог этого слова - 'РО'.",
                )
            ),
        )
}