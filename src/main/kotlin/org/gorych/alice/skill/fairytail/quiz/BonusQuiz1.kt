package org.gorych.alice.skill.fairytail.quiz

import org.gorych.alice.skill.core.quiz.Quiz
import org.gorych.alice.skill.core.quiz.QuizItem
import org.gorych.alice.skill.core.quiz.QuizItem.Level.HARD

class BonusQuiz1 : Quiz(bonusQuiz = true) {

    override val items: List<QuizItem>
        get() = listOf(
            QuizItem(
                number = 1,
                level = HARD,
                question = "К какому предмету обращалась злая мачеха, чтобы узнать, кто на свете всех милее?",
                answers = listOf("зеркальце", "зеркальцу", "к зеркальцу", "зеркало", "к зеркалу"),
                hints = listOf(
                    "Оно из стекла.",
                    "В нем можно увидеть своё отражение.",
                )
            ),
            QuizItem(
                number = 2,
                level = HARD,
                question = "Сколько раз забрасывал невод в море старик из сказки 'О рыбаке и рыбке'?",
                answers = listOf("3", "три"),
                hints = listOf(
                    "Раз, два..",
                    "Столько же сколько богатырей.",
                )
            ),
            QuizItem(
                number = 3,
                level = HARD,
                question = "Сколько месяцев сидело у костра в новогоднем лесу?",
                answers = listOf("12", "двенадцать"),
                hints = listOf(
                    "Это число состоит из двух цифр.",
                    "Вторая цифра в этом числе - '2'.",
                    "Это число между одиннадцатью и тринадцатью.",
                )
            ),
            QuizItem(
                number = 4,
                level = HARD,
                question = "Как называется скатерть, которая сама кормит?",
                answers = listOf("самобранка", "скатерть самобранка"),
                hints = listOf(
                    "Она сама себя собирает.",
                    "Вторая часть этого слова - 'БРАНКА'.",
                )
            ),
            QuizItem(
                number = 5,
                level = HARD,
                question = "Из какой сказки фраза: Лети, лети лепесток, через запад на восток...",
                answers = listOf("цветик-семицветик", "цветик семицветик", "цветик, семицветик", "цветик,семицветик"),
                hints = listOf(
                    "В этой фразе 2 слова.",
                    "Первое слово - 'цветик'.",
                    "Второе слово - 'семицветик'.",
                )
            ),
        )
}