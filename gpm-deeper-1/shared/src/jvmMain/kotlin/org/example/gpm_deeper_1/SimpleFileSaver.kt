package org.example.gpm_deeper_1

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.awt.FileDialog
import java.awt.Frame
import java.io.File

@Composable
actual fun SimpleFileSaver(onResult: (Boolean) -> Unit): (suggestedName: String, bytes: ByteArray) -> Unit {
    val scope = rememberCoroutineScope()
    val currentOnResult by rememberUpdatedState(onResult)

    return { suggestedName, bytes ->
        scope.launch {
            val ok = withContext(Dispatchers.IO) {
                val dialog = FileDialog(null as Frame?, "Save file", FileDialog.SAVE)
                dialog.file = suggestedName          // nome sugerido
                dialog.isVisible = true              // bloqueia só esta coroutine

                val dir = dialog.directory
                val name = dialog.file
                if (dir == null || name == null) {
                    false                            // usuário cancelou
                } else {
                    runCatching { File(dir, name).writeBytes(bytes) }.isSuccess
                }
            }
            currentOnResult(ok)
        }
    }
}