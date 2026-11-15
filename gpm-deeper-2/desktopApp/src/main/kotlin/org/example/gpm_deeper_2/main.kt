package org.example.gpm_deeper_2

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Simple Plain Text Editor",
        resizable = false
    ) {
        App()
    }
}