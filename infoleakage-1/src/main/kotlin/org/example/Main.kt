package org.example

fun main() {
    val configUpdater = ConfigUpdater()
    val iniFileWriter = IniFileWriter()
    val iniFileReader = IniFileReader()
    val console = AppConsole()

    AppCli(iniFileReader, iniFileWriter, configUpdater, console).apply {
        run()
    }
}