package org.example

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class IniFileReaderTest {
    @Test
    fun `should load configuration file from ini file`() {
        File("config.ini").apply {
            createNewFile()
            writeText("first=value")
        }

        val iniFileReader = IniFileReader()

        val iniFile = iniFileReader.load()
        val actual = iniFile.getParameter("first")

        assertEquals("value", actual["first"])
    }
}