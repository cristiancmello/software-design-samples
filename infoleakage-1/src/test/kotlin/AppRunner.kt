package org.example

import java.io.File

fun runApp(workDir: File, input: String = ""): String {
    val process = ProcessBuilder(
        "java", "-cp", System.getProperty("java.class.path"), "org.example.MainKt"
    )
        .directory(workDir)
        .start()

    process.outputStream.bufferedWriter().use {
        it.write(input)
    }

    val output = process.inputStream.bufferedReader().readText()

    process.waitFor()

    return output
}