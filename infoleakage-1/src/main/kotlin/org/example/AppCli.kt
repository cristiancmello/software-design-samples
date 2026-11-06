package org.example

class AppCli(
    val iniFileReader: IniFileReader,
    val iniFileWriter: IniFileWriter,
    val configUpdater: ConfigUpdater,
) {
    fun run() {
        iniFileReader.load()
        configUpdater.update()
        iniFileWriter.save()
    }
}
