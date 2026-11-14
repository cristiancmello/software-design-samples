package org.example.gpm_deeper_1

class Cursor(private val start: Int, private val end: Int) {
    override fun toString(): String {
        return "Cursor(start=$start, end=$end)"
    }

    fun getStart() = start

    fun getEnd() = end
}