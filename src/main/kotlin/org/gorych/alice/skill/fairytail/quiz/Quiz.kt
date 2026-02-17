package org.gorych.alice.skill.fairytail.quiz

class Quiz {
    companion object {
        private val items: List<Pair<String, List<String>>> = listOf(
            Pair("Кто посадил репку?", listOf("дедка", "деда", "дед")),
            Pair("Кто съел бабушку красной шапочки?", listOf("волк")),
            Pair("Кто испёк колобка?", listOf("баба", "бабка", "бабушка")),
            Pair("Как звали курочку, которая снесла золотое яичко?", listOf("ряба")),
            Pair("Как звали собаку в сказке репка?", listOf("жучка")),
        )

        fun question(number: Int): String {
            require(number > 0) { "Question number must be greater than zero" }
            return items[number - 1].first
        }

        fun getAnswerFor(questionNumber: Int): List<String> {
            require(questionNumber > 0) { "Question number must be greater than zero" }
            return items[questionNumber - 1].second
        }

        fun countOfQuestions(): Int = items.size
    }
}