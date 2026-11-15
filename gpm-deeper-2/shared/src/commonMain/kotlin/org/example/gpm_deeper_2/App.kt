package org.example.gpm_deeper_2

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.*
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.io.File

@Composable
@Preview
fun App() {
    val plainText = PlainText()
    var editorContentState by remember { mutableStateOf(TextFieldValue()) }
    val currentPosition = Position()
    val plainTextFile = File("plain.txt").also { it.createNewFile() }

    MaterialTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = {
                    plainText.clear()
                    plainText.insert(Position(), plainTextFile.readText())
                    editorContentState = TextFieldValue(plainText.content())
                }) {
                    Text("Load plain.txt")
                }

                Button(onClick = {
                    plainTextFile.writeText(plainText.content())
                }) {
                    Text("Save plain.txt")
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
            ) {
                TextField(
                    textStyle = TextStyle(
                        fontFamily = FontFamily.Monospace,
                        fontSize = 20.sp,
                    ),
                    value = editorContentState,
                    modifier = Modifier
                        .fillMaxSize()
                        .onPreviewKeyEvent { keyEvent ->
                            if (keyEvent.type == KeyEventType.KeyDown) {
                                when (keyEvent.key) {
                                    Key.Enter -> {
                                        plainText.insert(currentPosition, "\n")
                                    }

                                    Key.Spacebar -> {
                                        plainText.insert(currentPosition, " ")
                                    }

                                    Key.Backspace -> {
                                        val selection = editorContentState.selection
                                        val selected = !editorContentState.selection.collapsed

                                        if (selected) {
                                            val start = Position(selection.start)
                                            val end = Position(selection.end)

                                            if (selection.reversed) {
                                                plainText.delete(end, start)
                                            } else {
                                                plainText.delete(start, end)
                                            }
                                        } else {
                                            plainText.delete(
                                                plainText.changePosition(currentPosition, -1),
                                                currentPosition
                                            )
                                        }
                                    }

                                    Key.Delete -> {
                                        val selection = editorContentState.selection
                                        val selected = !editorContentState.selection.collapsed

                                        if (selected) {
                                            val start = Position(selection.start)
                                            val end = Position(selection.end)

                                            if (selection.reversed) {
                                                plainText.delete(end, start)
                                            } else {
                                                plainText.delete(start, end)
                                            }
                                        } else {
                                            plainText.delete(
                                                currentPosition,
                                                plainText.changePosition(currentPosition, 1)
                                            )
                                        }
                                    }

                                    else -> {
                                        val anyCharTyped = keyEvent.utf16CodePoint.toChar()
                                        val onlyNumberOrLetterCharTyped =
                                            if (anyCharTyped.isLetterOrDigit()) anyCharTyped else ""
                                        plainText.insert(currentPosition, onlyNumberOrLetterCharTyped.toString())
                                    }
                                }
                            }

                            false
                        },
                    onValueChange = { value ->
                        editorContentState = value
                        currentPosition.update(editorContentState.selection.start)
                    },
                )
            }
        }
    }
}