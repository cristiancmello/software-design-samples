package org.example.gpm_deeper_2

class Position(private var value: Int = 0) {
    fun getValue(): Int = value

    fun update(value: Int) {
        this.value = value
    }

    override fun toString(): String {
        return "Position(value=$value)"
    }
}
