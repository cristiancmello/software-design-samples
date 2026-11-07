package org.example

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import java.io.File
import java.util.Map.entry
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class IniFileWriterTest {
    @AfterEach
    fun tearDown() {
        File("config.ini").delete()
    }

    @Test
    fun `should write iniFile with updated entries`() {
        val configFile = File("config.ini").apply {
            createNewFile()
        }

        val contentBeforeSave = configFile.readText()

        val iniFileWriter = IniFileWriter()
        val iniFile = IniFile()

        iniFile.setParameter(entry("key", "value"))

        iniFileWriter.save(iniFile)

        val contentAfterSave = configFile.readText()

        assertNotEquals(contentBeforeSave, contentAfterSave, "Content after saved should be different")
        assertEquals("key:value\n", contentAfterSave)
    }
}