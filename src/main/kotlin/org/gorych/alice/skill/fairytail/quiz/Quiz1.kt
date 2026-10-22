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
                imageId = "213044/3x9k4h7rbk510tcq818u",
            ),
            QuizItem(
                number = 2,
                question = "Кто съел бабушку красной шапочки?",
                answers = listOf("волк"),
                hints = listOf("Он живёт в лесу.", "Его кума - лиса.", "Он серого цвета."),
                imageId = "965417/wbje5qkvh6ofe4hy83xu",
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
                imageId = "997614/tt4lyy7esm6hquviqemc",
            ),
            QuizItem(
                number = 4,
                question = "Кто съел колобка?",
                answers = listOf("лиса"),
                hints = listOf("Её кум - волк.", "Она рыжая и живёт в лесу."),
                imageId = "997614/hhilgl049pn6oxwtubyn",
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
                imageId = "965417/06ppvroy2gmlwpvx66e8",
            ),
            QuizItem(
                number = 6,
                question = "Сколько гномов было у белоснежки?",
                answers = listOf("7", "семь"),
                hints = listOf(
                    "Их было столько же, сколько козлят в сказке про волка.",
                    "Это на один меньше, чем 8."
                ),
                imageId = "1540737/ydrzzh0rhxaftotw4t2k",
            ),
            QuizItem(
                number = 7,
                question = "Ниф-ниф, наф-наф, а кто третий?",
                answers = listOf("Нуф-нуф", "нуф-нуф", "нуф нуф", "нуфнуф"),
                hints = listOf(
                    "Первая часть ответа содержит слово 'НУФ'.",
                    "Вторая часть полностью совпадает с первой."
                ),
                imageId = "1540737/pdxrfdyvrmza2hfjfl0q",
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
                imageId = "1540737/ip77uwwexw52ak5a6a9z",
            ),
            QuizItem(
                number = 9,
                question = "Продолжи фразу: У меня зазвонил телефон, кто говорит?..",
                answers = listOf("слон"),
                hints = listOf("У него есть хобот.", "Это самое крупное животное на земле."),
                imageId = "1652229/ef9v88l00kyb51pbknov",
            ),
            QuizItem(
                number = 10,
                question = "В какой сказке встречается фраза: 'Кто хлебал в моей чашке'?",
                answers = listOf("три медведя", "3 медведя"),
                hints = listOf("В этой фразе 2 слова.", "Первое слово 'три'.", "Второе слово 'медведя'."),
                imageId = "1521359/tduhbog6egpl7ngrmdtz",
            ),
            QuizItem(
                number = 11,
                question = "Как звали девочку, которая произносила фразу: 'Не садись на пенёк, не ешь пирожок!'?",
                answers = listOf("Маша", "маша", "машенька"),
                hints = listOf(
                    "Это имя отличается только одной буквой от слова 'саша'.",
                    "Это слово начинается на букву 'М'."
                ),
                imageId = "1652229/20smb4ujj3zpy37wtnhd",
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
                imageId = "1652229/b0tr4ri95k06hh1jrcu4",
            ),
            QuizItem(
                number = 13,
                question = "Как называется сказка, в которой гуси украли братика у девочки?",
                answers = listOf("гуси-лебеди", "гуси лебеди"),
                hints = listOf("В этой фразе 2 слова.", "Первое слово 'гуси'.", "Второе слово 'лебеди'."),
                imageId = "1652229/dll6xfhg1eqcd2mxwuw4",
            ),
            QuizItem(
                number = 14,
                question = "Продолжи фразу Алёнушки: Не пей, братец, ...",
                answers = listOf("козлёночком станешь", "козленочком станешь"),
                hints = listOf("В этой фразе 2 слова.", "Первое слово 'козлёночком'.", "Второе слово 'станешь'."),
                imageId = "213044/zczvownclh6s1wrl3gc5",
            ),
            QuizItem(
                number = 15,
                question = "Из чего солдат варил кашу?",
                answers = listOf("топор", "из топора", "топора"),
                hints = listOf("Им можно рубить дрова."),
                imageId = "1652229/3f1r9k26xz6muwk9n81p",
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
                imageId = "1521359/hnfuzdc86t9rpzkvo400",
            ),
            QuizItem(
                number = 17,
                question = "От кого убегала вся посуда, мебель и вещи?",
                answers = listOf("Федора", "федора", "федоры", "от федоры"),
                hints = listOf("Это бабушка.", "Если бы это был мужчина, то его звали бы Фёдор."),
                imageId = "1030494/gqkyvur56nscnr9z983v",
            ),
            QuizItem(
                number = 18,
                question = "Продолжи фразу: Ясни ясни на небе звёзды, мёрзни мёрзни...",
                answers = listOf("волчий хвост"),
                hints = listOf("В этой фразе два слова.", "Первое слово 'волчий'.", "Второе слово 'хвост'."),
                imageId = "965417/ztbepukko9g18uxdnsaf",
            ),
            QuizItem(
                number = 19,
                question = "Кто был хозяином кота в сапогах?",
                answers = listOf("маркиз Карабас", "маркиз карабас"),
                hints = listOf("В этой фразе 2 слова.", "Первое слово 'маркиз'.", "Второе слово 'карабас'."),
                imageId = "1030494/0cv3x2wdpj8ibgyz7scm",
            ),
            QuizItem(
                number = 20,
                question = "Кого удалось спасти Дюймовочке?",
                answers = listOf("ласточка", "ласточку"),
                hints = listOf("Это птица.", "Начальный слог этого слова 'ЛАС'", "Последний слог этого слова 'КА'"),
                imageId = "997614/td86oatauljcx2rgiqgr",
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
                imageId = "997614/du4hbcj95nncs12i6ct9",
            ),
            QuizItem(
                number = 22,
                question = "В кого превратился гадкий утенок?",
                answers = listOf("лебедь", "лебедя", "в лебедя"),
                hints = listOf("Это птица белого цвета.", "У этой птицы длинная шея."),
                imageId = "14236656/0zjz3zbyb9t6wq9zuybl",
            ),
            QuizItem(
                number = 23,
                question = "Где живет Карлсон?",
                answers = listOf("на крыше", "крыша"),
                hints = listOf("Это самая верхняя часть дома.", "Она защищает дом от дождя."),
                imageId = "13200873/90j73f1q0h24orxdar10",
            ),
            QuizItem(
                number = 24,
                question = "Как звали деревянного мальчика, которому папа Карло купил азбуку?",
                answers = listOf("буратино"),
                hints = listOf("У него очень длинный нос.", "Он сделан из полена."),
                imageId = "1540737/nv8zxflipen75hzdag7j",
            ),
            QuizItem(
                number = 25,
                question = "Кто помог зайцу выгнать лису в сказке заюшкина избушка?",
                answers = listOf("петух"),
                hints = listOf("Он живёт с курами.", "У него есть гребень и клюв.", "Он громко кукарекает."),
                imageId = "1540737/q1pogmiiv0c9jy74jo75",
            ),
        )
}