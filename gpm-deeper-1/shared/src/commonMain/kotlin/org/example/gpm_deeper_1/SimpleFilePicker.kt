package org.example.gpm_deeper_1

import androidx.compose.runtime.Composable
import java.io.File

@Composable
expect fun SimpleFilePicker(onResult: (File?) -> Unit): () -> Unit