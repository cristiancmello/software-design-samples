package org.example.gpm_deeper_2

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlin.time.Duration.Companion.milliseconds

fun memoryFlow(intervalMs: Long = 2_000): Flow<MemoryInfo> = flow {
    while (true) {
        emit(MemoryMonitor.heapInfo())
        delay(intervalMs.milliseconds)
    }
}.flowOn(Dispatchers.Default)