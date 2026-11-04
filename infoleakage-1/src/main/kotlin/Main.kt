package org.example

import java.io.File

fun main() {
    File("config.ini").createNewFile()

    println("Loading config file from config.ini")

    println("Do you wish to add new parameter? [yes/no]")
}