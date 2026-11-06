package org.example

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.io.File
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

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

        val output = runApp(workDir, arrayListOf("no"))

        assert("Do you wish to add new parameter? [yes/no]" in output) { "Failed to found question about to add new parameter" }
        val contentOfFileAfterRunApp = configFile.readText()

        assertEquals(contentOfFileAfterRunApp, contentOfFileBeforeRunApp,"Not the same config file's content after run app")
    }

    @Test
    fun `user says 'yes' to add new parameter and config file is changed`() {
        val configFile = File(workDir, "config.ini")
        configFile.createNewFile()

        val contentOfFileBeforeRunApp = configFile.readText()

        val output = runApp(workDir, arrayListOf("yes", "yes"))

        assert("Do you wish to add new parameter? [yes/no]" in output) { "Failed to found question about to add new parameter" }
        assert("Save configuration? [yes/no]" in output) { "Save configuration prompt is not present" }

        val contentOfFileAfterRunApp = configFile.readText()

        assertNotEquals(contentOfFileAfterRunApp, contentOfFileBeforeRunApp,"The config file's content should be different after run app")
    }
}