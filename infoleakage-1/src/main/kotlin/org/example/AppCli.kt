package org.example

import java.io.File
import java.util.Map.entry

class AppCli(
    val iniFileReader: IniFileReader,
    val iniFileWriter: IniFileWriter,
    val configUpdater: ConfigUpdater,
    val console: AppConsole
) {
    fun run() {
        File("config.ini").createNewFile()
        val iniFile = iniFileReader.load()

        println("Loading config file from config.ini")

        println("Do you wish to add new parameter? [yes/no]")
        val addNewParameter = console.readln()

        if (addNewParameter == "yes") {
            console.println("Parameter name: ")
            val parameterName = console.readln()

            console.println("Parameter value: ")
            val parameterValue = console.readln()

            configUpdater.update(iniFile, entry(parameterName, parameterValue))
        }

        println("Save configuration? [yes/no]")
        iniFileWriter.save(iniFile)
    }
}
