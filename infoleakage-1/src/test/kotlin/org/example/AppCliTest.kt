package org.example

import io.mockk.every
import io.mockk.mockk
import io.mockk.verifyOrder
import org.junit.jupiter.api.Test

class AppCliTest {
    @Test
    fun `should read, update storage and save file`() {
        val iniFileReader = mockk<IniFileReader>(relaxUnitFun = true)
        val configUpdater = mockk<ConfigUpdater>(relaxUnitFun = true)
        val iniFileWriter = mockk<IniFileWriter>(relaxUnitFun = true)
        val console = mockk<AppConsole>(relaxUnitFun = true)
        val appCli = AppCli(iniFileReader, iniFileWriter, configUpdater, console)

        every { iniFileReader.load() } returns IniFile()
        every { console.println(any<String>()) } returns Unit
        every { console.readln() } returns
            "yes" andThen
            "first" andThen
            "value" andThen
            "yes"

        appCli.run()

        verifyOrder {
            iniFileReader.load()
            configUpdater.update(any(), any())
            iniFileWriter.save(any())
        }
    }
}