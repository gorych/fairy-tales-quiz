package org.gorych.alice.skill.fairytail.command

import org.gorych.alice.skill.core.COIN
import org.gorych.alice.skill.core.api.RequestObject
import org.gorych.alice.skill.core.api.ResponseObject
import org.gorych.alice.skill.core.command.Command
import org.gorych.alice.skill.core.quiz.QuizSource

private const val INTRODUCTION_TEXT = "" +
        "Рада слышать тебя снова! Ты в гостях у «Викторины по сказкам». " +
        "Я подготовила для тебя три режима игры: «Вопрос-ответ», «Угадай по звуку» и «Правда или ложь». " +
        "Если хочешь более подробно узнать про каждый из них просто скажи: " +
        "\"Алиса, расскажи про режимы\". " +
        "Какой режим выберешь?"

class IntroductionCommand(private val quizzes: QuizSource) : Command {

    override fun name(): String = IntroductionCommand.name()

    override fun canHandle(requestObject: RequestObject): Boolean {
        return requestObject.isNewSession()
    }

    override fun execute(requestObject: RequestObject): ResponseObject {
        return ResponseObject.of(
            text = INTRODUCTION_TEXT,
            tts = "sil <[500]> $COIN sil <[500]> $INTRODUCTION_TEXT",
            buttons = GameModeMenu.buttons(quizzes.availableModes),
            sessionState = GameModeMenu.sessionState(requestObject),
            endSession = false,
        )
    }

    companion object {
        fun name(): String = IntroductionCommand::class.java.simpleName
    }
}
