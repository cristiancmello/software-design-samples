package org.example.gpm_deeper_2

actual object MemoryMonitor {
    actual fun heapInfo(): MemoryInfo {
        val rt = Runtime.getRuntime()
        val total = rt.totalMemory()
        val free = rt.freeMemory()
        return MemoryInfo(
            usedHeapBytes = total - free,
            totalHeapBytes = total,
            maxHeapBytes = rt.maxMemory()
        )
    }
}