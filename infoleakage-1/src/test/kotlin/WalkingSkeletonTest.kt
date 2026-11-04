package org.example

import org.junit.jupiter.api.Test

class WalkingSkeletonTest {
    @Test
    fun `app starts and prints loading message`() {
        val process = ProcessBuilder(
            "java", "-cp", System.getProperty("java.class.path"), "org.example.MainKt"
        ).start()

        process.outputStream.close()

        val output = process.inputStream.bufferedReader().readText()

        process.waitFor()

        assert("Loading config file from config.ini" in output) { "Failed to found loading message when app starts" }
    }
}