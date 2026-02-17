package org.gorych.alice.skill.core.api

class SessionState(val currentQuestion: Int? = null, val transitionCommands: Set<String>? = setOf()) {

    constructor(transitionCommands: Set<String>) : this(null, transitionCommands)

    fun containsTransitionCommand(commandName: String): Boolean = transitionCommands?.contains(commandName) ?: false
}