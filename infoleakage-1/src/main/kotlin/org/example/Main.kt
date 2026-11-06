package org.example

fun main() {
    val configUpdater = ConfigUpdater()
    val iniFileWriter = IniFileWriter()
    val iniFileReader = IniFileReader()

    AppCli(iniFileReader, iniFileWriter, configUpdater).apply {
        run()
    }
}