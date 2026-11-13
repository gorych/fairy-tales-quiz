package org.gorych.alice.skill.core

const val VICTORY_FANFARE = "<speaker audio=\"alice-sounds-game-win-1.opus\">"
const val COIN = "<speaker audio=\"alice-sounds-game-8-bit-coin-1.opus\">"

private const val SKILL_ID = "8070abca-f39f-40ec-bc59-377070bed8b8"

fun soundTag(soundId: String): String = "<speaker audio=\"dialogs-upload/$SKILL_ID/$soundId.opus\">"
