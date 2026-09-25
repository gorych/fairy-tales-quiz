package org.gorych.alice.skill.fairytail.quiz

import org.gorych.alice.skill.core.quiz.Quiz
import org.gorych.alice.skill.core.quiz.QuizItem

class BonusQuiz4 : Quiz(bonusQuiz = true) {

    override val items: List<QuizItem>
        get() = listOf(
            QuizItem(
                number = 1,
                question = "Кто помог Ивану-царевичу найти иголку в яйце?",
                answers = listOf("щука"),
                hints = listOf(
                    "Она достала яйцо со дна моря.",
                    "Это речная хищная рыба.",
                )
            ),
            QuizItem(
                number = 2,
                question = "Как звали пуделя Мальвины?",
                answers = listOf("Артемон", "артемон"),
                hints = listOf(
                    "Он защищал её от Карабаса-Барабаса.",
                    "Его имя начинается на 'А'.",
                )
            ),
            QuizItem(
                number = 3,
                question = "На чем летал старик Хоттабыч?",
                answers = listOf("ковер-самолет", "ковер"),
                hints = listOf(
                    "Это лежит на полу, но в сказке умеет летать.",
                    "Это мягкое и ворсистое изделие.",
                )
            ),
            QuizItem(
                number = 4,
                question = "Как называются сапоги, которые позволяют делать огромные шаги и быстро передвигаться?",
                answers = listOf("сапоги-скороходы", "скороходы"),
                hints = listOf(
                    "Они скоро ходят."
                )
            ),
            QuizItem(
                number = 5,
                question = "Сколько золотых монет было у Буратино, когда он отправился в Страну Дураков?",
                answers = listOf("5", "пять"),
                hints = listOf(
                    "Их количество совпадает с числом пальцев на одной руке.",
                    "Это число меньше шести.",
                )
            ),
        )
}