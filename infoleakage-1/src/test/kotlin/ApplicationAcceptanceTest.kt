package org.example

import org.junit.jupiter.api.Test
import java.io.File

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

        process.waitFor()

        assert("config.ini" in (workDir.list() ?: emptyArray())) { "config.ini not found in process's workdir " }
    }
}