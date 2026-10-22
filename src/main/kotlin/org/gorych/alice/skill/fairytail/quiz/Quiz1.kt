package org.gorych.alice.skill.fairytail.quiz

import org.gorych.alice.skill.core.quiz.Quiz
import org.gorych.alice.skill.core.quiz.QuizItem

class Quiz1 : Quiz() {

    override val items: List<QuizItem>
        get() = listOf(
            QuizItem(
                number = 1,
                question = "Кто посадил репку?",
                answers = listOf("дедка", "деда", "дед", "детка"),
                hints = listOf("Он живёт с бабкой.", "Это слово начинается на букву 'Д'."),
                imageId = "213044/fn6tcye576l04syaqxi0",
            ),
            QuizItem(
                number = 2,
                question = "Кто съел бабушку красной шапочки?",
                answers = listOf("волк"),
                hints = listOf("Он живёт в лесу.", "Его кума - лиса.", "Он серого цвета."),
                imageId = "14236656/anv5sa3jbtv43budzl3e",
            ),
            QuizItem(
                number = 3,
                question = "Как звали курочку, которая снесла золотое яичко?",
                answers = listOf("курочка ряба", "ряба"),
                hints = listOf(
                    "Это слово начинается на букву 'Р'.",
                    "Вторая буква в этом слове - 'Я'.",
                    "Последний слог этого слова - 'БА'.",
                ),
                imageId = "14236656/v3vzwbrs29fmdp2ckifm",
            ),
            QuizItem(
                number = 4,
                question = "Кто съел колобка?",
                answers = listOf("лиса"),
                hints = listOf("Её кум - волк.", "Она рыжая и живёт в лесу."),
                imageId = "213044/1gfhobbwnqii3em6l40c",
            ),
            QuizItem(
                number = 5,
                question = "Что купила муха на базаре?",
                answers = listOf("самовар"),
                hints = listOf(
                    "Он сам варит.",
                    "Начальный слог этого слова - 'СА'.",
                    "Средний слог этого слова - 'МО'.",
                    "Последний слог этого слова - 'ВАР'."
                ),
                imageId = "997614/sl4m93g4iexy37nbb2m2",
            ),
            QuizItem(
                number = 6,
                question = "Сколько гномов было у белоснежки?",
                answers = listOf("7", "семь"),
                hints = listOf(
                    "Их было столько же, сколько козлят в сказке про волка.",
                    "Это на один меньше, чем 8."
                ),
                imageId = "213044/gs79m6ezzdja8nxnhdhz",
            ),
            QuizItem(
                number = 7,
                question = "Ниф-ниф, наф-наф, а кто третий?",
                answers = listOf("Нуф-нуф", "нуф-нуф", "нуф нуф", "нуфнуф"),
                hints = listOf(
                    "Первая часть ответа содержит слово 'НУФ'.",
                    "Вторая часть полностью совпадает с первой."
                ),
                imageId = "1030494/eflhlsj7loxcxd8vsv65",
            ),
            //region Level 2
            QuizItem(
                number = 8,

                question = "Как звали доктора, который лечил всех зверей?",
                answers = listOf("Айболит", "айболит", "доктор айболит", "ай-болит"),
                hints = listOf(
                    "Этот доктор сидит под деревом.",
                    "Его имя содержит слово 'болит'.",
                    "Начальный слог этого слова - 'АЙ'."
                ),
                imageId = "965417/8yivnxo5vyr86lszrj03",
            ),
            QuizItem(
                number = 9,
                question = "Продолжи фразу: У меня зазвонил телефон, кто говорит?..",
                answers = listOf("слон"),
                hints = listOf("У него есть хобот.", "Это самое крупное животное на земле."),
                imageId = "1521359/52pvlwzc77iv0zdyzuzo",
            ),
            QuizItem(
                number = 10,
                question = "В какой сказке встречается фраза: 'Кто хлебал в моей чашке'?",
                answers = listOf("три медведя", "3 медведя"),
                hints = listOf("В этой фразе 2 слова.", "Первое слово 'три'.", "Второе слово 'медведя'."),
                imageId = "1533899/nxzll2bp7yywgevcv5s2",
            ),
            QuizItem(
                number = 11,
                question = "Как звали девочку, которая произносила фразу: 'Не садись на пенёк, не ешь пирожок!'?",
                answers = listOf("Маша", "маша", "машенька"),
                hints = listOf(
                    "Это имя отличается только одной буквой от слова 'саша'.",
                    "Это слово начинается на букву 'М'."
                ),
                imageId = "1533899/zsuxp7afjpdk7n2bscby",
            ),
            QuizItem(
                number = 12,
                question = "Как звали главного героя сказки 'По щучьему веленью'?",
                answers = listOf("Емеля", "емеля", "емеля-дурак", "емеля дурак", "емеля-дурачок", "емеля дурачок"),
                hints = listOf(
                    "В этом слове 5 букв.",
                    "Это слово начинается на букву 'Е'.",
                    "Последний слог этого слова - 'ЛЯ'."
                ),
                imageId = "1521359/szatcslg2vtkj84vme19",
            ),
            QuizItem(
                number = 13,
                question = "Как называется сказка, в которой гуси украли братика у девочки?",
                answers = listOf("гуси-лебеди", "гуси лебеди"),
                hints = listOf("В этой фразе 2 слова.", "Первое слово 'гуси'.", "Второе слово 'лебеди'."),
                imageId = "14236656/yhocc8tsrdekj3viir5a",
            ),
            QuizItem(
                number = 14,
                question = "Продолжи фразу Алёнушки: Не пей, братец, ...",
                answers = listOf("козлёночком станешь", "козленочком станешь"),
                hints = listOf("В этой фразе 2 слова.", "Первое слово 'козлёночком'.", "Второе слово 'станешь'."),
                imageId = "14236656/2hvrnuk90bibws5jzakf",
            ),
            QuizItem(
                number = 15,
                question = "Из чего солдат варил кашу?",
                answers = listOf("топор", "из топора", "топора"),
                hints = listOf("Им можно рубить дрова."),
                imageId = "14236656/upcqw3qf88wd5yba4b1g",
            ),
            //endregion

            //region Level 3
            QuizItem(
                number = 16,
                question = "Как называется сказка о мальчике грязнуле?",
                answers = listOf("мойдодыр"),
                hints = listOf(
                    "Начальный слог этого слова - 'МОЙ'.",
                    "Последний слог этого слова - 'ДЫР'."
                ),
                imageId = "965417/0f8yay4wmwknzzzunsop",
            ),
            QuizItem(
                number = 17,
                question = "От кого убегала вся посуда, мебель и вещи?",
                answers = listOf("Федора", "федора", "федоры", "от федоры"),
                hints = listOf("Это бабушка.", "Если бы это был мужчина, то его звали бы Фёдор."),
                imageId = "13200873/q2nwm8qflinnba3z77g3",
            ),
            QuizItem(
                number = 18,
                question = "Продолжи фразу: Ясни ясни на небе звёзды, мёрзни мёрзни...",
                answers = listOf("волчий хвост"),
                hints = listOf("В этой фразе два слова.", "Первое слово 'волчий'.", "Второе слово 'хвост'."),
                imageId = "1652229/9fc18kkise7sk9cekd3y",
            ),
            QuizItem(
                number = 19,
                question = "Кто был хозяином кота в сапогах?",
                answers = listOf("маркиз Карабас", "маркиз карабас"),
                hints = listOf("В этой фразе 2 слова.", "Первое слово 'маркиз'.", "Второе слово 'карабас'."),
                imageId = "1656841/p8zbs4lsl6xw350pdduj",
            ),
            QuizItem(
                number = 20,
                question = "Кого удалось спасти Дюймовочке?",
                answers = listOf("ласточка", "ласточку"),
                hints = listOf("Это птица.", "Начальный слог этого слова 'ЛАС'", "Последний слог этого слова 'КА'"),
                imageId = "1656841/la78y7i5awruid1x9xd2",
            ),
            QuizItem(
                number = 21,
                question = "Как звали девочку с длинными волосами, которая была заперта в башне?",
                answers = listOf("Рапунцель", "рапунцель"),
                hints = listOf(
                    "Начальный слог этого слова - 'РА'.",
                    "Средний слог этого слова - 'ПУН'.",
                    "Последний слог этого слова - 'ЦЕЛЬ'."
                ),
                imageId = "14236656/j0kix1bvjqe3w2iu3aw6",
            ),
            QuizItem(
                number = 22,
                question = "В кого превратился гадкий утенок?",
                answers = listOf("лебедь", "лебедя", "в лебедя"),
                hints = listOf("Это птица белого цвета.", "У этой птицы длинная шея."),
                imageId = "14236656/ds99t783v5fzk3zo8hdd",
            ),
            QuizItem(
                number = 23,
                question = "Где живет Карлсон?",
                answers = listOf("на крыше", "крыша"),
                hints = listOf("Это самая верхняя часть дома.", "Она защищает дом от дождя."),
                imageId = "1521359/sv6lz4o9qkt91gr4zgzy",
            ),
            QuizItem(
                number = 24,
                question = "Как звали деревянного мальчика, которому папа Карло купил азбуку?",
                answers = listOf("буратино"),
                hints = listOf("У него очень длинный нос.", "Он сделан из полена."),
                imageId = "1656841/6gc8f60kp0l1e72lqb9w",
            ),
            QuizItem(
                number = 25,
                question = "Кто помог зайцу выгнать лису в сказке заюшкина избушка?",
                answers = listOf("петух"),
                hints = listOf("Он живёт с курами.", "У него есть гребень и клюв.", "Он громко кукарекает."),
                imageId = "14236656/n1bpvj2a5bcl20jzwrgd",
            ),
        )
}