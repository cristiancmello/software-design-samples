package org.example

import java.io.File

fun main() {
    File("config.ini").createNewFile()

    println("Loading config file from config.ini")
}