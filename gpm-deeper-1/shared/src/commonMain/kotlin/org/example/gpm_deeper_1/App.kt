package org.example.gpm_deeper_1

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.*
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
@Preview
fun App() {
    val plainTextEditorContent = PlainTextEditorContent()
    var text by remember { mutableStateOf(TextFieldValue(plainTextEditorContent.content())) }
    var filename by remember { mutableStateOf<String?>(null) }
    val pickFile = SimpleFilePicker { file ->
        filename = file?.name
        file?.readText()?.let {
            text = TextFieldValue(text = it)
            plainTextEditorContent.setContent(it)
        }
    }

    val saveFile = SimpleFileSaver {}

    MaterialTheme {
        FlowColumn {
            FlowRow(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                Button(onClick = { pickFile() }) {
                    Text("Open a file")
                }

                Button(onClick = { saveFile("file.txt", plainTextEditorContent.content().toByteArray()) }) {
                    Text("Save file")
                }
            }

            FlowRow(modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp)) {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .onPreviewKeyEvent { event ->
                            if (event.type == KeyEventType.KeyDown) {
                                val currentCursor = Cursor(text.selection.start, text.selection.end)

                                when (event.key) {
                                    Key.Backspace -> {
                                        val selection = text.selection
                                        val selected = !text.selection.collapsed

                                        if (selected) {
                                            if (selection.reversed) {
                                                plainTextEditorContent.deleteSelection(Selection(selection.end, selection.start))
                                            } else {
                                                plainTextEditorContent.deleteSelection(Selection(selection.start, selection.end))
                                            }
                                        } else {
                                            plainTextEditorContent.backspace(currentCursor)
                                        }
                                    }

                                    Key.Delete -> {
                                        val selection = text.selection
                                        val selected = !text.selection.collapsed

                                        if (selected) {
                                            if (selection.reversed) {
                                                plainTextEditorContent.deleteSelection(Selection(selection.end, selection.start))
                                            } else {
                                                plainTextEditorContent.deleteSelection(Selection(selection.start, selection.end))
                                            }
                                        } else {
                                            plainTextEditorContent.delete(currentCursor)
                                        }
                                    }

                                    Key.Enter -> {
                                        plainTextEditorContent.enter(currentCursor)
                                    }

                                    Key.Spacebar -> {
                                        plainTextEditorContent.spacebar(currentCursor)
                                    }

                                    else -> {
                                        val anyCharTyped = event.utf16CodePoint.toChar()
                                        val onlyLetterOrDigits =
                                            if (anyCharTyped.isLetterOrDigit()) anyCharTyped else null

                                        plainTextEditorContent.typed(currentCursor, onlyLetterOrDigits)
                                    }
                                }
                            }
                            false
                        },
                    minLines = 18,
                    maxLines = 18,
                    textStyle = TextStyle(
                        fontFamily = FontFamily.Monospace,
                        fontSize = 16.sp
                    ),
                    value = text,
                    onValueChange = { value ->
                        text = value
                    }
                )
            }

            FlowRow(modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp)) {
                Text(text = filename ?: "Click on 'Open a file'")
            }
        }
    }
}
