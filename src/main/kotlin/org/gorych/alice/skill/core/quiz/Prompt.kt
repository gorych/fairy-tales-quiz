package org.gorych.alice.skill.core.quiz

import org.gorych.alice.skill.core.api.BigImageCard

data class Prompt(
    val text: String,
    val tts: String,
    val card: BigImageCard? = null,
)
