package org.example

import java.io.File

fun runApp(workDir: File, closeStdin: Boolean = true, input: String = "") {
    val process = ProcessBuilder(
        "java", "-cp", System.getProperty("java.class.path"), "org.example.MainKt"
    )
        .directory(workDir)
        .start()

    if (closeStdin) {
        process.outputStream.close()
    } else {
        process.outputStream.bufferedWriter().also {
            it.write(input)
            it.flush()
            it.close()
        }
    }
}