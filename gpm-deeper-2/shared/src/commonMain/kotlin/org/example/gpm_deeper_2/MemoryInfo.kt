package org.example.gpm_deeper_2

data class MemoryInfo(
    val usedHeapBytes: Long,
    val totalHeapBytes: Long,
    val maxHeapBytes: Long
) {
    val usedHeapMb: String get() = format(usedHeapBytes.toMb(), 1)
    val totalHeapMb: String get() = format(totalHeapBytes.toMb(), 1)
    val maxHeapMb: String get() = format(maxHeapBytes.toMb(), 1)

    private fun Long.toMb(): Double = this / BYTES_PER_MB

    fun format(value: Double, decimals: Int): String {
        var factor = 1.0
        repeat(decimals) { factor *= 10 }
        val rounded = kotlin.math.round(value * factor) / factor
        return rounded.toString()
    }

    private companion object {
        const val BYTES_PER_MB = 1024.0 * 1024.0
    }
}

expect object MemoryMonitor {
    fun heapInfo(): MemoryInfo
}