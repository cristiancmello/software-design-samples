package org.example

import java.io.File
import java.util.Map.entry

class IniFileReader {
    fun load(): IniFile {
        val configFile = File("config.ini").apply {
            createNewFile()
        }

        val iniFile = IniFile()

        configFile.readLines().forEach { line ->
            val entryComponents = line.split(":")
            iniFile.setParameter(entry(entryComponents[0], entryComponents[1]))
        }

        return iniFile
    }
}
