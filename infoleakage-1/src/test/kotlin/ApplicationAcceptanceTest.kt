package org.example

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.io.File
import kotlin.test.assertEquals

class ApplicationAcceptanceTest {
    @TempDir
    private lateinit var workDir: File

    @Test
    fun `app starts create a empty config file`() {
        runApp(workDir)

        assert("config.ini" in (workDir.list() ?: emptyArray())) { "config.ini not found in process's workdir " }
    }

    @Test
    fun `user declines to add a parameter and config file is unchanged`() {
        val configFile = File(workDir, "config.ini")
        configFile.createNewFile()

        val contentOfFileBeforeRunApp = configFile.readText()

        val output = runApp(workDir, "no")

        assert("Do you wish to add new parameter? [yes/no]" in output) { "Failed to found question about to add new parameter" }
        val contentOfFileAfterRunApp = configFile.readText()

        assertEquals(contentOfFileAfterRunApp, contentOfFileBeforeRunApp,"Not the same config file's content after run app")
    }
}