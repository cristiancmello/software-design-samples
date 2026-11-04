package org.example

import java.io.File

fun runApp(workDir: File) {
    val process = ProcessBuilder(
        "java", "-cp", System.getProperty("java.class.path"), "org.example.MainKt"
    )
        .directory(workDir)
        .start()
}