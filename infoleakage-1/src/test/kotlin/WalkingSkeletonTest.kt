package org.example

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.io.File

class WalkingSkeletonTest {
    @TempDir
    private lateinit var workDir: File

    @Test
    fun `app starts and prints loading message`() {
        val process = ProcessBuilder(
            "java", "-cp", System.getProperty("java.class.path"), "org.example.MainKt"
        )
            .directory(workDir)
            .start()

        process.outputStream.close()

        val output = process.inputStream.bufferedReader().readText()

        process.waitFor()

        assert("Loading config file from config.ini" in output) { "Failed to found loading message when app starts" }
    }
}