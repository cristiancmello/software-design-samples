package org.example.gpm_deeper_1

class PlainTextEditorContent(content: String = "") {
    private val text: StringBuilder = StringBuilder(content)

    private var currentCursor: Cursor = Cursor(start = 0, end = 0)

    fun backspace(cursor: Cursor) {
        if (cursor.getStart() > 0) {
            text.delete(cursor.getStart() - 1, cursor.getEnd())
        }
    }

    fun enter(cursor: Cursor) {
        text.insert(cursor.getStart(), "\n")
    }

    fun delete(cursor: Cursor) {
        text.delete(cursor.getStart(), cursor.getEnd() + 1)
    }

    fun deleteSelection(selection: Selection) {
        text.deleteRange(selection.start(), selection.end())
    }

    fun spacebar(cursor: Cursor) {
        text.insert(cursor.getStart(), " ")
    }

    fun content() = text.toString()

    fun setContent(content: String) {
        text.clear()
        text.append(content)
    }

    fun typed(cursor: Cursor, chr: Char?) {
        if (chr != null) text.insert(cursor.getStart(), chr)

        currentCursor = cursor
    }
}