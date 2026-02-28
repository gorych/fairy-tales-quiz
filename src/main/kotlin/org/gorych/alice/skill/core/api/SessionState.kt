package org.gorych.alice.skill.core.api

data class SessionState(
    val currentQuestion: Int? = null,
    val rightAnswersCount: Int = 0,
    val hintedQuestions: Set<Int> = setOf(),
    val transitionCommands: Set<String>? = setOf()
) {
    constructor(transitionCommands: Set<String>) : this(null, 0, setOf(), transitionCommands)

    constructor(currentQuestion: Int?, transitionCommands: Set<String>) : this(
        currentQuestion,
        0,
        setOf(),
        transitionCommands
    )

    fun containsTransitionCommand(commandName: String): Boolean = transitionCommands?.contains(commandName) ?: false
}