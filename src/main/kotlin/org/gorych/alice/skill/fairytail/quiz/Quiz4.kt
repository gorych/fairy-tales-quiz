package org.gorych.alice.skill.fairytail.quiz

import org.gorych.alice.skill.core.quiz.Quiz
import org.gorych.alice.skill.core.quiz.QuizItem

class Quiz4 : Quiz() {

    override val items: List<QuizItem>
        get() = listOf(
            QuizItem(
                number = 1,
                question = "Из чего был сделан Буратино?",
                answers = listOf("полено", "дерево", "из полена", "из дерева"),
                hints = listOf(
                    "Это отпиленная часть дерева.",
                    "Начальный слог этого слова - 'ПО'.",
                    "Последний слог этого слова - 'НО'.",
                )
            ),
            QuizItem(
                number = 2,
                question = "На ком Дюймовочка улетела в теплые края?",
                answers = listOf("ласточка", "на ласточке"),
                hints = listOf(
                    "Это птица, которую девочка спасла зимой.",
                    "У неё длинные крылья и хвост."
                ),
                imageId = "1656841/la78y7i5awruid1x9xd2",
            ),
            QuizItem(
                number = 3,
                question = "После появления какого героя теремок развалился?",
                answers = listOf("медведь", "медведя", "после медведя"),
                hints = listOf(
                    "Он косолапый.",
                    "Он собирал шишки."
                )
            ),
            QuizItem(
                number = 4,
                question = "Как звали девочку, которая пошла в лес и набрела на домик трех медведей?",
                answers = listOf("Маша", "маша", "машенька", "Машенька"),
                hints = listOf(
                    "Она съела кашу из самой маленькой чашки.",
                    "Она уснула в кроватке Мишутки."
                ),
                imageId = "14236656/q37at1n5a6megrp0xgyt",
            ),
            QuizItem(
                number = 5,
                question = "Кто был лучшим другом Крокодила Гены?",
                answers = listOf("Чебурашка", "чебурашка"),
                hints = listOf(
                    "У него большие уши.",
                    "Он жил в телефонной будке."
                )
            ),
            QuizItem(
                number = 6,
                question = "Из какого материала был сделан стойкий солдатик?",
                answers = listOf("олово", "оловянный", "оловяный"),
                hints = listOf(
                    "Это мягкий металл.",
                    "Название этого металла скрыто в имени героя.",
                )
            ),
            QuizItem(
                number = 7,
                question = "Как звали девочку, которая отправилась к бабушке через лес с корзинкой?",
                answers = listOf("красная шапочка"),
                hints = listOf(
                    "Она носила красный головной убор.",
                    "По пути она встретила волка.",
                )
            ),
            QuizItem(
                number = 8,
                question = "Кого вылепил старик со старухой из снега, а она потом растаяла?",
                answers = listOf("Снегурочка", "снегурочка", "снегурочку"),
                hints = listOf(
                    "Она прыгнула через костер.",
                    "Это внучка Деда Мороза."
                )
            ),
            QuizItem(
                number = 9,
                question = "Как звали девочку с голубыми волосами из сказки про Буратино?",
                answers = listOf("Мальвина", "мальвина"),
                hints = listOf(
                    "Она учила Буратино писать и считать.",
                    "Её имя начинается на 'МАЛЬ'."
                )
            ),
            //region Level 2
            QuizItem(
                number = 10,
                question = "Кто тянул репку последним?",
                answers = listOf("мышка", "мышь"),
                hints = listOf(
                    "Она маленькая и серая.",
                    "Она живёт в норке."
                ),
                imageId = "213044/fn6tcye576l04syaqxi0",
            ),
            QuizItem(
                number = 11,
                question = "Какое яичко снесла курочка Ряба в конце сказки?",
                answers = listOf("простое", "простое яичко"),
                hints = listOf(
                    "Оно не золотое.",
                    "Это обычное яйцо.",
                ),
                imageId = "14236656/v3vzwbrs29fmdp2ckifm",
            ),
            QuizItem(
                number = 12,
                question = "Кто со слезами просил прислать калоши?",
                answers = listOf("крокодил", "кракадил", "кракодил"),
                hints = listOf(
                    "У него большие зубы.",
                    "Он зеленый и умеет плавать."
                )
            ),
            QuizItem(
                number = 13,
                question = "Кто спас козлят от волка?",
                answers = listOf("коза", "мама-коза", "мама"),
                hints = listOf(
                    "У неё есть рога и копыта.",
                    "Начальный слог этого слова - 'КО'.",
                    "Последний слог этого слова - 'ЗА'.",
                )
            ),
            QuizItem(
                number = 14,
                question = "Как звали девочку, которая жила у семи гномов?",
                answers = listOf("белоснежка", "Белоснежка"),
                hints = listOf(
                    "У неё кожа белая, как снег.",
                    "Она съела отравленное яблоко.",
                )
            ),
            QuizItem(
                number = 15,
                question = "Кто заставил мальчика-грязнулю умыться?",
                answers = listOf("Мойдодыр", "мойдодыр"),
                hints = listOf(
                    "Он начальник умывальников.",
                    "Он сердито топнул ногой."
                ),
                imageId = "965417/0f8yay4wmwknzzzunsop",
            ),
            QuizItem(
                number = 16,
                question = "Кого колобок встретил первым?",
                answers = listOf("заяц", "зайца", "зайку"),
                hints = listOf(
                    "У него длинные уши.",
                    "Он любит морковку."
                ),
                imageId = "213044/1gfhobbwnqii3em6l40c",
            ),
            QuizItem(
                number = 17,
                question = "Кто спас Красную Шапочку и бабушку?",
                answers = listOf("дровосеки", "охотники", "дровосек", "охотник"),
                hints = listOf(
                    "Они работают в лесу.",
                    "У них есть топоры."
                ),
                imageId = "14236656/anv5sa3jbtv43budzl3e",
            ),
            QuizItem(
                number = 18,
                question = "Кто помог Золушке попасть на бал?",
                answers = listOf("фея", "крёстная", "крестная"),
                hints = listOf(
                    "Она добрая волшебница.",
                    "У неё есть волшебная палочка."
                )
            ),
            //endregion

            //region Level 3
            QuizItem(
                number = 19,
                question = "Какие цветы выращивали Кай и Герда?",
                answers = listOf("розы", "роза"),
                hints = listOf(
                    "Они красивые и колючие.",
                    "Они были в ящике на окне."
                )
            ),
            QuizItem(
                number = 20,
                question = "Кто похитил Кая?",
                answers = listOf("снежная королева", "Снежная королева"),
                hints = listOf(
                    "Она живёт во дворце изо льда.",
                    "Она поцеловала Кая."
                )
            ),
            QuizItem(
                number = 21,
                question = "Как звали друга Карлсона?",
                answers = listOf("малыш", "Малыш"),
                hints = listOf(
                    "Он маленький мальчик.",
                    "Он живёт в обычной квартире."
                ),
                imageId = "1521359/sv6lz4o9qkt91gr4zgzy",
            ),
            QuizItem(
                number = 22,
                question = "Где жил Чебурашка?",
                answers = listOf("телефонная будка", "в телефонной будке", "будка", "в будке"),
                hints = listOf(
                    "Там раньше звонили по телефону.",
                    "Это небольшой домик."
                )
            ),
            QuizItem(
                number = 23,
                question = "Из чего построил дом поросенок Ниф-Ниф?",
                answers = listOf("солома", "соломы", "из соломы"),
                hints = listOf(
                    "Это желтая сухая трава.",
                    "Из нее дом получился непрочным."
                ),
                imageId = "1030494/eflhlsj7loxcxd8vsv65",
            ),
            QuizItem(
                number = 24,
                question = "Что продал папа Карло, чтобы купить азбуку для Буратино?",
                answers = listOf("куртку", "куртка", "свою куртку"),
                hints = listOf(
                    "Это предмет одежды.",
                )
            ),
            QuizItem(
                number = 25,
                question = "Кто был врагом Буратино?",
                answers = listOf("карабас-барабас", "карабас барабас", "карабас", "барабас"),
                hints = listOf(
                    "У него длинная борода.",
                    "Он директор кукольного театра."
                )
            ),

            //endregion
        )
}