package org.example

import java.io.File
import java.util.Map.entry

class AppCli(
    val iniFileReader: IniFileReader,
    val iniFileWriter: IniFileWriter,
    val configUpdater: ConfigUpdater,
) {
    fun run() {
        File("config.ini").createNewFile()
        iniFileReader.load()

        println("Loading config file from config.ini")

        println("Do you wish to add new parameter? [yes/no]")
        configUpdater.update(IniFile(), entry("key", "value"))

        println("Save configuration? [yes/no]")
        iniFileWriter.save(IniFile())
    }
}
