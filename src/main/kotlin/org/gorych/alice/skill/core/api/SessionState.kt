package org.gorych.alice.skill.core.api

data class SessionState(
    val currentQuestion: Int? = null,
    val rightAnswersCount: Int = 0,
    val hintedQuestions: Set<Int> = setOf(),
    val previousHintNumber: Int = 0,
    val transitionCommands: Set<String>? = setOf(),
) {
    constructor(transitionCommands: Set<String>) : this(
        currentQuestion = null,
        rightAnswersCount = 0,
        hintedQuestions = setOf(),
        previousHintNumber = 0,
        transitionCommands = transitionCommands
    )

    constructor(currentQuestion: Int?, transitionCommands: Set<String>) : this(
        currentQuestion = currentQuestion,
        rightAnswersCount = 0,
        hintedQuestions = setOf(),
        previousHintNumber = 0,
        transitionCommands = transitionCommands
    )

    fun containsTransitionCommand(commandName: String): Boolean = transitionCommands?.contains(commandName) ?: false
}