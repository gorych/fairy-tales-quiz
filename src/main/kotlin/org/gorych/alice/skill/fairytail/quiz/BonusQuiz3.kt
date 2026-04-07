package org.gorych.alice.skill.fairytail.quiz

import org.gorych.alice.skill.core.quiz.Quiz
import org.gorych.alice.skill.core.quiz.QuizItem
import org.gorych.alice.skill.core.quiz.QuizItem.Level.HARD

class BonusQuiz3 : Quiz(bonusQuiz = true) {

    override val items: List<QuizItem>
        get() = listOf(
            QuizItem(
                number = 1,
                level = HARD,
                question = "Из какого города были знаменитые бродячие музыканты?",
                answers = listOf("Бремен", "из Бремена", "бремен", "из бремена"),
                hints = listOf(
                    "Это город в Германии.",
                    "Название города начинается на букву 'Б'.",
                    "Последний слог этого слова 'МЕН'.",
                )
            ),
            QuizItem(
                number = 2,
                level = HARD,
                question = "Как звали крысу старухи Шапокляк?",
                answers = listOf("Лариска", "Лариса", "крыска Лариска", "лариска", "лариса", "крыска лариска"),
                hints = listOf(
                    "Начальный слог этого слова 'ЛА'.",
                    "Второй слог этого слова 'РИС'.",
                    "Последний слог этого слова 'КА'.",
                )
            ),
            QuizItem(
                number = 3,
                level = HARD,
                question = "Из какой крупы была каша, которую варил горшочек?",
                answers = listOf("пшено", "пшенная", "просо"),
                hints = listOf(
                    "Эта крупа желтого цвета.",
                    "Эта крупа получается из проса.",
                )
            ),
            QuizItem(
                number = 4,
                level = HARD,
                question = "Как звали кота из деревни Простоквашино?",
                answers = listOf("Матроскин", "кот Матроскин", "матроскин", "кот матроскин"),
                hints = listOf(
                    "Его фамилия связана с одеждой моряков.",
                    "Начальный слог этого слова 'МАТ'.",
                    "Второй слог этого слова 'РОС'.",
                    "Последний слог этого слова 'КИН'.",
                )
            ),
            QuizItem(
                number = 5,
                level = HARD,
                question = "Как назывался город, в котором жил Незнайка и его друзья?",
                answers = listOf("Цветочный город", "Цветочный", "цветочный город", "цветочный"),
                hints = listOf(
                    "В этом городе дома стояли среди огромных растений.",
                    "Это слово начинается на букву 'Ц'.",
                    "Второй слог этого слова 'ТОЧ'.",
                    "Последний слог этого слова 'НЫЙ'.",
                )
            ),
        )
}