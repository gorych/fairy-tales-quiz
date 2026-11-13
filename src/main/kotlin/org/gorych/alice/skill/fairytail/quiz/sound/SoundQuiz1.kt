package org.gorych.alice.skill.fairytail.quiz.sound

import org.gorych.alice.skill.core.quiz.sound.SoundQuestion
import org.gorych.alice.skill.core.quiz.sound.SoundQuiz

class SoundQuiz1 : SoundQuiz() {

    override val items: List<SoundQuestion>
        get() = listOf(
            SoundQuestion(
                number = 1,
                soundId = "f2fdb7f5-f211-4bc3-b1e2-4e37bfefb694",
                answers = listOf("репка", "Репка", "сказка репка", "про репку"),
                hints = listOf("Эту сказку тянут всей семьёй, а помогает маленькая мышка."),
            ),
            SoundQuestion(
                number = 2,
                soundId = "a51487db-cc48-42f1-9b4b-95a8a2229dcd",
                answers = listOf("курочка ряба", "Курочка ряба", "курочка-ряба", "ряба"),
                hints = listOf("Золотое яичко сначала не смогли разбить, а потом оно разбилось само."),
            ),
            SoundQuestion(
                number = 3,
                soundId = "85ff67a2-6aa0-414e-a638-d7a1dcce1159",
                answers = listOf("три поросенка", "Три поросенка", "три поросёнка", "поросята"),
                hints = listOf("Герои строят домики, и только один из них оказывается прочным."),
            ),
            SoundQuestion(
                number = 4,
                soundId = "23e3089f-d6e7-4436-b3e0-681889a4bc59",
                answers = listOf("муха цокотуха", "Муха Цокотуха", "муха-цокотуха", "цокотуха"),
                hints = listOf("Героиня покупает самовар и зовёт гостей на именины."),
            ),
            SoundQuestion(
                number = 5,
                soundId = "18c4185b-b887-4227-b916-861c8b0672b4",
                answers = listOf("телефон", "Телефон", "сказка телефон"),
                hints = listOf("Герою звонят звери, и он не может положить трубку."),
            ),
        )
}
