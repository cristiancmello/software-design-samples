package org.example

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals
import kotlin.test.assertSame

class ApplicationAcceptanceTest {
    @Test
    fun `app starts create a empty config file`() {
        val workDir = File("filepath").also {
            it.mkdir()
        }

        val process = ProcessBuilder(
            "java", "-cp", System.getProperty("java.class.path"), "org.example.MainKt"
        )
            .directory(workDir)
            .start()

        process.outputStream.close()

        process.waitFor()

        assert("config.ini" in (workDir.list() ?: emptyArray())) { "config.ini not found in process's workdir " }
    }

    @Test
    fun `user declines to add a parameter and config file is unchanged`() {
        val workDir = File("filepath").also {
            it.mkdir()
        }

        val configFile = File(workDir, "config.ini")
        configFile.delete()
        configFile.createNewFile()

        val contentOfFileBeforeRunApp = configFile.readText()

        val process = ProcessBuilder(
            "java", "-cp", System.getProperty("java.class.path"), "org.example.MainKt"
        )
            .directory(workDir)
            .start()

        process.outputStream.bufferedWriter().also {
            it.write("no")
            it.flush()
            it.close()
        }

        val output = process.inputStream.bufferedReader().readText()

        process.waitFor()

        assert("Do you wish to add new parameter? [yes/no]" in output) { "Failed to found question about to add new parameter" }
        val contentOfFileAfterRunApp = configFile.readText()

        configFile.delete()
        workDir.delete()

        assertEquals(contentOfFileAfterRunApp, contentOfFileBeforeRunApp,"Not the same config file's content after run app")
    }
}