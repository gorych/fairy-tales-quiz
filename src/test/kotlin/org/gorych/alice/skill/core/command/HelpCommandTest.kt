package org.gorych.alice.skill.core.command

import kotlin.test.Test

class HelpCommandTest {

    @Test
    fun `WHEN name call THEN should return class simple name`() {
        //given
        val command = HelpCommand()
        val expected = HelpCommand::class.java.simpleName

        //when
        val actual = command.name()

        //then
        kotlin.test.assertEquals(expected, actual)
        kotlin.test.assertEquals(command.name(), HelpCommand.name())
    }
}