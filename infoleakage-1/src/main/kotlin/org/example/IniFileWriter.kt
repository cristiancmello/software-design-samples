package org.example

import java.io.File

class IniFileWriter {
    fun save(iniFile: IniFile) {
        File("config.ini").apply {
            createNewFile()

            val lines = StringBuilder()

            iniFile.getParameters().forEach { (key, value) -> lines.append("$key:$value\n") }

            writeText(lines.toString())
        }
    }
}
