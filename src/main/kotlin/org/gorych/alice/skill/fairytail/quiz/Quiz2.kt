package org.gorych.alice.skill.fairytail.quiz

import org.gorych.alice.skill.core.quiz.Quiz
import org.gorych.alice.skill.core.quiz.QuizItem
import org.gorych.alice.skill.core.quiz.QuizItem.Level.*

class Quiz2 : Quiz() {

    override val items: List<QuizItem>
        get() = listOf(
            QuizItem(
                number = 1,
                level = EASY,
                question = "Кого встретил колобок последним?",
                answers = listOf("лиса", "лису", "лисичку", "лисичка"),
                hints = listOf("Она рыжая и хитрая.", "Она его съела.")
            ),
            QuizItem(
                number = 2,
                level = EASY,
                question = "Кого позвала Жучка, чтобы вытянуть репку?",
                answers = listOf("кошка", "кошку"),
                hints = listOf("Она говорит 'мяу'.", "Она любит ловить мышей.")
            ),
            QuizItem(
                number = 3,
                level = EASY,
                question = "Кто нёс Машу в коробе с пирожками?",
                answers = listOf("медведь", "мишка", "миша"),
                hints = listOf(
                    "Он любит мёд.",
                    "Он спит всю зиму.",
                )
            ),
            QuizItem(
                number = 4,
                level = EASY,
                question = "Кто первым позвонил и попросил шоколада?",
                answers = listOf("слон"),
                hints = listOf(
                    "У него огромные уши и хобот.",
                    "Он прислал ответ: пудов этак пять или шесть."
                )
            ),

            QuizItem(
                number = 5,
                level = EASY,
                question = "На чем Емеля ехал к царю во дворец?",
                answers = listOf("печь", "печи", "на печи", "печка", "на печке"),
                hints = listOf(
                    "В ней пекут пирожки.",
                    "Её топят дровами."
                )
            ),
            QuizItem(
                number = 6,
                level = EASY,
                question = "Что первым убежало от грязнули в сказке Мойдодыр?",
                answers = listOf("одеяло"),
                hints = listOf(
                    "Им укрываются.",
                    "Оно улетело вместе с простыней."
                )
            ),
            QuizItem(
                number = 7,
                level = MEDIUM,
                question = "Что убежало от бабушки Федоры?",
                answers = listOf("посуда"),
                hints = listOf(
                    "Это чашки, ложки и кастрюли одним словом.",
                    "Это слово начинается на букву 'П'.",
                    "Последний слог этого слова - 'ДА'.",
                )
            ),
            QuizItem(
                number = 8,
                level = MEDIUM,
                question = "Что нашла Муха-Цокотуха, когда по полю пошла?",
                answers = listOf("денежка", "денежку", "деньги", "денюжка"),
                hints = listOf(
                    "За это можно что-то купить.",
                    "Они лежат в кошельке.",
                )
            ),
            //region Level 2
            QuizItem(
                number = 9,
                level = MEDIUM,
                question = "Из чего построил свой самый прочный дом поросёнок Наф-Наф?",
                answers = listOf("кирпич", "кирпичи", "из кирпича", "из кирпичей", "с кирпича"),
                hints = listOf(
                    "Он тяжелый, красный и крепкий.",
                    "Из него строят настоящие дома.",
                )
            ),
            QuizItem(
                number = 10,
                level = MEDIUM,
                question = "Какая избушка была у лисы в сказке: ледяная или лубяная?",
                answers = listOf("ледяная"),
                hints = listOf(
                    "Она растаяла весной.",
                    "Это застывшая вода.",
                )
            ),
            QuizItem(
                number = 11,
                level = MEDIUM,
                question = "Что потеряла Золушка на балу, когда убегала?",
                answers = listOf("туфелька", "туфельку", "башмачок"),
                hints = listOf(
                    "Это её обувь.",
                    "Она была из хрусталя.",
                )
            ),
            QuizItem(
                number = 12,
                level = MEDIUM,
                question = "В какую страну отправился Айболит, чтобы лечить зверей?",
                answers = listOf("Африка", "африка", "африку", "в африку"),
                hints = listOf(
                    "Это слово начинается на букву 'А'.",
                    "Там очень жарко и течет Лимпопо.",
                    "Там живут жирафы."
                )
            ),
            QuizItem(
                number = 13,
                level = MEDIUM,
                question = "Сколько гномов приютили Белоснежку?",
                answers = listOf("7", "семь"),
                hints = listOf(
                    "Их столько же, как дней в неделе.",
                    "Их было столько же, сколько козлят в сказке про волка.",
                )
            ),
            QuizItem(
                number = 14,
                level = HARD,
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
                )
            ),
            QuizItem(
                number = 15,
                level = HARD,
                question = "Что больше всего на свете любил есть Карлсон?",
                answers = listOf("варенье"),
                hints = listOf(
                    "Оно сладкое и в банке.",
                    "Его варят из ягод."
                )
            ),
            QuizItem(
                number = 16,
                level = HARD,
                question = "Что было очень длинным у принцессы Рапунцель?",
                answers = listOf("волосы", "коса"),
                hints = listOf(
                    "Они есть у всех на голове.",
                    "Их можно стричь."
                )
            ),
            //endregion

