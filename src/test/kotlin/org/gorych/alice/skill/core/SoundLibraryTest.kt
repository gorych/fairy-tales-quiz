package org.gorych.alice.skill.core

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SoundLibraryTest {

    @Test
    fun `WHEN sound tag is built THEN it should point to the uploaded audio of the skill`() {
        assertEquals(
            "<speaker audio=\"dialogs-upload/8070abca-f39f-40ec-bc59-377070bed8b8/18c4185b-b887-4227-b916-861c8b0672b4.opus\">",
            soundTag("18c4185b-b887-4227-b916-861c8b0672b4")
        )
    }
}
