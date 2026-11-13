package org.gorych.alice.skill.core.quiz

import org.gorych.alice.skill.core.mode.GameMode

object ModeProfiles {

    fun of(mode: GameMode): ModeProfile = when (mode) {
        GameMode.QUESTION_ANSWER -> questionAnswerModeProfile
        GameMode.GUESS_SOUND, GameMode.TRUE_OR_FALSE ->
            error("Profile of the $mode mode is not implemented yet")
    }
}
