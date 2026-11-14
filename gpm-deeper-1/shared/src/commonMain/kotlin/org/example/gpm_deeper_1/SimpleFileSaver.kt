package org.example.gpm_deeper_1

import androidx.compose.runtime.Composable

@Composable
expect fun SimpleFileSaver(
    onResult: (Boolean) -> Unit = {}
): (suggestedName: String, bytes: ByteArray) -> Unit