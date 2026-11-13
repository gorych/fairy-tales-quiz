package org.gorych.alice.skill.core.quiz

import org.gorych.alice.skill.core.mode.GameMode

object ModeProfiles {

    fun of(mode: GameMode): ModeProfile = when (mode) {
        GameMode.QUESTION_ANSWER -> questionAnswerModeProfile
        GameMode.GUESS_SOUND -> soundModeProfile
        GameMode.TRUE_OR_FALSE -> truthOrLieModeProfile
    }
}