            //region Level 3
            QuizItem(
                number = 17,
                level = EASY,
                question = "В кого превратилась Василиса Прекрасная в сказке Царевна-лягушка?",
                answers = listOf("лягушка", "в лягушку", "лягушку"),
                hints = listOf(
                    "Она зелёная и живет в болоте.",
                    "Иван-царевич нашёл её со стрелой."
                )
            ),
            QuizItem(
                number = 18,
                level = EASY,
                question = "В какого зверя превратился Людоед, перед тем как Кот его съел?",
                answers = listOf("лев", "льва", "во льва", "в льва"),
                hints = listOf(
                    "Это царь зверей.",
                    "У него есть грива.",
                )
            ),

            QuizItem(
                number = 19,
                level = HARD,
                question = "Кто хотел жениться на Дюймовочке и заставить её считать зерна?",
                answers = listOf("крот"),
                hints = listOf(
                    "Он живёт под землёй.",
                    "Он плохо видит.",
                )
            ),
            QuizItem(
                number = 20,
                level = HARD,
                question = "Во что превратилась карета Золушки после полуночи?",
                answers = listOf("тыква", "в тыкву", "тыкву"),
                hints = listOf(
                    "Это большой и оранжевый овощ.",
                    "Это слово начинается на букву 'Т'.",
                    "Это слово заканчивается на 'ВА'.",
                )
            ),
            QuizItem(
                number = 21,
                level = HARD,
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
                )
            ),
            QuizItem(
                number = 22,
                level = HARD,
                question = "В кого превратился гадкий утёнок?",
                answers = listOf("лебедь", "лебедя", "в лебедя"),
                hints = listOf("Это птица белого цвета.", "У этой птицы длинная шея.")
            ),
            QuizItem(
                number = 23,
                level = HARD,
                question = "Как зовут бабушку, которая живёт в избушке на курьих ножках?",
                answers = listOf("баба-яга", "баба яга", "яга"),
                hints = listOf(
                    "Она летает на метле.",
                    "Кощей Бессмертный её друг.",
                )
            ),
            QuizItem(
                number = 24,
                level = HARD,
                question = "Какое слово пытался сложить из льдинок Кай в замке Снежной королевы?",
                answers = listOf("вечность"),
                hints = listOf(
                    "Начальный слог этого слова - 'ВЕЧ'.",
                    "Последний слог этого слова - 'НОСТЬ'."
                )
            ),
            QuizItem(
                number = 25,
                level = HARD,
                question = "В какой сказке мачеха заставляла девочку перебирать мешки с крупой и шить платья для сестёр?",
                answers = listOf("золушка"),
                hints = listOf(
                    "Попасть на бал ей помогла добрая Фея.",
                    "Первый слог этого слова 'ЗО'.",
                    "Второй слог этого слова 'ЛУШ'.",
                    "Третий слог этого слова 'КА'.",
                )
            ),
        )
}