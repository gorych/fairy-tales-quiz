package org.gorych.alice.skill.fairytail.command

import org.gorych.alice.skill.core.api.Button
import org.gorych.alice.skill.core.api.RequestObject
import org.gorych.alice.skill.core.api.ResponseObject
import org.gorych.alice.skill.core.api.SessionState
import org.gorych.alice.skill.core.mode.GameMode

object GameModeMenu {

    fun of(
        phrase: String,
        modes: Collection<GameMode>,
        requestObject: RequestObject,
        tts: String = phrase,
    ): ResponseObject = ResponseObject.of(
        text = phrase,
        tts = tts,
        buttons = buttons(modes),
        sessionState = sessionState(requestObject),
        endSession = false,
    )

    fun buttons(modes: Collection<GameMode>): List<Button> =
        modes.map { Button.gameMode(it) } + Button.whatCanYouDo()

    fun sessionState(requestObject: RequestObject): SessionState =
        (requestObject.state?.session ?: SessionState()).copy(
            transitionCommands = setOf(GameModeSelectionCommand.name(), ModesHelpCommand.name())
        )
}
