package org.example.gpm_deeper_1

import androidx.compose.runtime.Composable
import java.io.File

@Composable
actual fun SimpleFilePicker(onResult: (File?) -> Unit): () -> Unit {
    return {
        val dialog = java.awt.FileDialog(null as java.awt.Frame?, "Escolher arquivo", java.awt.FileDialog.LOAD)
        dialog.isVisible = true
        val file = dialog.files.firstOrNull()
        onResult(file)
    }
}