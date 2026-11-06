package org.example

class ConfigUpdater {
    fun update(iniFile: IniFile, entry: Map.Entry<String, String>) {
        iniFile.setParameter(entry)
    }
}
