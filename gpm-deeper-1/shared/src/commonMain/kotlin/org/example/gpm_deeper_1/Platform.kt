package org.example.gpm_deeper_1

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform