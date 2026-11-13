package org.gorych.alice.skill.core.mode

import org.gorych.alice.skill.core.quiz.question_answer.questionAnswerModeProfile
import org.gorych.alice.skill.core.quiz.sound.soundModeProfile
import org.gorych.alice.skill.core.quiz.truth_or_lie.truthOrLieModeProfile


object ModeProfiles {

    fun of(mode: GameMode): ModeProfile = when (mode) {
        GameMode.QUESTION_ANSWER -> questionAnswerModeProfile
        GameMode.GUESS_SOUND -> soundModeProfile
        GameMode.TRUE_OR_FALSE -> truthOrLieModeProfile
    }
}
