package org.example

class AppConsole {
    fun println(message: String) {
        kotlin.io.println(message)
    }

    fun readln(): String {
        return kotlin.io.readln()
    }
}
