package org.gorych.alice.skill.fairytail.quiz.truth_or_lie

import org.gorych.alice.skill.core.quiz.truth_or_lie.TruthOrLieQuiz
import org.gorych.alice.skill.core.quiz.truth_or_lie.TruthStatement

class TruthOrLieQuiz1 : TruthOrLieQuiz() {

    override val items: List<TruthStatement>
        get() = listOf(
            TruthStatement(
                number = 1,
                statement = "Правда, что репку посадил дед?",
                isTrue = true,
                explanation = "Репку посадил дед, а вытянул её вместе с бабкой, внучкой, Жучкой, кошкой и мышкой.",
                hints = listOf("В этой сказке тянули овощ, который вырос очень большим."),
            ),
            TruthStatement(
                number = 2,
                statement = "Правда, что колобка съел волк?",
                isTrue = false,
                explanation = "Колобка съела лиса - она обхитрила его хитрой песенкой.",
                hints = listOf("Этот зверь рыжий и живёт в лесу."),
            ),
            TruthStatement(
                number = 3,
                statement = "Правда, что курочку, которая снесла золотое яйцо, звали Ряба?",
                isTrue = true,
                explanation = "Курочку звали Ряба.",
                hints = listOf("Её имя звучит как слово, которым называют рябую птицу."),
            ),
            TruthStatement(
                number = 4,
                statement = "Правда, что у Белоснежки было восемь гномов?",
                isTrue = false,
                explanation = "Гномов было семь.",
                hints = listOf("Это число на единицу меньше восьми."),
            ),
            TruthStatement(
                number = 5,
                statement = "Правда, что муха купила на базаре самовар?",
                isTrue = true,
                explanation = "В «Мухе-Цокотухе» муха купила самовар.",
                hints = listOf("Он сам варит воду."),
            ),
            TruthStatement(
                number = 6,
                statement = "Правда, что зверей лечил доктор Айболит?",
                isTrue = true,
                explanation = "Доктор Айболит лечил всех зверей.",
                hints = listOf("Он сидит под деревом и ждёт пациентов."),
            ),
            TruthStatement(
                number = 7,
                statement = "Правда, что от Федоры убежала только посуда?",
                isTrue = false,
                explanation = "От Федоры убежали и посуда, и мебель, и вещи.",
                hints = listOf("Вспомни, что ещё было в её доме."),
            ),
            TruthStatement(
                number = 8,
                statement = "Правда, что солдат варил кашу из топора?",
                isTrue = true,
                explanation = "Солдат сварил кашу из топора.",
                hints = listOf("Этим предметом рубят дрова."),
            ),
            TruthStatement(
                number = 9,
                statement = "Правда, что Красную шапочку съел медведь?",
                isTrue = false,
                explanation = "Красную шапочку съел волк.",
                hints = listOf("Этот зверь живёт в лесу и охотится на бабушку и внучку."),
            ),
            TruthStatement(
                number = 10,
                statement = "Правда, что Карлсон живёт на крыше?",
                isTrue = true,
                explanation = "Карлсон живёт на крыше, в маленьком домике.",
                hints = listOf("Это самая верхняя часть дома."),
            ),
            TruthStatement(
                number = 11,
                statement = "Правда, что Буратино сделали из полена?",
                isTrue = true,
                explanation = "Буратино выстругали из полена.",
                hints = listOf("Папа Карло купил ему азбуку."),
            ),
            TruthStatement(
                number = 12,
                statement = "Правда, что старик из сказки о рыбаке и рыбке забрасывал невод пять раз?",
                isTrue = false,
                explanation = "Невод он забрасывал три раза.",
                hints = listOf("Это число меньше пяти и больше двух."),
            ),
        )
}
