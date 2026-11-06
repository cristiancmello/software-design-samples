package org.example

import java.util.Map.entry

class AppCli(
    val iniFileReader: IniFileReader,
    val iniFileWriter: IniFileWriter,
    val configUpdater: ConfigUpdater,
    val console: AppConsole
) {
    fun run() {
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
        val saveConfig = console.readln()

        if (saveConfig == "yes") {
            iniFileWriter.save(iniFile)
        }
    }
}
