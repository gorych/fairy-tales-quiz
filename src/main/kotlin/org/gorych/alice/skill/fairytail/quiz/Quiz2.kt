package org.gorych.alice.skill.fairytail.quiz

import org.gorych.alice.skill.core.quiz.Quiz
import org.gorych.alice.skill.core.quiz.QuizItem

class Quiz2 : Quiz() {

    override val items: List<QuizItem>
        get() = listOf(
            QuizItem(
                number = 1,
                question = "Кого встретил колобок последним?",
                answers = listOf("лиса", "лису", "лисичку", "лисичка"),
                hints = listOf("Она рыжая и хитрая.", "Она его съела."),
                imageId = "213044/1gfhobbwnqii3em6l40c",
            ),
            QuizItem(
                number = 2,
                question = "Кого позвала Жучка, чтобы вытянуть репку?",
                answers = listOf("кошка", "кошку"),
                hints = listOf("Она говорит 'мяу'.", "Она любит ловить мышей."),
                imageId = "213044/fn6tcye576l04syaqxi0",
            ),
            QuizItem(
                number = 3,
                question = "Кто нёс Машу в коробе с пирожками?",
                answers = listOf("медведь", "мишка", "миша"),
                hints = listOf(
                    "Он любит мёд.",
                    "Он спит всю зиму.",
                ),
                imageId = "1533899/zsuxp7afjpdk7n2bscby",
            ),
            QuizItem(
                number = 4,
                question = "Кто первым позвонил и попросил шоколада?",
                answers = listOf("слон"),
                hints = listOf(
                    "У него огромные уши и хобот.",
                    "Он прислал ответ: пудов этак пять или шесть."
                ),
                imageId = "1521359/52pvlwzc77iv0zdyzuzo",
            ),

            QuizItem(
                number = 5,
                question = "На чем Емеля ехал к царю во дворец?",
                answers = listOf("печь", "печи", "на печи", "печка", "на печке"),
                hints = listOf(
                    "В ней пекут пирожки.",
                    "Её топят дровами."
                ),
                imageId = "1521359/szatcslg2vtkj84vme19",
            ),
            QuizItem(
                number = 6,
                question = "Что первым убежало от грязнули в сказке Мойдодыр?",
                answers = listOf("одеяло"),
                hints = listOf(
                    "Им укрываются.",
                    "Оно улетело вместе с простыней."
                ),
                imageId = "965417/0f8yay4wmwknzzzunsop",
            ),
            QuizItem(
                number = 7,

                question = "Что убежало от бабушки Федоры?",
                answers = listOf("посуда"),
                hints = listOf(
                    "Это чашки, ложки и кастрюли одним словом.",
                    "Это слово начинается на букву 'П'.",
                    "Последний слог этого слова - 'ДА'.",
                ),
                imageId = "13200873/q2nwm8qflinnba3z77g3",
            ),
            QuizItem(
                number = 8,
                question = "Что нашла Муха-Цокотуха, когда по полю пошла?",
                answers = listOf("денежка", "денежку", "деньги", "денюжка"),
                hints = listOf(
                    "За это можно что-то купить.",
                    "Они лежат в кошельке.",
                ),
                imageId = "997614/sl4m93g4iexy37nbb2m2",
            ),
            //region Level 2
            QuizItem(
                number = 9,
                question = "Из чего построил свой самый прочный дом поросёнок Наф-Наф?",
                answers = listOf("кирпич", "кирпичи", "из кирпича", "из кирпичей", "с кирпича"),
                hints = listOf(
                    "Он тяжелый, красный и крепкий.",
                    "Из него строят настоящие дома.",
                ),
                imageId = "1030494/eflhlsj7loxcxd8vsv65",
            ),
            QuizItem(
                number = 10,
                question = "Какая избушка была у лисы в сказке: ледяная или лубяная?",
                answers = listOf("ледяная"),
                hints = listOf(
                    "Она растаяла весной.",
                    "Это застывшая вода.",
                ),
                imageId = "1533899/6s02hecw6zy23e0x171h",
            ),
            QuizItem(
                number = 11,
                question = "Что потеряла Золушка на балу, когда убегала?",
                answers = listOf("туфелька", "туфельку", "башмачок"),
                hints = listOf(
                    "Это её обувь.",
                    "Она была из хрусталя.",
                ),
                imageId = "1533899/68ypsup8jxv8uvwmkx8g",
            ),
            QuizItem(
                number = 12,
                question = "В какую страну отправился Айболит, чтобы лечить зверей?",
                answers = listOf("Африка", "африка", "африку", "в африку"),
                hints = listOf(
                    "Это слово начинается на букву 'А'.",
                    "Там очень жарко и течет Лимпопо.",
                    "Там живут жирафы."
                ),
                imageId = "965417/8yivnxo5vyr86lszrj03",
            ),
            QuizItem(
                number = 13,
                question = "Сколько гномов приютили Белоснежку?",
                answers = listOf("7", "семь"),
                hints = listOf(
                    "Их столько же, как дней в неделе.",
                    "Их было столько же, сколько козлят в сказке про волка.",
                ),
                imageId = "213044/gs79m6ezzdja8nxnhdhz",
            ),
            QuizItem(
                number = 14,
                question = "В какого зверя превратился Иванушка, когда попил из копытца?",
                answers = listOf(
                    "козлёнок",
                    "козленок",
                    "в козлёнка",
                    "в козленка",
                    "козлёночка",
                    "козленочка",
                    "в козлёночка",
                    "в козленочка",
                ),
                hints = listOf(
                    "У него есть рога.",
                    "Это ребёнок козы."
                ),
                imageId = "14236656/2hvrnuk90bibws5jzakf",
            ),
            QuizItem(
                number = 15,
                question = "Что больше всего на свете любил есть Карлсон?",
                answers = listOf("варенье"),
                hints = listOf(
                    "Оно сладкое и в банке.",
                    "Его варят из ягод."
                ),
                imageId = "1656841/x8wa5s3e1y816x87r5b3",
            ),
            QuizItem(
                number = 16,
                question = "Что было очень длинным у принцессы Рапунцель?",
                answers = listOf("волосы", "коса"),
                hints = listOf(
                    "Они есть у всех на голове.",
                    "Их можно стричь."
                ),
                imageId = "14236656/j0kix1bvjqe3w2iu3aw6",
            ),
            //endregion

            //region Level 3
            QuizItem(
                number = 17,
                question = "В кого превратилась Василиса Прекрасная в сказке Царевна-лягушка?",
                answers = listOf("лягушка", "в лягушку", "лягушку"),
                hints = listOf(
                    "Она зелёная и живет в болоте.",
                    "Иван-царевич нашёл её со стрелой."
                ),
                imageId = "1656841/iohx8ayk5wvkqiebcrgu",
            ),
            QuizItem(
                number = 18,
                question = "В какого зверя превратился Людоед, перед тем как Кот его съел?",
                answers = listOf("лев", "льва", "во льва", "в льва"),
                hints = listOf(
                    "Это царь зверей.",
                    "У него есть грива.",
                ),
                imageId = "1656841/p8zbs4lsl6xw350pdduj",
            ),

            QuizItem(
                number = 19,
                question = "Кто хотел жениться на Дюймовочке и заставить её считать зерна?",
                answers = listOf("крот"),
                hints = listOf(
                    "Он живёт под землёй.",
                    "Он плохо видит.",
                ),
                imageId = "1656841/la78y7i5awruid1x9xd2",
            ),
            QuizItem(
                number = 20,
                question = "Во что превратилась карета Золушки после полуночи?",
                answers = listOf("тыква", "в тыкву", "тыкву"),
                hints = listOf(
                    "Это большой и оранжевый овощ.",
                    "Это слово начинается на букву 'Т'.",
                    "Это слово заканчивается на 'ВА'.",
                ),
                imageId = "937455/fvuah8seqdy9z21qcg34",
            ),
            QuizItem(
                number = 21,
                question = "Как звали черепаху из сказки Буратино?",
                answers = listOf(
                    "Тортила",
                    "тортила",
                    "тартила",
                    "черепаха тортила",
                    "черепаха тортилла",
                    "черепаха тартила",
                    "черепаха тартилла",
                    "тартилла",
                    "тортилла",
                    "Тортилла",
                ),
                hints = listOf(
                    "Начальный слог этого слова - 'ТОР'.",
                    "Средний слог этого слова - 'ТИ'.",
                    "Последний слог этого слова - 'ЛА'."
                ),
                imageId = "1656841/6gc8f60kp0l1e72lqb9w",
            ),
            QuizItem(
                number = 22,
                question = "В кого превратился гадкий утёнок?",
                answers = listOf("лебедь", "лебедя", "в лебедя"),
                hints = listOf("Это птица белого цвета.", "У этой птицы длинная шея."),
                imageId = "14236656/ds99t783v5fzk3zo8hdd",
            ),
            QuizItem(
                number = 23,
                question = "Как зовут бабушку, которая живёт в избушке на курьих ножках?",
                answers = listOf("баба-яга", "баба яга", "яга"),
                hints = listOf(
                    "Она летает на метле.",
                    "Кощей Бессмертный её друг.",
                ),
                imageId = "1533899/zugd8ohxn5wftco7ema8",
            ),
            QuizItem(
                number = 24,
                question = "Какое слово пытался сложить из льдинок Кай в замке Снежной королевы?",
                answers = listOf("вечность"),
                hints = listOf(
                    "Начальный слог этого слова - 'ВЕЧ'.",
                    "Последний слог этого слова - 'НОСТЬ'."
                ),
                imageId = "965417/p9avct598bz0iafjw4au",
            ),
            QuizItem(
                number = 25,
                question = "В какой сказке мачеха заставляла девочку перебирать мешки с крупой и шить платья для сестёр?",
                answers = listOf("золушка"),
                hints = listOf(
                    "Попасть на бал ей помогла добрая Фея.",
                    "Первый слог этого слова 'ЗО'.",
                    "Второй слог этого слова 'ЛУШ'.",
                    "Третий слог этого слова 'КА'.",
                ),
                imageId = "1533899/68ypsup8jxv8uvwmkx8g",
            ),
        )
}