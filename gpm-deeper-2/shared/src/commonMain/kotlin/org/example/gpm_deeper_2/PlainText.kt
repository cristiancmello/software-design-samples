package org.example.gpm_deeper_2

class PlainText {
    private val content = StringBuilder()

    fun insert(position: Position, value: String) {
        content.insert(position.getValue(), value)
    }

    fun delete(start: Position, end: Position) {
        content.delete(start.getValue(), end.getValue())
    }

    fun changePosition(position: Position, numChars: Int): Position {
        if (position.getValue() > 0) {
            return Position(position.getValue() + numChars)
        }

        return position
    }

    fun content(): String = content.toString()

    fun clear() = content.clear()
}
